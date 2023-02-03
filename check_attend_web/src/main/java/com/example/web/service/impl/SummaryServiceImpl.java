package com.example.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.core.enums.ResultCode;
import com.example.core.model.vo.Result;
import com.example.core.utils.PageUtil;
import com.example.core.utils.ResultUtil;
import com.example.web.model.pojo.Summary;
import com.example.web.mapper.SummaryMapper;
import com.example.web.model.vo.SummaryVo;
import com.example.web.service.SummaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lincheon
 * @since 2022-05-22
 */
@Service
public class SummaryServiceImpl extends ServiceImpl<SummaryMapper, Summary> implements SummaryService {

    @Autowired
    private SummaryMapper summaryMapper;

    @Override
    public Result summarize(String pageInfo) {
        JSONObject info = JSONObject.parseObject(pageInfo);
        JSONObject page = info.getJSONObject("page");
        JSONObject query = info.getJSONObject("query");
        int currentPage= page==null?1:page.getInteger("current"),
                currentSize=page==null?20:page.getInteger("size");
        String departmentId= query==null?null:query.getString("departmentId");
        String startTime= query==null?null:query.getString("startTime");
        String endTime= query==null?null:query.getString("endTime");
        Page<SummaryVo> result=new Page<>(currentPage,currentSize);
        result.setRecords(summaryMapper.getSummary(result,startTime,endTime,departmentId));
        return ResultUtil.success(ResultCode.SUCCESS,"获取数据成功", PageUtil.getResponsePage(result));
    }
}
