package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.Data;

import java.util.List;

/**
 * @author bh.zhong
 * @date 2019/9/17 4:07 PM
 */
@Data
public class WhiteTypeBO {
    /**
     * 白名单门店信息
     */
    private List<Integer> storeWhites;

    /**
     * 白名单城市信息
     */
    private List<Integer> cityWhites;
}
