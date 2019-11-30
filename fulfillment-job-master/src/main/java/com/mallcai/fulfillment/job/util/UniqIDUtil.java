package com.mallcai.fulfillment.job.util;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;

/**
 * 日志工具类
 * @author bh.zhong
 * @date 2019/10/6 11:27 PM
 */
public final class UniqIDUtil {

    private static final int    IP            = ipInt();

    private static final int    PID           = pidInt();

    private static final String FORMAT_IP_PID = format(IP) + format(PID);

    private static final Object OBJECT        = new Object();

    private static int ipInt() {
        int result = 0;
        try {
            byte[] address = InetAddress.getLocalHost().getAddress();
            for (int i = 0; i < 4; i++) {
                result = (result << 8) - Byte.MIN_VALUE + (int) address[i];
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        return result;
    }

    private static int pidInt() {
        String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        int indexOf = jvmName.indexOf('@');
        long pid = System.currentTimeMillis();
        if (indexOf > 0) {
            pid = Long.valueOf(jvmName.substring(0, indexOf));
        }
        return (int) (pid >>> 8);
    }

    private static short SEQUENCE_NO = (short) 0;

    private static String format(int intval) {
        String formatted = Integer.toHexString(intval);
        StringBuffer buf = new StringBuffer("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    private static String format(short shortval) {
        String formatted = Integer.toHexString(shortval);
        StringBuffer buf = new StringBuffer("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }

    public static String create() {
        long curTimeMills;
        short sequenceNo;
        synchronized (OBJECT) {
            if (++SEQUENCE_NO < 0) {
                SEQUENCE_NO = 0;
            }
            sequenceNo = SEQUENCE_NO;
            curTimeMills = System.currentTimeMillis();
        }
        short highTime = (short) (curTimeMills >>> 32);
        StringBuilder sb = new StringBuilder(32);
        return sb.append(FORMAT_IP_PID).append(format(highTime)).append(format((int) curTimeMills))
            .append(format(sequenceNo)).toString();
    }

}
