package org.lengyan.crawler.dynamic;

import org.apache.commons.lang3.RandomStringUtils;

public class DynamicGecco {
	
	public static JavassistDynamicBean html(String htmlBeanName) {
		return new JavassistDynamicBean(htmlBeanName, JavassistDynamicBean.HtmlBean);
	}
	
	public static JavassistDynamicBean json(String jsonBeanName) {
		return new JavassistDynamicBean(jsonBeanName, JavassistDynamicBean.JsonBean);
	}
	
	public static JavassistDynamicBean html() {
		return new JavassistDynamicBean("org.lengyan.crawler.dynamic.HtlmBean"+RandomStringUtils.randomAlphabetic(6)+System.nanoTime(), JavassistDynamicBean.HtmlBean);
	}
	
	public static JavassistDynamicBean json() {
		return new JavassistDynamicBean("org.lengyan.crawler.dynamic.JsonBean"+RandomStringUtils.randomAlphabetic(6)+System.nanoTime(), JavassistDynamicBean.JsonBean);
	}
	
	public static void unregister(Class<?> clazz) {
		new JavassistDynamicBean(clazz.getName()).unloadClass();;
	}
}