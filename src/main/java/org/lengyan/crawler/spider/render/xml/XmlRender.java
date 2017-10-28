package org.lengyan.crawler.spider.render.xml;

import java.util.Map;

import org.dom4j.Document;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.response.HttpResponse;
import org.lengyan.crawler.spider.SpiderBean;
import org.lengyan.crawler.spider.render.AbstractRender;

import net.sf.cglib.beans.BeanMap;

public class XmlRender extends AbstractRender {
	
	private XmlFieldRender xmlFieldRender;
	
	public XmlRender() {
		super();
		this.xmlFieldRender = new XmlFieldRender();
	}

	@Override
	public void fieldRender(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean) {
		
	}

	@Override
	public Map<String, Object> fieldRender(Document document, SpiderBean bean) {
//		Set<Field> requestFields = ReflectionUtils.getAllFields(bean.getClass(), ReflectionUtils.withAnnotation(Request.class));
//		for(Field field : requestFields) {
//			beanMap.put(field.getName(), document);
//		}
//		Map<String, Object> fieldMap = new HashMap<String, Object>();
//		Set<Field> requestParameterFields = ReflectionUtils.getAllFields(bean.getClass(), ReflectionUtils.withAnnotation(RequestParameter.class));
//		for(Field field : requestParameterFields) {
//			RequestParameter requestParameter = field.getAnnotation(RequestParameter.class);
//			String key = requestParameter.value();
//			if(StringUtils.isEmpty(key)) {
//				key = field.getName();
//			}
//			String src = document.getParameter(key);
//			try {
//				Object value = Conversion.getValue(field.getType(), src);
//				fieldMap.put(field.getName(), value);
//			} catch(Exception ex) {
//				//throw new FieldRenderException(field, src, ex);
//				FieldRenderException.log(field, src, ex);
//			}
//		}
//		beanMap.putAll(fieldMap);
		return xmlFieldRender.render(document, bean);
	}
	
}
