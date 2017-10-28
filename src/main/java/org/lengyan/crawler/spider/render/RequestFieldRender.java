package org.lengyan.crawler.spider.render;

import java.lang.reflect.Field;
import java.util.Set;

import net.sf.cglib.beans.BeanMap;

import org.reflections.ReflectionUtils;
import org.lengyan.crawler.annotation.Request;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.response.HttpResponse;
import org.lengyan.crawler.spider.SpiderBean;

public class RequestFieldRender implements FieldRender {

	@Override
	@SuppressWarnings({"unchecked" })
	public void render(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean) {
		Set<Field> requestFields = ReflectionUtils.getAllFields(bean.getClass(), ReflectionUtils.withAnnotation(Request.class));
		for(Field field : requestFields) {
			beanMap.put(field.getName(), request);
		}
	}
	
}
