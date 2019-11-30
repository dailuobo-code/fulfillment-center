package com.mallcai.fulfillment.dp.infrastructure.mongo;


import com.mallcai.trade.common.mongo.vo.MongoOrder;
import org.springframework.stereotype.Repository;

/**
 * @author wangtao
 * @description: TODO
 * @title: OrderMongoDAO
 * @date 2019-05-10 17:00
 */
@Repository
public class OrderMongoDAO extends AbstractMongoBaseDAO<MongoOrder> {

  @Override
  public String getCollectionName() {
    return "tbl_order";
  }


}
