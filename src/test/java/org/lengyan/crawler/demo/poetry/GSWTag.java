package org.lengyan.crawler.demo.poetry;

import java.util.List;

import org.lengyan.crawler.GeccoEngine;
import org.lengyan.crawler.annotation.Gecco;
import org.lengyan.crawler.annotation.Request;
import org.lengyan.crawler.annotation.html.HtmlField;
import org.lengyan.crawler.request.HttpGetRequest;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.spider.HtmlBean;
import org.lengyan.crawler.store.model.po.Tag;

/**
 * 古诗文-标签
 * @author kangtiancheng
 * @date 2017年6月20日
 */
@Gecco(matchUrl="http://so.gushiwen.org/shiwen/tags.aspx", pipelines={"tagListPipeline"})
public class GSWTag implements HtmlBean{

	private static final long serialVersionUID = -7235477047148963880L;

	@Request
	private HttpRequest request;
	
	@HtmlField(cssPath=".main3 .left .sons .bookcont span")
	private List<Tag> tags;
	
	public List<Tag> getTags() {
		return tags;
	}
	
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public static void main(String[] args) {
		HttpGetRequest start = new HttpGetRequest("http://so.gushiwen.org/shiwen/tags.aspx");
		start.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		start.addHeader("Accept-Encoding", "gzip, deflate, sdch");
		start.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		start.addHeader("Cache-Control", "no-cache");
		start.addHeader("Connection", "keep-alive");
		start.addHeader("Host", "so.gushiwen.org");
		start.addHeader("Pragma", "no-cache");
		start.addHeader("Referer", "http://so.gushiwen.org/authors/");
		start.addHeader("Upgrade-Insecure-Requests", "1");
		start.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
		
		start.addCookie("__cfduid", "d0a1a830bbdf6231cfd6cd5535db45a7c1497841525");
		start.addCookie("ASP.NET_SessionId", "lr1daywv50cc231ml1f305jm");
		
		GeccoEngine.create()
		.classpath("org.lengyan.crawler.demo.poetry.GSWTag")
		.start(start)
		.interval(5000)
		.run();
	}
}
