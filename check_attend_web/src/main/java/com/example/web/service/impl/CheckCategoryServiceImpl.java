package com.example.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.core.service.impl.AbsServiceImpl;
import com.example.web.model.pojo.CheckCategory;
import com.example.web.mapper.CheckCategoryMapper;
import com.example.web.service.CheckCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.web.service.CheckRecordService;
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
public class CheckCategoryServiceImpl extends ServiceImpl<CheckCategoryMapper, CheckCategory> implements CheckCategoryService, AbsServiceImpl<CheckRecordService,CheckCategory> {

    @Override
    public int confirmDelete(String id) {
        return 0;
    }

    @Override
    public Page deepSearch(Page page, Wrapper wrapper) {
        return null;
    }
}
