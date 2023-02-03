package com.example.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.core.service.impl.AbsServiceImpl;
import com.example.web.model.pojo.Employe;
import com.example.web.mapper.EmployeMapper;
import com.example.web.service.EmployeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lincheon
 * @since 2022-05-19
 */
@Service
public class EmployeServiceImpl extends ServiceImpl<EmployeMapper, Employe> implements EmployeService, AbsServiceImpl<EmployeService,Employe> {

    @Override
    public int confirmDelete(String id) {
        return 0;
    }

    @Override
    public Page deepSearch(Page page, Wrapper wrapper) {
        return page.setRecords(baseMapper.deepSearch(page,wrapper));
    }
}
