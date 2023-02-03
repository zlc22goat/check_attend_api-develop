package com.example.web.mapper;

import com.example.web.model.pojo.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lincheon
 * @since 2022-03-04
 */
@Repository
@Mapper
public interface RecordMapper extends BaseMapper<Record> {

}
