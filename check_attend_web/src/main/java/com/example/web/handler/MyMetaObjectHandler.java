package com.example.web.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 *
 * 实体类入库时候自动填充创建时间和修改时间
 *
 * @author tzy
 * @since 2021/8/1 20:13
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert……");
        this.strictInsertFill(metaObject,"gmtCreate", LocalDateTime.class,LocalDateTime.now());
//        this.strictInsertFill(metaObject,"gmtModified",LocalDateTime.class,LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start modify……");
        this.strictUpdateFill(metaObject,"gmtUpdate",LocalDateTime.class,LocalDateTime.now());
//        this.strictUpdateFill(metaObject,"gmtModified",LocalDateTime.class,LocalDateTime.now());
    }
}
