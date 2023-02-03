package com.example.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.core.model.pojo.User;
import com.example.core.model.vo.Result;
import com.example.core.service.impl.AbsServiceImpl;
import com.example.core.utils.AcquireRequestMsg;
import com.example.core.utils.JWTUtils;
import com.example.web.mapper.UserMapper;
import com.example.web.model.pojo.CheckRecord;
import com.example.web.mapper.CheckRecordMapper;
import com.example.web.service.CheckRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.web.service.UserService;
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
public class CheckRecordServiceImpl extends ServiceImpl<CheckRecordMapper, CheckRecord> implements CheckRecordService, AbsServiceImpl<CheckRecordService, CheckRecord> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int confirmDelete(String id) {
        return 0;
    }

    @Override
    public Page deepSearch(Page page, Wrapper wrapper) {
        return page.setRecords(baseMapper.deepSearch(page, wrapper));
    }

    @Override
    public JSONObject beforeCreate(JSONObject jsonObject) {
        HttpServletRequest request = AcquireRequestMsg.getRequest();
        String token = request.getHeader("token");
        String username = JWTUtils.getUsername(token);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("user_name",username);
        User user = userMapper.selectOne(wrapper);
        jsonObject.put("recorderId",user.getEmployeId());
        return AbsServiceImpl.super.beforeCreate(jsonObject);
    }

}
