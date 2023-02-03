package com.example.core.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.core.enums.ResultCode;
import com.example.core.model.vo.Result;
import com.example.core.utils.PageUtil;
import com.example.core.utils.ResultUtil;
import com.example.core.utils.SnowflakeIdWorker;
import com.example.core.model.dto.SelectListUnit;
import com.example.core.model.dto.SelectableEntity;
import com.example.core.model.dto.UnrepeatableEntity;
import com.example.core.service.AbsService;
import org.springframework.dao.DuplicateKeyException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * ! 如非特别必要，请不要改动！！！！！！！
 * <p>
 * 注意传入泛型
 * 一样的功能使用抽象类统一继承使用
 * ! 注意：这个类在大多数情况下不需要二次修改，即如果某个接口有附加的注意事项或者条件，那么就直接在对应的自己的ServiceImpl层override那个方法，保证这个类足够的简洁有效，也方便后期维护
 *
 * @author lincheon
 */
public interface AbsServiceImpl<M, T> extends AbsService<T> {

    static SnowflakeIdWorker WORKER=new SnowflakeIdWorker(0,0);

    @Override
    int confirmDelete(String id);

    @Override
    Page deepSearch(Page page, Wrapper wrapper);

    default String convertUnderLine(Class<T> entityClass) {
        String bigCamel = entityClass.getSimpleName();
        StringBuilder underline = new StringBuilder();
        for (int i = 0; i < bigCamel.length(); i++) {
            char c = bigCamel.charAt(i);
            if (c <= 90 && c >= 65) {
                if (i != 0) {
                    underline.append("_");
                }
                underline.append(Character.toLowerCase(c));
            } else {
                underline.append(c);
            }
        }
        return underline.toString();
    }


    default String convertUnderLine(String smallCamel) {
        StringBuilder underline = new StringBuilder();
        for (int i = 0; i < smallCamel.length(); i++) {
            char c = smallCamel.charAt(i);
            if (c <= 90 && c >= 65) {
                if (i != 0) {
                    underline.append("_");
                }
                underline.append(Character.toLowerCase(c));
            } else {
                underline.append(c);
            }
        }
        return underline.toString();
    }


    @Override
    default JSONObject beforeCreate(JSONObject jsonObject) {
        return jsonObject;
    }

    @Override
    default Object afterCreate(Object entity) {
        return entity;
    }


    @Override
    default Result<String> create(String requestBody, Class<T> goalClass, IService<T> service) {
        System.out.println("requestBody = " + requestBody);
        try {
            JSONObject jsonObject = JSONObject.parseObject(requestBody);
            jsonObject.put("id", WORKER.nextId());
            if (UnrepeatableEntity.class.isAssignableFrom(goalClass)) {
                Method getJudgeProperty = goalClass.getMethod("judgeProperty", null);
                Object obj = goalClass.newInstance();
                String judge = (String) getJudgeProperty.invoke(obj, null);
                if(jsonObject.containsKey(judge)) {
                    String judge_value = jsonObject.getString(judge);
                    QueryWrapper<T> condition = new QueryWrapper<T>().eq(convertUnderLine(judge), judge_value);
                    T one = service.getOne(condition);
                    if (one != null) {
                        return ResultUtil.fail(ResultCode.CREATE_FAIL, "重复添加,已存在");
                    }
                }
            }
            jsonObject = beforeCreate(jsonObject);
            Object entity = JSONObject.toJavaObject(jsonObject, goalClass);
            entity = afterCreate(entity);
            try {
                System.out.println("entity = " + entity);
                service.save((T) entity);
            } catch (DuplicateKeyException e) {
                e.printStackTrace();
                return ResultUtil.fail(ResultCode.CREATE_CONFLICT, "添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.fail(ResultCode.CREATE_FAIL, "添加失败");
        }
        return ResultUtil.success(ResultCode.CREATE_SUCCESS, "添加成功");
    }

    @Override
    default void beforeDelete(JSONArray ids) {

    }

    @Override
    default Result<String> delete(String requestBody, IService<T> service) {
        int code;
        String message;
        try {
            boolean isAllNotFound = false, isHaveNotFound = false;
            boolean isAllAssociated = false, isHaveAssociated = false;
            JSONArray ids = JSONArray.parseArray(requestBody);
            int associatedCount = 0;
            int notFoundCount = 0;
            int oldSize = ids.size();
            for (int i = 0; i < ids.size(); i++) {
                String id = ids.getString(i);
                // 0：可以删除、1：存在关联、2：不存在这个id
                int result = confirmDelete(id);
                if (result == 1) {
                    isHaveAssociated = true;
                    associatedCount += 1;
                    ids.remove(i--);
                } else if (result == 2) {
                    isHaveNotFound = true;
                    notFoundCount += 1;
                    ids.remove(i--);
                }
            }
            if (associatedCount == oldSize) {
                isAllAssociated = true;
            }
            if (notFoundCount == oldSize) {
                isAllNotFound = true;
            }
            beforeDelete(ids);
            service.removeByIds(ids.toJavaList(String.class));
            if (isHaveAssociated && isHaveNotFound) {
                code = ResultCode.DELETE_DATA_NOT_FOUND_OR_ASSOCIATED.code();
                message = ResultCode.DELETE_DATA_NOT_FOUND_OR_ASSOCIATED.message();
            } else if (isHaveAssociated) {
                if (isAllAssociated) {
                    code = ResultCode.ALL_DELETE_DATA_ASSOCIATED.code();
                    message = ResultCode.ALL_DELETE_DATA_ASSOCIATED.message();
                } else {
                    code = ResultCode.PARTIAL_DELETE_DATA_ASSOCIATED.code();
                    message = ResultCode.PARTIAL_DELETE_DATA_ASSOCIATED.message();
                }
            } else if (isHaveNotFound) {
                if (isAllNotFound) {
                    code = ResultCode.ALL_DELETE_DATA_NOT_FOUND.code();
                    message = ResultCode.ALL_DELETE_DATA_NOT_FOUND.message();
                } else {
                    code = ResultCode.PARTIAL_DELETE_DATA_NOT_FOUND.code();
                    message = ResultCode.PARTIAL_DELETE_DATA_NOT_FOUND.message();
                }
            } else {
                code = ResultCode.DELETE_SUCCESS.code();
                message = ResultCode.DELETE_SUCCESS.message();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.fail(ResultCode.DELETE_FAIL, "删除失败");
        }
        return new Result<String>(code, message, null);
    }

    @Override
    default void beforeUpdate(Object entity) {

    }
    @Override
    default void afterUpdate(Object oldEntity, Object newEntity) {

    }

    @Override
    default Result<String> update(String requestBody, Class<T> entityClass, IService<T> service) {
        try {
            System.out.println("requestBody = " + requestBody);
            JSONObject jsonObject = JSONObject.parseObject(requestBody);
            Object oldEntity = service.getById(jsonObject.getString("id")); // 旧数据
            if (oldEntity == null) {
                return ResultUtil.fail(ResultCode.UPDATE_FAIL, "未找到相应数据");
            }
            if(UnrepeatableEntity.class.isAssignableFrom(entityClass)){
                Method getJudgeProperty = entityClass.getMethod("judgeProperty", null);
                Object obj = entityClass.newInstance();
                String judge = (String) getJudgeProperty.invoke(obj, null);
                if(jsonObject.containsKey(judge)) {
                    String judge_value = jsonObject.getString(judge);
                    QueryWrapper<T> condition = new QueryWrapper<T>().eq(convertUnderLine(judge), judge_value);
                    T one = service.getOne(condition);
                    if (one != null) {
                        return ResultUtil.fail(ResultCode.CREATE_FAIL, "重复添加,已存在");
                    }
                }
            }
            Object newEntity = jsonObject.toJavaObject(entityClass);
            try {
                beforeUpdate(newEntity);
                service.updateById((T) newEntity);
                afterUpdate(oldEntity, newEntity);
            } catch (DuplicateKeyException e) {
                e.printStackTrace();
                return ResultUtil.fail(ResultCode.UPDATE_CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.fail(ResultCode.UPDATE_FAIL);
        }
        return ResultUtil.success(ResultCode.UPDATE_SUCCESS);
    }

    @Override
    default String beforeList(String requestBody) {
        return requestBody;
    }

    @Override
    default void afterList(Page result) {

    }

    @Override
    default void queryDispose(String tableName, JSONObject query, Set<String> keys, QueryWrapper<T> wrapper) {
        for (String key : keys) {
            Object v = query.get(key);
            if ((v == null) || "".equals(v)) {
                continue;
            }
            String k = convertUnderLine(key);
            wrapper.eq("`" + tableName + "`." + k, v);
//            wrapper.like(k,v); // 模糊查询设置
        }
    }


    @Override
    default Result list(String requestBody, Class<T> entityClass, IService<T> service) {
        try {
            requestBody=beforeList(requestBody);
            JSONObject jsonObject = JSONObject.parseObject(requestBody);
            JSONObject page = jsonObject.getJSONObject("page");
            JSONObject sort = jsonObject.getJSONObject("sort");
            JSONObject query = jsonObject.getJSONObject("query");
            Boolean isDeep = (Boolean) jsonObject.getOrDefault("isDeep", true);
            QueryWrapper<T> wrapper = new QueryWrapper<T>();
            wrapper.isNotNull("`" + convertUnderLine(entityClass.getSimpleName()) + "`.id");
            if (query != null) {
                Set<String> keys = query.keySet();
                queryDispose(convertUnderLine(entityClass.getSimpleName()), query, keys, wrapper);
            }
            if (sort != null && sort.getString("property") != null && !"".equals(sort.getString("property"))) {
                wrapper.orderBy(true, !"descending".equals(sort.getString("direction")), convertUnderLine(sort.getString("property")));
            }
            int currentPageValue = page == null ? 1 : page.getInteger("current"),
                    pageSizeValue = page == null ? 20 : page.getInteger("size");
            Page result = null;
            if (isDeep) {
                result = ((AbsService) service).deepSearch(new Page<>(currentPageValue, pageSizeValue), wrapper);
            }
            if (result == null) {
                result = service.page(new Page<>(currentPageValue, pageSizeValue), wrapper);
            }
            afterList(result);
            HashMap data = PageUtil.getResponsePage(result);
            return ResultUtil.success(ResultCode.SUCCESS, "获取数据成功", data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.fail(ResultCode.FAIL, "获取数据失败");
        }
    }

    @Override
    default Result selectList(Class<T> entityClass, IService<T> service) {
        try {
            if (!SelectableEntity.class.isAssignableFrom(entityClass)) {
                return ResultUtil.fail(ResultCode.GET_DATA_FAIL);
            }
            QueryWrapper<T> wrapper = new QueryWrapper<T>();
            List<T> entities = service.list(null);
            List<SelectListUnit> selectList = new ArrayList<>();
            for (T entity : entities) {
                if (!((SelectableEntity) entity).unAble()) {
                    selectList.add(new SelectListUnit(((SelectableEntity) entity).getId(), ((SelectableEntity) entity).selectValue()));
                }
            }
            return ResultUtil.success(ResultCode.SUCCESS, "获取数据成功", selectList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.fail(ResultCode.GET_DATA_FAIL);
        }
    }

    @Override
    default Result detail(String id, IService<T> service, Class<T> entityClass) {
        Page result;
        String tableName = convertUnderLine(entityClass.getSimpleName());
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("`" + tableName + "`" + ".id", id);result = ((AbsService) service).deepSearch(new Page<>(), wrapper);

        if (result == null) {
            T entity = service.getById(id);
            if (entity == null) {
                return ResultUtil.fail(ResultCode.GET_DATA_FAIL, "未找到相应数据");
            }
            return ResultUtil.success(ResultCode.GET_DATA_SUCCESS, "数据获取成功", entity);
        }
        if (result.getTotal() == 0) {
            return ResultUtil.fail(ResultCode.GET_DATA_FAIL, "未找到相应数据");
        }
        return ResultUtil.success(ResultCode.GET_DATA_SUCCESS, "数据获取成功", result.getRecords().get(0));
    }
}
