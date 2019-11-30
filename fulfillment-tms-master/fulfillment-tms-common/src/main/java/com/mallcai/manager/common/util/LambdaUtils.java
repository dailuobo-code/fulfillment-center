package com.mallcai.manager.common.util;

import java.util.function.Supplier;

/**
 * LambdaUtils
 * @author admin
 * @date 2019-08-13 23:48:34
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
