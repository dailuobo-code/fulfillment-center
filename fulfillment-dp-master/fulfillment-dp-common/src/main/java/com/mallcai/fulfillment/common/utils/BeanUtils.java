package com.mallcai.fulfillment.common.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * @className BeanUtils
 * @description beanUtils
 * @author zhanghao
 * @date 2019-04-25 11:17
 */
@Slf4j
public class BeanUtils {


	private static final DozerBeanMapper DOZER = new DozerBeanMapper();

	/**
	 *  支持深拷贝
	 * @param source
	 * @param destinationClass
	 * @return
	 */
	public static <T> T map(Object source, Class<T> destinationClass) {
		if (source == null) {
			return null;
		}
		return DOZER.map(source, destinationClass);
	}

	/**
	 * 支持深拷贝
	 * @param sourceList
	 * @param destinationClass
	 * @return
	 */
	public static <T> List<T> mapList(Collection<?> sourceList, Class<T> destinationClass) {
		if(CollectionUtils.isEmpty(sourceList)){
			return Lists.newArrayList();
		}
		List<T> destinationList = Lists.newArrayList();
		for (Object sourceObject : sourceList) {
			T destinationObject = DOZER.map(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		}
		return destinationList;
	}

	public static <T> T copy(Object source, Class<T> targetClass) {
		if (source == null) {
			return null;
		}
		BeanCopier copierOrder = BeanCopier.create(source.getClass(), targetClass, false);
		try {
			T t = targetClass.newInstance();
			copierOrder.copy(source, t, null);
			return t;
		} catch (InstantiationException | IllegalAccessException e) {
			log.error("bean copy error", e);
		}
		return null;


	}

	public static <T> List<T> copyList(Collection<?> sourceList, Class<?> sourceClass, Class<T> targetClass) {
		try {
			if (sourceList.isEmpty() || sourceList.size() == 0) {
				return null;
			}
			List<T> list = Lists.newArrayList();
			BeanCopier copierOrder = BeanCopier.create(sourceClass, targetClass, false);
			for (Object source : sourceList) {
				T t = targetClass.newInstance();
				copierOrder.copy(source, t, null);
				list.add(t);
			}
			return list;
		} catch (InstantiationException | IllegalAccessException e) {
			log.error("bean list copy error", e);
		}
		return null;
	}
}
