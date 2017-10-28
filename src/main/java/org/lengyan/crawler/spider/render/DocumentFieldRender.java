package org.lengyan.crawler.spider.render;

import java.util.Map;

import org.dom4j.Document;
import org.lengyan.crawler.spider.SpiderBean;

/**
 * 文档字段解析
 * @author kangtiancheng
 * @date 2017年10月11日
 */
public interface DocumentFieldRender {
	
	public Map<String, Object> render(Document document, SpiderBean bean);
}
