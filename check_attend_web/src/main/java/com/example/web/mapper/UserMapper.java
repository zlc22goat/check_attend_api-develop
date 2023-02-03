package com.example.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.model.pojo.User;
import com.example.web.model.vo.LoginUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tzy
 * @since 2021-03-27
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    LoginUserVo getUserAllInfo(@Param("userName")String userName);
}
