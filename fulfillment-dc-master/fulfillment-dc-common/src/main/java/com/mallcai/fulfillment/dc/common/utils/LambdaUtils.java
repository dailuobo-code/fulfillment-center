package com.mallcai.fulfillment.dc.common.utils;

import java.util.function.Supplier;

/**
 * LambdaUtils
 *
 * @author bh.zhong
 * @date 2019年08月13日23:43:10
 */
public class LambdaUtils {

    public static Object[] getAll(final Supplier<?>... suppliers) {
        if (suppliers == null) {
            return null;
        }

        final Object[] result = new Object[suppliers.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = suppliers[i].get();
        }
        return result;
    }

}
