package com.example.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.core.service.impl.AbsServiceImpl;
import com.example.web.model.pojo.VacateCategory;
import com.example.web.mapper.VacateCategoryMapper;
import com.example.web.service.VacateCategoryService;
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
public class VacateCategoryServiceImpl extends ServiceImpl<VacateCategoryMapper, VacateCategory> implements VacateCategoryService, AbsServiceImpl<VacateCategoryService,VacateCategory> {

    @Override
    public int confirmDelete(String id) {
        return 0;
    }

    @Override
    public Page deepSearch(Page page, Wrapper wrapper) {
        return null;
    }
}
