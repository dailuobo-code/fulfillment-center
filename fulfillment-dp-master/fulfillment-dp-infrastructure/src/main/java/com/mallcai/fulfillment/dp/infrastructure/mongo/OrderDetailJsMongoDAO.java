package com.mallcai.fulfillment.dp.infrastructure.mongo;

import com.mallcai.trade.common.mongo.vo.MongoOrderDetailjs;
import org.springframework.stereotype.Repository;

/**
 * @author yl
 * @description: TODO
 * @title: OrderDetailJsMongoDAO
 * @date 2019-06-20
 */
@Repository
public class OrderDetailJsMongoDAO extends AbstractMongoBaseDAO<MongoOrderDetailjs> {

  @Override
  public String getCollectionName() {
    return "tbl_order_detail_js";
  }
}
