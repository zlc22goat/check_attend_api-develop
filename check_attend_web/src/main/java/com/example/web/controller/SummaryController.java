package com.example.web.controller;


import com.example.core.model.vo.Result;
import com.example.web.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lincheon
 * @since 2022-05-22
 */
@RestController
@RequestMapping("/summary")
public class SummaryController {
    @Autowired
    private SummaryService summaryService;
    @PostMapping("summarize")
    public Result summarize(@RequestBody String pageInfo){
        return summaryService.summarize(pageInfo);
    }
}

