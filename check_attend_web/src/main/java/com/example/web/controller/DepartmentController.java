package com.example.web.controller;


import com.example.core.controller.AbsController;
import com.example.core.model.vo.Result;
import com.example.web.model.pojo.Department;
import com.example.web.service.DepartmentService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lincheon
 * @since 2022-05-22
 */
@RestController
@RequestMapping("/department")
public class DepartmentController extends AbsController<DepartmentService, Department> {
    @Override
    public Result<String> create(HttpServletRequest request, String requestBody) {
        return null;
    }

    @Override
    public Result<String> delete(HttpServletRequest request, String requestBody) {
        return null;
    }

    @Override
    public Result<String> update(HttpServletRequest request, String requestBody) {
        return null;
    }

    @Override
    public Result list(HttpServletRequest request, String requestBody) {
        return null;
    }

    @Override
    public Result selectList() {
        return super.selectList();
    }

    @Override
    public Result detail(String id) {
        return null;
    }
}

