package com.mallcai.fulfillment.pe.biz.service.util;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.lang.reflect.InvocationTargetException;

public class BaseBeanUtil {

    public static <T> T convertObject(Object src, Class<? extends T> dstClz) {
        if (src == null) {
            return null;
        }
        try {
            T newObject = dstClz.newInstance();
            BeanUtils.copyProperties(src,newObject);
            return newObject;
        } catch (InstantiationException  | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void copyProperties(Object src, Object dst){
        try {
            BeanUtils.copyProperties(src, dst);
        } catch ( BeansException  e) {
            throw new RuntimeException(e);
        }
    }

}
