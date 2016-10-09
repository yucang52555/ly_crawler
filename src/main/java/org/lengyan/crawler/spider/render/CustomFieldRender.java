package org.lengyan.crawler.spider.render;

import java.lang.reflect.Field;

import net.sf.cglib.beans.BeanMap;

import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.response.HttpResponse;
import org.lengyan.crawler.spider.SpiderBean;

/**
 * 属性的渲染有时会较复杂，不能用上述注解描述，gecco爬虫支持属性渲染的自定义方式，自定义渲染器实现CustomFieldRender接口，并定义属性渲染器名称。
 * 
 * @author huchengyi
 *
 */
public interface CustomFieldRender {
	
	/**
	 * 
	 * @param request HttpRequest
	 * @param response HttpResponse
	 * @param beanMap 将Field放入SpiderBean
	 * @param bean 已经注入后的SpiderBean
	 * @param field 需要注入的Field
	 */
	public void render(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean, Field field);

}
