package com.mallcai.fulfillment.pe.dependency.client;

import com.mallcai.service.user.vo.user.AppUserInfo;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-28 21:50:26
 */
public interface UserServiceClient {

    AppUserInfo getUserInfoById(Integer userId);
}
