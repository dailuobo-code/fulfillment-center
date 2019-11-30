package com.mallcai.fulfillment.common.utils;

import org.slf4j.Logger;

import java.util.function.Supplier;

/**
 * <pre>
 *   利用lambda表达式延迟计算特性实现延迟打印日志的功能
 * </pre>
 *
 * @author admin
 * @date 2019-08-13 23:48:51
 */
public interface LazyLogs {

    /**
     * debug
     *
     * @param logger
     * @param message
     */
    static void debug(Logger logger, Supplier<String> message) {
        if (logger.isDebugEnabled()) {
            logger.debug(message.get());
        }
    }

    /**
     * debug
     *
     * @param logger
     * @param format
     * @param params
     */
    static void debug(Logger logger, String format, Supplier<?>... params) {
        if (logger.isDebugEnabled()) {
            logger.debug(format, LambdaUtils.getAll(params));
        }
    }

    /**
     * debug
     *
     * @param logger
     * @param format
     * @param message
     */
    static void debug(Logger logger, String format, Object... message) {
        if (logger.isDebugEnabled()) {
            logger.debug(format, message);
        }
    }

    /**
     * info
     *
     * @param logger
     * @param message
     */
    static void info(Logger logger, Supplier<String> message) {
        if (logger.isInfoEnabled()) {
            logger.info(message.get());
        }
    }

    /**
     * info
     *
     * @param logger
     * @param format
     * @param params
     */
    static void info(Logger logger, String format, Supplier<?>... params) {
        if (logger.isInfoEnabled()) {
            logger.info(format, LambdaUtils.getAll(params));
        }
    }

    /**
     * info
     *
     * @param logger
     * @param format
     * @param params
     */
    static void info(Logger logger, String format, Object... params) {
        if (logger.isInfoEnabled()) {
            logger.info(format, params);
        }
    }

    /**
     * warn
     *
     * @param logger
     * @param message
     */
    static void warn(Logger logger, Supplier<String> message) {
        if (logger.isWarnEnabled()) {
            logger.warn(message.get());
        }
    }

    /**
     * warn
     *
     * @param logger
     * @param format
     * @param params
     */
    static void warn(Logger logger, String format, Supplier<?>... params) {
        if (logger.isWarnEnabled()) {
            logger.warn(format, LambdaUtils.getAll(params));
        }
    }

    /**
     * warn
     *
     * @param logger
     * @param format
     * @param params
     */
    static void warn(Logger logger, String format, Object... params) {
        if (logger.isWarnEnabled()) {
            logger.warn(format, params);
        }
    }

    /**
     * error
     *
     * @param logger
     * @param message
     */
    static void error(Logger logger, Supplier<String> message) {
        if (logger.isErrorEnabled()) {
            logger.error(message.get());
        }
    }

    /**
     * error
     *
     * @param logger
     * @param format
     * @param params
     */
    static void error(Logger logger, String format, Supplier<?>... params) {
        if (logger.isErrorEnabled()) {
            logger.error(format, LambdaUtils.getAll(params));
        }
    }

    /**
     * error
     *
     * @param logger
     * @param format
     * @param params
     */
    static void error(Logger logger, String format, Object... params) {
        if (logger.isErrorEnabled()) {
            logger.error(format, params);
        }
    }

    /**
     * error
     *
     * @param logger
     * @param format
     * @param e
     * @param params
     */
    static void error(Logger logger, String format, Throwable e, Object... params) {
        if (logger.isErrorEnabled()) {
            logger.error(format, params, e);
        }
    }

    /**
     * error
     *
     * @param logger
     * @param format
     * @param e
     * @param params
     */
    static void error(Logger logger, String format, Throwable e, Supplier<?>... params) {
        if (logger.isErrorEnabled()) {
            logger.error(format, LambdaUtils.getAll(params), e);
        }
    }

}
