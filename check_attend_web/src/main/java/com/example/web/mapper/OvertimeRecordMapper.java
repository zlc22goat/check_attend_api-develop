package com.example.web.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.web.model.pojo.OvertimeRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web.model.vo.BusinessTripRecordVo;
import com.example.web.model.vo.OvertimeRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lincheon
 * @since 2022-05-19
 */
public interface OvertimeRecordMapper extends BaseMapper<OvertimeRecord> {
    List<OvertimeRecordVo> deepSearch(Page page, @Param("ew") Wrapper wrapper);
    Double getOvertimes(@Param("employeId") String id);
}
