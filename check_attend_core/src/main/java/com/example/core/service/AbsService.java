package com.example.core.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.core.model.vo.Result;

import java.util.Set;

/**
 *
 * ! 如非特别必要，请不要改动！！！！！！！
 *
 * 注意传入泛型
 * 一样的功能使用抽象类统一继承使用
 * ! 注意：这个类在大多数情况下不需要二次修改，即如果某个接口有附加的注意事项或者条件，那么就直接在对应的自己的Service层override那个方法，保证这个抽象类足够的简洁有效，也方便后期维护
 *
 * @author lincheon
 */
public interface AbsService<T> {

    int confirmDelete(String id);

    Page deepSearch(Page page,Wrapper wrapper);

    /**
     * Create 的钩子函数，预处理（比如有些字段数据需要结合前端传来的数据进行计算，就可以在这里做截断处理）
     * ！requestBody转为相应的类的之前
     */
    JSONObject beforeCreate(JSONObject jsonObject);

    /**
     * Create 的钩子函数，后处理（比如有些字段数据需要结合前端传来的数据进行计算，就可以在这里做截断处理）
     * ！requestBody转为相应的类的之后，可以对转换成类后再做一些处理
     */
    Object afterCreate(Object entity);

    /**
     * 新增
     */
    Result<String> create(String requestBody, Class<T> goalClass, IService<T> service);

    /**
     * 在正式删除前可以在这里添加附加的处理(注意：已经走过一遍筛选工作)
     */
    void beforeDelete(JSONArray ids);

    /**
     * 批量删除 (通过confirmDelete判断是否可以直接删除)
     */
    Result<String> delete(String requestBody, IService<T> service);

    /**
     * 在正式更新数据前的操作（例如：如果修改这个数据需要其他表对应进行部分的修改，可以使用这个方法 ………………）
     */
    void beforeUpdate(Object entity);

    /**
     * 在更新数据后可以添加进行的操作（例如：如果修改这个数据需要其他表对应进行部分的修改，可以使用这个方法 ………………）
     */
    void afterUpdate(Object oldEntity, Object newEntity);

    /**
     * 更新
     */
    Result<String> update(String requestBody, Class<T> entityClass, IService<T> service);

    /**
     * 在正式查询前对其预处理(例如：处理下前端传来的requestBody)
     */
    String beforeList(String requestBody);

    /**
     * 查询后数据的处理
     */
    void afterList(Page result);

    /**
     * 查询条件的处理(可人为覆写)(覆写场景：当出现有些查询条件需要二次处理的时候，注意最好不要用来做深查询，自己写深查询需要写sql语句)
     */
    void queryDispose(String tableName,JSONObject query, Set<String> keys, QueryWrapper<T> wrapper);

    /**
     * 查询（不会深搜 即关联的数据也一并查询，需要根据业务场景重写）
     */
    Result list(String requestBody, Class<T> entityClass, IService<T> service);

    /**
     * 下拉列表查询
     */
    Result selectList(Class<T> entityClass, IService<T> service);

    /**
     * 细节查询(根据id)
     */
    Result detail(String id,IService<T> service,Class<T> entityClass);

}
