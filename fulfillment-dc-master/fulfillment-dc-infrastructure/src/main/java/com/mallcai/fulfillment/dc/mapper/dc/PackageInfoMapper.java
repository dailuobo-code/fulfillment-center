package com.mallcai.fulfillment.dc.mapper.dc;

import com.mallcai.fulfillment.dc.valueobject.dc.PackageInfoDO;

import java.util.List;

/**
 * 批量插入包裹信息
 * @author bh.zhong
 * @date 2019/9/1 8:57 PM
 */
public interface PackageInfoMapper {

    /**
     * 插入订单详情信息
     *
     * @param packages
     * @return
     */
    int batchPackageInfo(List<PackageInfoDO> packages);

}
