package org.lengyan.crawler.spider.render;

import org.dom4j.Document;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.response.HttpResponse;
import org.lengyan.crawler.spider.SpiderBean;

/**
 * 渲染器，将下载下来的各种格式内容对象化，并将需要继续抓取的链接抽取出来
 * 
 * @author huchengyi
 *
 */
public interface Render {

	public SpiderBean inject(Class<? extends SpiderBean> clazz, HttpRequest request, HttpResponse response);
	
	public void requests(HttpRequest request, SpiderBean bean);

	public SpiderBean inject(Class<? extends SpiderBean> clazz, Document document);
}
