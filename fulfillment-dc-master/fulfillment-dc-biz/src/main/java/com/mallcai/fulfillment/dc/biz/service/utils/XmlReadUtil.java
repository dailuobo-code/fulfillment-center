package com.mallcai.fulfillment.dc.biz.service.utils;

import com.mallcai.fulfillment.dc.biz.service.inner.dispatch.AnnotationJavaReflectionProvider;
import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-16 11:29:34
 */
@Slf4j
public class XmlReadUtil {

    /**
     * 读取xml并转成对象,通过文件路径
     *
     * @param classPath
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T readXmlToDto(String classPath, Class<T> clazz) {
        if (StringUtils.isBlank(classPath) || clazz == null) {
            return null;
        }
        String fileContent = readXmlToStr(classPath);
        return readFileContentToDto(fileContent, clazz);
    }

    public static String readXmlToStr(String classPath) {
        String fileContent = null;
        try {
            Resource resource = new ClassPathResource(classPath);
            fileContent = IOUtils.toString(resource.getInputStream(), "UTF-8");
        } catch (Exception e) {
            log.error(String.format("readXml-->getFile error,classPath=%s", classPath), e);
            throw new RuntimeException("readXml fail!");
        }
        return fileContent;
    }

    public static <T> T readFileContentToDto(String fileContent, Class<T> clazz) {
        XStream xStream = new XStream(new AnnotationJavaReflectionProvider());
        xStream.processAnnotations(clazz);
        T dto = null;
        try {
            dto = (T) xStream.fromXML(fileContent);
        } catch (Exception e) {
            log.error("readXml--> fromXML error, className={}", clazz.getName(), e);
            throw new RuntimeException("readXmlToDto fail!");
        }
        log.info("readXml--> read complete! className={}", clazz.getName());
        return dto;
    }
}
