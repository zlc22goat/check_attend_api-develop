package com.example.web.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.web.model.pojo.Summary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web.model.vo.SummaryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lincheon
 * @since 2022-05-22
 */
public interface SummaryMapper extends BaseMapper<Summary> {
    List<SummaryVo> getSummary(Page page, @Param("st") String startTime, @Param("et") String endTime, @Param("dmi") String departmentId);
}
