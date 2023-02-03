package com.example.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.core.service.impl.AbsServiceImpl;
import com.example.web.model.pojo.TripMode;
import com.example.web.mapper.TripModeMapper;
import com.example.web.service.TripModeService;
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
public class TripModeServiceImpl extends ServiceImpl<TripModeMapper, TripMode> implements TripModeService, AbsServiceImpl<TripModeService,TripMode> {

    @Override
    public int confirmDelete(String id) {
        return 0;
    }

    @Override
    public Page deepSearch(Page page, Wrapper wrapper) {
        return null;
    }
}
