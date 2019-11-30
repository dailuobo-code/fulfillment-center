package com.mallcai.manager.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-27 18:18:16
 */
@Slf4j
public class ObjectCopyUtil {

    public static Object deeplyCopy(Serializable obj) {
        try {
            return bytes2object(object2bytes(obj));
        } catch (Exception e) {
            log.error("序列化异常", e);
            throw new RuntimeException();
        }
    }

    public static byte[] object2bytes(Serializable obj) {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);

            return baos.toByteArray();
        } catch (Exception e) {
            log.error("对象写入异常", e);
            throw new RuntimeException();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (Exception e) {
                log.error("流关闭异常", e);
            }
        }
    }

    public static Object bytes2object(byte[] bytes) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            log.error("对象读取异常", e);
            throw new RuntimeException();
        } finally {
            try {
                if (bais != null) {
                    bais.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (Exception e) {
                log.error("流关闭异常", e);
            }
        }
    }
}
