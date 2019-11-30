package com.mallcai.manager.common.util;

import org.springframework.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bh.zhong
 * @date 2019/8/26 8:32 PM
 */
public class BeanCopierUtils {

    /**
     * 缓存转换过的bean
     */
    private final static Map<String, BeanCopier> beanCopierMap = new HashMap<>();

    /**
     * bean属性转换
     * @param source
     * @param target
     */
    public static void copyProperties(Object source,Object target) {
        String beanKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier;
        if (!beanCopierMap.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            beanCopierMap.put(beanKey, copier);
        } else {
            copier = beanCopierMap.get(beanKey);
        }
        copier.copy(source, target, null);
    }

    private static String generateKey(Class<?>class1, Class<?>class2){
        return class1.toString() + class2.toString();
    }

}
