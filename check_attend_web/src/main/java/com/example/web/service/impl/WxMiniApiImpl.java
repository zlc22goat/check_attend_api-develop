package com.example.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.core.utils.WeChatUtil;
import com.example.web.service.WxMiniApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author lincheon
 * @since 2022/1/8 17:20
 **/


@Slf4j
@Service
public class WxMiniApiImpl implements WxMiniApi {

    @Override
    public JSONObject authCode2Session(String appId, String secret, String jsCode) {

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret + "&js_code=" + jsCode + "&grant_type=authorization_code";
        String str = WeChatUtil.httpRequest(url, "GET", null);
        log.info("api/wx-mini/getSessionKey:" + str);
        if (StringUtils.isEmpty(str)) {
            return null;
        } else {
            return JSONObject.parseObject(str);
        }

    }
}

