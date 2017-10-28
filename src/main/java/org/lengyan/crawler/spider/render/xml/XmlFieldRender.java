package org.lengyan.crawler.spider.render.xml;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.lengyan.crawler.annotation.xml.XmlField;
import org.lengyan.crawler.spider.SpiderBean;
import org.lengyan.crawler.spider.render.DocumentFieldRender;
import org.lengyan.crawler.spider.render.FieldRenderException;
import org.lengyan.crawler.utils.ReflectUtils;
import org.reflections.ReflectionUtils;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class XmlFieldRender implements DocumentFieldRender {

	@Override
	public Map<String, Object> render(Document document, SpiderBean bean) {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		Set<Field> xmlFields = ReflectionUtils.getAllFields(bean.getClass(), ReflectionUtils.withAnnotation(XmlField.class));
		for (Field xmlField : xmlFields) {
			Object value = injectXmlField(document, xmlField, bean.getClass());
			if(value != null) {
				fieldMap.put(xmlField.getName(), value);
			}
		}
		return fieldMap;
	}

	private Object injectXmlField(Document document, Field field, Class<? extends SpiderBean> clazz) {
		XmlField xmlField = field.getAnnotation(XmlField.class);
		String xPath = xmlField.xPath();
		Class<?> type = field.getType();// 属性的类
		boolean isList = ReflectUtils.haveSuperType(type, List.class);// 是List类型
		if (isList) {
			Type genericType = field.getGenericType();// 获得包含泛型的类型
			Class genericClass = ReflectUtils.getGenericClass(genericType, 0);// 泛型类
			if (ReflectUtils.haveSuperType(genericClass, SpiderBean.class)) {
				// List<spiderBean>
				return document.selectNodes(xPath);
			} else {
				// List<Object>
				try {
					return document.selectNodes(xPath);
				} catch (Exception ex) {
					FieldRenderException.log(field, document.asXML(), ex);
				}
			}
		} else {
			if (ReflectUtils.haveSuperType(type, SpiderBean.class)) {
				// SpiderBean
				return document.selectSingleNode(xPath).getText();
			} else {
				// Object
				try {
					return document.selectSingleNode(xPath).getText();
				} catch (Exception ex) {
					FieldRenderException.log(field, document.asXML(), ex);
				}
			}
		}
		return null;
	}

}
