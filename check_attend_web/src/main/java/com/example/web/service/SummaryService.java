package com.example.web.service;

import com.example.core.model.vo.Result;
import com.example.web.model.pojo.Summary;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lincheon
 * @since 2022-05-22
 */
public interface SummaryService extends IService<Summary> {
    Result summarize(String pageInfo);
}
