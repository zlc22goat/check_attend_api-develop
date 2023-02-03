package com.example.web.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.core.enums.ResultCode;
import com.example.core.model.vo.Result;
import com.example.core.utils.JWTUtils;
import com.example.core.utils.ResultUtil;
import com.example.core.utils.UUIDUtil;
import com.example.core.utils.WeChatUtil;
import com.example.web.model.dto.AuthUserDto;
import com.example.web.model.pojo.WxUser;
import com.example.web.mapper.WxUserMapper;
import com.example.web.service.WxMiniApi;
import com.example.web.service.WxUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lincheon
 * @since 2022-01-08
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements WxUserService {

    @Value("${wxMini.appId}")
    private String appId;
    @Value("${wxMini.secret}")
    private String secret;

    @Autowired
    private WxMiniApi wxMiniApi;

    @Autowired
    private WxUserMapper wxUserMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result login(AuthUserDto authUserDto, HttpServletRequest request) {
        System.out.println("authUserDto = " + authUserDto);
        JSONObject jsonObject = wxMiniApi.authCode2Session(appId, secret, authUserDto.getCode());
        if (jsonObject == null) {
            throw new RuntimeException("调用微信端授权接口错误");
        }
        String openId = jsonObject.getString("openid");
        String sessionKey = jsonObject.getString("session_key");
        String unionId = jsonObject.getString("unionid");
        if (StringUtils.isEmpty(openId)) {
            return new Result(Integer.parseInt(jsonObject.getString("errcode")), jsonObject.getString("errmsg"));
        }
        authUserDto.setOpenId(openId);
        WxUser wxUser = this.findByOpenId(openId);
        if (ObjectUtils.isEmpty(wxUser)) {
            String userInfo = WeChatUtil.decryptData(authUserDto.getEncryptedData(), sessionKey, authUserDto.getIv());
            System.out.println("de_userInfo = " + userInfo);
            if (StringUtils.isEmpty(userInfo)) {
                throw new RuntimeException("解密用户对象错误");
            }
            WxUser deCodeUser = JSONObject.parseObject(userInfo, WxUser.class);
            if (ObjectUtils.isEmpty(deCodeUser)) {
                throw new RuntimeException("填充用户对象错误");
            }
            deCodeUser.setUnionId(unionId);
            deCodeUser.setOpenId(openId);
            this.save(deCodeUser);
            authUserDto.setUuid(deCodeUser.getId());
            authUserDto.setUserInfo(deCodeUser);
        } else {
            authUserDto.setUserInfo(wxUser);
            authUserDto.setUuid(wxUser.getId());
        }
        String token = JWTUtils.wxSign(openId);
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("生成token失败");
        }
        authUserDto.setToken(token);
        return ResultUtil.success(ResultCode.LOGIN_SUCCESS,"登陆成功",authUserDto);
    }

    @Override
    public WxUser findByOpenId(String openId) {
        QueryWrapper<WxUser> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id", openId);
        return wxUserMapper.selectOne(wrapper);
    }
}
