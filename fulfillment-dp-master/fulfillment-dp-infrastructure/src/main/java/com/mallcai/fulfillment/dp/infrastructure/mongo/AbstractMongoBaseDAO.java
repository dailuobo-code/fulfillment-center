package com.mallcai.fulfillment.dp.infrastructure.mongo;

import com.mallcai.backend.common.cache.mongo.utils.BeanMapUtils;
import com.mallcai.fulfillment.dp.infrastructure.mongo.util.DocumentUtil;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangtao
 * @description: mongodb 抽象基础类
 * @title: AbstractMongoBaseDAO
 * @date 2019-05-10 16:56
 */
@Slf4j
public abstract class AbstractMongoBaseDAO<T> {

  private MongoClient mongoClient;

  @Value("${mongo.uri}")
  private String url;

  @Value("${mongo.dbname}")
  private String mongoDb;

  /***
   * getCollectionName
   * @return
   */
  public abstract String getCollectionName();

  /**
   * 更新第一条记录
   *
   * @param isFieldUpdate 是否只更新部分字段
   */
  public Long update(T set, T where, boolean isFieldUpdate) {
    Document sourceDocument = DocumentUtil.transBean2DocumentWithConversionUnderLine(where);
    Document targetDocument = DocumentUtil.transBean2DocumentWithConversionUnderLine(set);
    if (isFieldUpdate) {
      return getCollection().updateOne(sourceDocument, new Document("$set", targetDocument))
          .getModifiedCount();
    } else {
      return getCollection().updateOne(sourceDocument, targetDocument).getModifiedCount();
    }
  }


  @SuppressWarnings("unchecked")
  public void batchInsert(List<T> tList) {
    try {
      List<Document> documentList = new ArrayList<>();
      for (T t : tList) {
        documentList.add(DocumentUtil.transBean2DocumentWithConversionUnderLine(t));
      }
      getCollection().insertMany(documentList);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  public void batchDelete(String fileName, String value) {
    try {
      getCollection().deleteMany(Filters.eq(fileName, value));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }


  public MongoClient getMongoClient(String content) {
    if (mongoClient != null) {
      return mongoClient;
    }

    Builder builder = MongoClientOptions.builder();
    builder.maxConnectionIdleTime(6000);
    builder.connectionsPerHost(10);
    builder.minConnectionsPerHost(5);
    builder.threadsAllowedToBlockForConnectionMultiplier(5);
    builder.maxWaitTime(120000);
    builder.connectTimeout(10000);
    builder.socketTimeout(0);
    MongoClientURI mongoClientUri = new MongoClientURI(content, builder);

    mongoClient = new MongoClient(mongoClientUri);
    return mongoClient;
  }

  private MongoCollection getCollection() {
    return getMongoClient(url).getDatabase(mongoDb)
        .getCollection(getCollectionName());
  }


  public void delOrder(String orderId, String updateTime) {
    Bson sourceDoucment = Filters.eq("order_id", orderId);
    Document targetDocument = new Document("is_del", "1");
    targetDocument.append("update_time", updateTime);
    getCollection().updateOne(sourceDoucment, new Document("$set", targetDocument));
  }

  public <E> List<E> find(Class<E> e, Bson query) {
    MongoCursor<Document> resultDocuments = getCollection().find(query).iterator();
    List<E> result = new ArrayList<>();
    while (resultDocuments.hasNext()) {
      result.add(
          BeanMapUtils.transDocument2BeanWithConversionCamel(resultDocuments.next(), e));
    }
    return result;
  }
}
