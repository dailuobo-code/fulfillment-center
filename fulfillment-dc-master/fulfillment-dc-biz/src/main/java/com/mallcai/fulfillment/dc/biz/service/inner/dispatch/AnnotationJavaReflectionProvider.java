package com.mallcai.fulfillment.dc.biz.service.inner.dispatch;

import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class AnnotationJavaReflectionProvider extends PureJavaReflectionProvider {

    /**
     * 写字段时验证字段可以被访问，且对于String类型需要进行trim
     */
    @Override
    public void writeField(Object object, String fieldName, Object value, Class definedIn) {
        Field field = fieldDictionary.field(object.getClass(), fieldName, definedIn);
        validateFieldAccess(field);
        try {
            if (value instanceof String) {
                field.set(object, ((String) value).trim());
            } else {
                field.set(object, value);
            }
        } catch (Exception e) {
            log.error("writeField system error,e:",e);
            return;
        }
    }
}