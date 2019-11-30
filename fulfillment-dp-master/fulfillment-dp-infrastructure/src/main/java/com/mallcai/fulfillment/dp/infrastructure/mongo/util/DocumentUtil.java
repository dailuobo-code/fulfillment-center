package com.mallcai.fulfillment.dp.infrastructure.mongo.util;

import com.mallcai.backend.common.utils.UnderlineCamelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.bson.Document;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * @author wangtao
 * @description: mongodb 对象转换类
 * @title: DocumentUtil
 * @date 2019-05-13 10:21
 */
@Slf4j
public class DocumentUtil {

  /**
   * 将document转为bean对象,针对document中的key是驼峰标识
   * @param docuemnt	将要转化的document
   * @param classes	需要转化成的对象类型
   * @return
   */
  public static <T> T transDocument2BeanWithOutConversionCamel(Document docuemnt, Class<T> classes){
    return transDocument2Bean(docuemnt, classes, Boolean.FALSE);
  }

  /**
   * 将document转为bean对象,针对document中的key是下划线标识
   * @param docuemnt	将要转化的document
   * @param classes	需要转化成的对象类型
   * @return
   */
  public static <T> T transDocument2BeanWithConversionCamel(Document docuemnt, Class<T> classes){
    return transDocument2Bean(docuemnt, classes, Boolean.TRUE);
  }

  /**
   * 将document转为bean对象
   * @param docuemnt		将要转化的document
   * @param classes		需要转化成的对象类型
   * @param needToCamel	是否需要做驼峰转化[当document中的key为下划线标识时，需要为true]
   * @return
   */
  private static <T> T transDocument2Bean(Document docuemnt, Class<T> classes, Boolean needToCamel){
    try {
      T t = classes.newInstance();
      return transDocument2Bean(docuemnt, t, needToCamel);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }



  /**
   * 将document转为bean对象
   * @param document		将要转化的document
   * @param t				转化后的对象
   * @param needToCamel	是否需要做驼峰转化[当document中的key为下划线标识时，需要为true]
   * @return
   */
  private static <T> T transDocument2Bean(Document document, T t, Boolean needToCamel){
    try {
      Iterator<Entry<String, Object>> iter = document.entrySet().iterator();
      while(iter.hasNext()){
        Entry<String, Object> entry = iter.next();
        if(needToCamel){
          BeanUtils.setProperty(t, UnderlineCamelUtils.underlineToCamel(entry.getKey()), entry.getValue());
        }else{
          BeanUtils.setProperty(t, entry.getKey(), entry.getValue());
        }
      }
      return t;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }


  /**
   * 将bean转为document对象,针对document中的key是下划线标识
   * @param obj 需要转化的bean
   */
  public static Document transBean2DocumentWithConversionUnderLine(Object obj){
    return transBean2Document(obj, Boolean.TRUE);
  }


  /**
   * 将bean转为document对象,针对document中的key是驼峰标识
   * @param obj 需要转化的bean
   */
  public static Document transBean2DocumentWithOutConversionUnderLine(Object obj){
    return transBean2Document(obj, Boolean.FALSE);
  }



  /**
   * 将obj转为document对象
   * @param obj				将要转化的对象
   * @param needToUnderLine	是否需要做驼峰转化[当document中的key为下划线标识时，需要为true]
   * @return
   */
  private static Document transBean2Document(Object obj, Boolean needToUnderLine){
    if (obj == null) {
      return null;
    }
    Document document = new Document();
    try {
      PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(obj);
      for (PropertyDescriptor property : propertyDescriptors){
        String key = property.getName();

        // 过滤class属性
        if (!"class".equals(key)) {
          // 得到property对应的getter方法
          Method getter = property.getReadMethod();
          Object value = getter.invoke(obj);
          if(value != null){
            if(needToUnderLine){
              document.put(UnderlineCamelUtils.camelToUnderline(key), value);
            }else{
              document.put(key, value);
            }
          }
        }
      }
      return document;
    } catch (Exception e) {
      log.error("transBean2Map Error ", e);
      return null;
    }
  }


  /**
   * 获取对象的property数据
   * @param object 对象
   * @return
   */
  public static PropertyDescriptor[] getPropertyDescriptors(Object object){
    BeanInfo targetBeanInfo;
    try {
      targetBeanInfo = Introspector.getBeanInfo(object.getClass());
      return targetBeanInfo.getPropertyDescriptors();
    } catch (IntrospectionException e) {
      log.error(e.getMessage(), e);
      return null;
    }

  }
}
