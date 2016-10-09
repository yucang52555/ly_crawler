package org.lengyan.crawler.spider.render.html;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.reflections.ReflectionUtils;

import org.lengyan.crawler.annotation.Image;
import org.lengyan.crawler.downloader.DownloaderContext;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.response.HttpResponse;
import org.lengyan.crawler.spider.SpiderBean;
import org.lengyan.crawler.spider.render.FieldRender;
import org.lengyan.crawler.spider.render.FieldRenderException;
import org.lengyan.crawler.utils.DownloadImage;

import net.sf.cglib.beans.BeanMap;

/**
 * 渲染@Image属性
 * 
 * @author huchengyi
 *
 */
public class ImageFieldRender implements FieldRender {

	@Override
	@SuppressWarnings("unchecked")
	public void render(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean) {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		Set<Field> imageFields = ReflectionUtils.getAllFields(bean.getClass(), ReflectionUtils.withAnnotation(Image.class));
		for (Field imageField : imageFields) {
			Object value = injectImageField(request, beanMap, bean, imageField);
			if(value != null) {
				fieldMap.put(imageField.getName(), value);
			}
		}
		beanMap.putAll(fieldMap);
	}

	private Object injectImageField(HttpRequest request, BeanMap beanMap, SpiderBean bean, Field field) {
		Object value = beanMap.get(field.getName());
		if(value == null) {
			return null;
		}
		String imgUrl = value.toString();
		if(StringUtils.isEmpty(imgUrl)) {
			return imgUrl;
		}
		Image image = field.getAnnotation(Image.class);
		String parentPath = image.download();
		if(StringUtils.isEmpty(parentPath)) {
			return imgUrl;
		}
		HttpResponse subReponse = null;
		try {
			String before =  StringUtils.substringBefore(imgUrl, "?");
			String last =  StringUtils.substringAfter(imgUrl, "?");
			String fileName = StringUtils.substringAfterLast(before, "/");
			if(StringUtils.isNotEmpty(last)) {
				last = URLEncoder.encode(last, "UTF-8");
				imgUrl = before + "?" + last;
			}
			HttpRequest subRequest = request.subRequest(imgUrl);
			subReponse = DownloaderContext.defaultDownload(subRequest);
			return DownloadImage.download(parentPath, fileName, subReponse.getRaw());
		} catch (Exception ex) {
			//throw new FieldRenderException(field, ex.getMessage(), ex);
			FieldRenderException.log(field, "download image error : " + imgUrl, ex);
			return imgUrl;
		} finally {
			if(subReponse != null) {
				subReponse.close();
			}
		}
	}
}
