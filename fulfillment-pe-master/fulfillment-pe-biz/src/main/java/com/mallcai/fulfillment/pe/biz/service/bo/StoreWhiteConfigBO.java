package com.mallcai.fulfillment.pe.biz.service.bo;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 门店白名单配置
 * @author bh.zhong
 * @date 2019/8/23 7:22 PM
 */
@Data
public class StoreWhiteConfigBO {
    /**
     * 配置开启 true:开启 false:不开启
     */
    private Boolean openWhite;
    /**
     * 门店Id 白名单开启，门店Id生效
     */
    private List<StoreWhiteBO> stores;

    public static void main(String[] args) {
        StoreWhiteConfigBO configBO = new StoreWhiteConfigBO();
        List<StoreWhiteBO> stores = new ArrayList<>();
        StoreWhiteBO whiteBO = new StoreWhiteBO();
        whiteBO.setEffectTime("2018-08-30");
        whiteBO.setStoreId(111);
        //stores.add(whiteBO);
        configBO.setStores(stores);
        configBO.setOpenWhite(true);
        System.out.println(JSON.toJSONString(configBO));

    }

}
