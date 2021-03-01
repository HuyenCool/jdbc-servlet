package com.laptrinhjavaweb.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class FormUtil {
		@SuppressWarnings({ "unchecked", "deprecation" })
		public static  <T> T toModel(Class<T> clazz, HttpServletRequest request) {
			T object = null;
			try {
				
					object = clazz.newInstance();
					BeanUtils.populate(object, request.getParameterMap());
				
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
				System.out.print(e.getMessage());
			}
			return object;
		}
		
	
}
