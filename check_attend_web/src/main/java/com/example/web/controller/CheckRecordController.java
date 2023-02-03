package com.example.web.controller;


import com.example.core.controller.AbsController;
import com.example.core.model.vo.Result;
import com.example.web.model.pojo.CheckRecord;
import com.example.web.service.CheckRecordService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lincheon
 * @since 2022-05-19
 */
@RestController
@RequestMapping("/check-record")
@RequiresRoles(value="ordinary")
public class CheckRecordController extends AbsController<CheckRecordService, CheckRecord> {

}

