package com.kunkun.util;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

@SuppressWarnings("unused")
public class FillBeanUtil {
	public static <T> T fillBean(HttpServletRequest request,Class<T> clazz){
		try {
			T bean = clazz.newInstance();
			BeanUtils.copyProperties(bean, request.getParameterMap());
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(); 
		}
	}

}
