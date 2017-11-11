package org.lengyan.crawler.html.poetry;

import java.util.List;

import org.lengyan.crawler.GeccoEngine;
import org.lengyan.crawler.annotation.Gecco;
import org.lengyan.crawler.annotation.Request;
import org.lengyan.crawler.annotation.RequestParameter;
import org.lengyan.crawler.annotation.html.HtmlField;
import org.lengyan.crawler.request.HttpGetRequest;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.spider.HtmlBean;
import org.lengyan.crawler.store.model.po.htmlpo.gushiwen.Author;

/**
 * 古诗文-作者
 * @author kangtiancheng
 * @date 2017年6月19日
 */
@Gecco(matchUrl="http://so.gushiwen.org/authors/Default.aspx?p={page}&c=", pipelines={"authorListPipeline"})
public class GSWAuthor implements HtmlBean{

	private static final long serialVersionUID = 295833399632474658L;
	
	@Request
	private HttpRequest request;
	
	@RequestParameter
	private Integer page;
	
	@HtmlField(cssPath="#main3 .sonspic")
	private List<Author> authors;
	
	public Integer getPage() {
		return page;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public List<Author> getAuthors() {
		return authors;
	}
	
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public static void main(String[] args) {
		HttpGetRequest start = new HttpGetRequest("http://so.gushiwen.org/authors/Default.aspx?p=1&c=");
		start.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		start.addHeader("Accept-Encoding", "gzip, deflate, sdch");
		start.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		start.addHeader("Cache-Control", "no-cache");
		start.addHeader("Connection", "keep-alive");
		start.addHeader("Host", "m.gushiwen.org");
		start.addHeader("Pragma", "no-cache");
		start.addHeader("Referer", "http://so.gushiwen.org/authors/Default.aspx?p=1&c=");
		start.addHeader("Upgrade-Insecure-Requests", "1");
		
		start.addCookie("__cfduid", "d0a1a830bbdf6231cfd6cd5535db45a7c1497841525");
		start.addCookie("ASP.NET_SessionId", "obtufgqzmpabcaxzkhfgxy34");
		
		GeccoEngine.create()
		.classpath("org.lengyan.crawler.demo.poetry.GSWAuthor")
		.start(start)
		.interval(5000)
		.run();
	}
}
