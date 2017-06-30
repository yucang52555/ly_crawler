package org.lengyan.crawler.store.helper;

import java.util.ArrayList;
import java.util.List;

import org.lengyan.crawler.request.HttpGetRequest;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.store.model.po.Tag;
import org.lengyan.crawler.store.service.ITagService;
import org.lengyan.crawler.store.service.impl.TagServiceImpl;
import org.lengyan.crawler.utils.CommonUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 辅助类
 * @author kangtiancheng
 * @date 2017年6月24日
 */
public class HttpRequestHelper {
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");
	
	public List<HttpRequest> buildTagHttpRequests() {
		List<HttpRequest> httpRequests = new ArrayList<HttpRequest>();
		//保存到数据库
		context.start();
		ITagService tagService = (TagServiceImpl)context.getBean("tagService");
		List<Tag> tags = tagService.selectAllTags();
		for (int i = 0; i < CommonUtils.sizeOf(tags); i++) {
			Tag tag = tags.get(i);
			HttpGetRequest start = new HttpGetRequest("http://so.gushiwen.org/type.aspx?p=1&t=" + tag.getTagName());
			start.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			start.addHeader("Accept-Encoding", "gzip, deflate, sdch");
			start.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
			start.addHeader("Cache-Control", "no-cache");
			start.addHeader("Connection", "keep-alive");
			start.addHeader("Host", "so.gushiwen.org");
			start.addHeader("Pragma", "no-cache");
			start.addHeader("Referer", "http://so.gushiwen.org/type.aspx?p=2");
			start.addHeader("Upgrade-Insecure-Requests", "1");
			start.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
			start.addCookie("__cfduid", "d0a1a830bbdf6231cfd6cd5535db45a7c1497841525");
			start.addCookie("ASP.NET_SessionId", "lr1daywv50cc231ml1f305jm");
			httpRequests.add(start);
		}
		return httpRequests;
	}
}
