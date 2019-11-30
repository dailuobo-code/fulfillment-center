package com.mallcai.fulfillment.dp.api;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dp.api.request.MilkPickUpDTO;
import com.mallcai.fulfillment.dp.api.request.MilkQueryDTO;
import com.mallcai.fulfillment.dp.api.response.MilkResultDTO;

/**
 * @description 取奶服务
 * @author zhanghao
 * @date 2019-08-15 20:20:22
 */
public interface MilkService {

    /**
     * 查询列表.
     * @param milkQueryDTO
     * @return
     */
    PlainResult<MilkResultDTO> queryMilkList(MilkQueryDTO milkQueryDTO);

    /**
     * 取奶.
     * @param milkPickUpDTO
     * @return
     */
    PlainResult<String> pickup(MilkPickUpDTO milkPickUpDTO);
}
