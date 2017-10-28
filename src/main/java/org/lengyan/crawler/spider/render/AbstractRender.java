package org.lengyan.crawler.spider.render;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.lengyan.crawler.annotation.html.FieldRenderName;
import org.lengyan.crawler.annotation.html.Href;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.response.HttpResponse;
import org.lengyan.crawler.scheduler.DeriveSchedulerContext;
import org.lengyan.crawler.spider.SpiderBean;
import org.lengyan.crawler.utils.ReflectUtils;
import org.reflections.ReflectionUtils;

import net.sf.cglib.beans.BeanMap;

/**
 * render抽象方法，主要包括注入基本的属性和自定义属性注入。将特定的html、json、xml注入放入实现类
 * 
 * @author huchengyi
 *
 */
public abstract class AbstractRender implements Render {
	
	private static Log log = LogFactory.getLog(AbstractRender.class);

	/**
	 * request请求的注入
	 */
	private RequestFieldRender requestFieldRender;

	/**
	 * request参数的注入
	 */
	private RequestParameterFieldRender requestParameterFieldRender;

	/**
	 * 自定义注入
	 */
	private CustomFieldRenderFactory customFieldRenderFactory;

	public AbstractRender() {
		this.requestFieldRender = new RequestFieldRender();
		this.requestParameterFieldRender = new RequestParameterFieldRender();
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public SpiderBean inject(Class<? extends SpiderBean> clazz, HttpRequest request, HttpResponse response) {
		try {
			SpiderBean bean = clazz.newInstance();
			BeanMap beanMap = BeanMap.create(bean);
			requestFieldRender.render(request, response, beanMap, bean);
			requestParameterFieldRender.render(request, response, beanMap, bean);
			fieldRender(request, response, beanMap, bean);
			Set<Field> customFields = ReflectionUtils.getAllFields(bean.getClass(),	ReflectionUtils.withAnnotation(FieldRenderName.class));
			for (Field customField : customFields) {
				FieldRenderName fieldRender = customField.getAnnotation(FieldRenderName.class);
				String name = fieldRender.value();
				CustomFieldRender customFieldRender = customFieldRenderFactory.getCustomFieldRender(name);
				if (customFieldRender != null) {
					customFieldRender.render(request, response, beanMap, bean, customField);
				}
			}
			requests(request, bean);
			return bean;
		} catch(Exception ex) {
			//throw new RenderException(ex.getMessage(), clazz);
			log.error("instance SpiderBean error", ex);
			return null;
		}
	}
	
	@Override
	public SpiderBean inject(Class<? extends SpiderBean> clazz, Document document) {
		try {
			SpiderBean bean = clazz.newInstance();
			Map<String, Object> beanMap = fieldRender(document, bean);
//			Set<Field> customFields = ReflectionUtils.getAllFields(bean.getClass(),	ReflectionUtils.withAnnotation(FieldRenderName.class));
//			for (Field customField : customFields) {
//				FieldRenderName fieldRender = customField.getAnnotation(FieldRenderName.class);
//				String name = fieldRender.value();
//				CustomFieldRender customFieldRender = customFieldRenderFactory.getCustomFieldRender(name);
//				if (customFieldRender != null) {
//					customFieldRender.render(document, beanMap, bean, customField);
//				}
//			}
			for (Iterator<String> iterator = beanMap.keySet().iterator(); iterator.hasNext();) {
				String key = iterator.next();
				bean = (SpiderBean) beanMap.get(key);
			}
			return bean;
		} catch(Exception ex) {
			//throw new RenderException(ex.getMessage(), clazz);
			log.error("instance SpiderBean error", ex);
			return null;
		}
	}

	public abstract void fieldRender(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean);
	
	public abstract Map<String, Object> fieldRender(Document document, SpiderBean bean);

	/**
	 * 需要继续抓取的请求
	 */
	@Override
	@SuppressWarnings({ "unchecked" })
	public void requests(HttpRequest request, SpiderBean bean) {
		BeanMap beanMap = BeanMap.create(bean);
		Set<Field> hrefFields = ReflectionUtils.getAllFields(bean.getClass(),
				ReflectionUtils.withAnnotation(Href.class));
		for (Field hrefField : hrefFields) {
			Href href = hrefField.getAnnotation(Href.class);
			if (href.click()) {
				Object o = beanMap.get(hrefField.getName());
				if (o == null) {
					continue;
				}
				boolean isList = ReflectUtils.haveSuperType(o.getClass(), List.class);// 是List类型
				if (isList) {
					List<String> list = (List<String>) o;
					for (String url : list) {
						if (StringUtils.isNotEmpty(url)) {
							DeriveSchedulerContext.into(request.subRequest(url));
						}
					}
				} else {
					String url = (String) o;
					if (StringUtils.isNotEmpty(url)) {
						DeriveSchedulerContext.into(request.subRequest(url));
					}
				}
			}
		}
	}

	public void setCustomFieldRenderFactory(CustomFieldRenderFactory customFieldRenderFactory) {
		this.customFieldRenderFactory = customFieldRenderFactory;
	}

}
