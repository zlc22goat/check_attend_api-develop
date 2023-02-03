package com.example.web.controller;


import com.example.core.controller.AbsController;
import com.example.web.model.pojo.OvertimeRecord;
import com.example.web.service.OvertimeRecordService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lincheon
 * @since 2022-05-19
 */
@RestController
@RequestMapping("/overtime-record")
public class OvertimeRecordController extends AbsController<OvertimeRecordService, OvertimeRecord> {

}

