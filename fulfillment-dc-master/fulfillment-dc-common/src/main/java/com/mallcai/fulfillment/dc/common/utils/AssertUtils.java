package com.mallcai.fulfillment.dc.common.utils;

import com.mallcai.fulfillment.dc.common.exception.BizException;
import com.mallcai.fulfillment.dc.common.exception.Errors;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * 断言工具类，用于参数校验等.
 * 所有方法的返回值都是待校验的值本身, 这样在赋值和校验的时候
 * 可以使用一句代码：
 * <code>
 * XXX.setName(AssertUtils.notNull(name));
 * <code/>
 *
 * @author bh.zhong
 * @date 2019年08月13日23:43:10
 */
public class AssertUtils {

    private static final String DEFAULT_MESSAGE = "参数错误";

    /**
     * 断言对象不为null
     *
     * @param value   待断言对象
     * @param message 如果断言失败抛出的异常信息
     * @param <T>
     * @return
     */
    public static <T> T assertNotNull(T value, String message) {
        if (null == value) {
            throw new BizException(message);
        }
        return value;
    }

    /**
     * 断言对象不为null
     *
     * @param value
     * @param <T>
     * @return
     */
    public static <T> T assertNotNull(T value) {
        return assertNotNull(value, DEFAULT_MESSAGE);
    }

    /**
     * 断言字符串不为null或空
     *
     * @param value
     * @param message
     * @return
     */
    public static String assertNotBlank(String value, String message) {
        if (StringUtils.isBlank(value)) {
            throw new BizException(message);
        }
        return value;
    }

    /**
     * 断言对象不为空
     * @return
     */
    public static void assertNotBlank(String... args){

        for (String arg : args) {

            if (StringUtils.isBlank(arg)){

                throw new BizException(Errors.FULFILLMENT_PARAMS_ERROR);
            }
        }
    }

    /**
     * 断言字符串不为null或空
     *
     * @param value
     * @return
     */
    public static String assertNotBlank(String value) {
        return assertNotBlank(value, DEFAULT_MESSAGE);
    }

    /**
     * 断言集合不null或空
     *
     * @param collection
     * @param message    断言失败抛出的异常信息
     * @param <T>        集合类型
     * @return 原集合
     */
    public static <T extends Collection> T assertNotEmpty(T collection, String message) {
        if (null == collection || collection.isEmpty()) {
            throw new BizException(message);
        }
        return collection;
    }

    /**
     * 断言集合不null或空
     *
     * @param collection
     * @param <T>        集合类型
     * @return 原集合
     */
    public static <T extends Collection> T assertNotEmpty(T collection) {
        return assertNotEmpty(collection, DEFAULT_MESSAGE);
    }

    /**
     * 断言true
     *
     * @param value
     * @param message
     */
    public static void assertTrue(boolean value, String message) {
        if (!value) {
            throw new BizException(message);
        }
    }

    /**
     * 断言false
     *
     * @param value
     * @param message
     */
    public static void assertFalse(boolean value, String message) {
        assertTrue(!value, message);
    }
}

