package com.mallcai.fulfillment.pe.dependency.client;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import com.mallcai.fulfillment.pe.common.exception.IErrors;
import com.mallcai.service.user.api.IUserService;
import com.mallcai.service.user.vo.user.AppUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-28 21:50:37
 */
@Slf4j
@Service
public class UserServiceClientImpl implements UserServiceClient {

    @Resource
    private IUserService iUserService;

    @Override
    public AppUserInfo getUserInfoById(Integer userId) {

        log.info("根据userId查询用户信息开始,userId:{}", userId);
        AppUserInfo appUserInfo;
        try {
            appUserInfo = iUserService.getUserInfoById(userId);
        } catch (Exception e) {

            log.error("根据userId查询用户信息异常,userId:{}", userId, e);
            throw new BizException(Errors.DEPENDENCY_SERVICE_EXCEPTION);
        }
        log.info("根据userId查询用户信息结束,userId:{},result:{}", userId, JSON.toJSONString(appUserInfo));

        if (appUserInfo == null || appUserInfo.getPhone() == null) {

            log.error("根据userId查询用户信息为空,userId:{}", userId);
            throw new BizException(Errors.DEPENDENCY_DATA_ERROR);
        }

        return appUserInfo;
    }
}
