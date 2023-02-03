package com.example.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.core.model.pojo.User;
import com.example.core.service.AbsService;
import com.example.core.service.impl.AbsServiceImpl;
import com.example.core.utils.AcquireRequestMsg;
import com.example.core.utils.JWTUtils;
import com.example.web.mapper.OvertimeRecordMapper;
import com.example.web.mapper.UserMapper;
import com.example.web.model.pojo.BusinessTripRecord;
import com.example.web.model.pojo.RestRecord;
import com.example.web.mapper.RestRecordMapper;
import com.example.web.service.RestRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lincheon
 * @since 2022-05-19
 */
@Service
public class RestRecordServiceImpl extends ServiceImpl<RestRecordMapper, RestRecord> implements RestRecordService, AbsServiceImpl<RestRecordService,RestRecord> {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OvertimeRecordMapper overtimeRecordMapper;

    @Override
    public int confirmDelete(String id) {
        return 0;
    }

    @Override
    public Page deepSearch(Page page, Wrapper wrapper) {
        return page.setRecords(baseMapper.deepSearch(page,wrapper));
    }
    @Override
    public JSONObject beforeCreate(JSONObject jsonObject) {
        HttpServletRequest request = AcquireRequestMsg.getRequest();
        String token = request.getHeader("token");
        String username = JWTUtils.getUsername(token);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("user_name",username);
        User user = userMapper.selectOne(wrapper);
        jsonObject.put("employeId",user.getEmployeId());
        jsonObject.put("status","0");
        Double overtimes = overtimeRecordMapper.getOvertimes(user.getEmployeId());
        Double restTimes = baseMapper.getRestTimes(user.getEmployeId());
        jsonObject.put("restResidue",(overtimes!=null?overtimes:0)-(restTimes!=null?restTimes:0));
        return AbsServiceImpl.super.beforeCreate(jsonObject);
    }

    public User getUser(){
        HttpServletRequest request = AcquireRequestMsg.getRequest();
        String token = request.getHeader("token");
        String username = JWTUtils.getUsername(token);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("user_name",username);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public String beforeList(String requestBody) {
        JSONObject object = JSONObject.parseObject(requestBody);
        JSONObject query = !object.containsKey("query")?new JSONObject():object.getJSONObject("query");
        if(query.containsKey("isAll")&&query.getBoolean("isAll")){
            query.remove("isAll");
        }
        else if(!query.containsKey("employeId")|| ObjectUtils.isEmpty(query.getString("employeId"))) {
            query.put("employeId",getUser().getEmployeId());
        }
        requestBody = JSONObject.toJSONString(object);
        return requestBody;
    }
    @Override
    public void beforeUpdate(Object entity) {
        ((RestRecord)entity).setAuditorId(getUser().getEmployeId());
        AbsServiceImpl.super.beforeUpdate(entity);
    }

}
