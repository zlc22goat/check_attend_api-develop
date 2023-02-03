package com.example.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.HashMap;

/**
 * created by CaiBaoHong at 2018/5/16 10:15<br>
 * Edited by Ginirohikocha on 2019/8/11.
 */
public class PageUtil {

    /**
     * 获取分页参数
     *
     * @param json
     * @return
     */
    public static Page getPageParam(JSONObject json) {
        int current = json.getIntValue("current");
        int size = json.getIntValue("size");
        if (current == 0) current = 1;
        if (size == 0) size = 10;
        return new Page(current, size);
    }

    public static HashMap<String, Object> getResponsePage(Page page) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("items", page.getRecords());
        return data;
    }

}
