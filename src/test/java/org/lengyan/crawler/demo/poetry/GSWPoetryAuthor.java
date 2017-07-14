package org.lengyan.crawler.demo.poetry;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.lengyan.crawler.GeccoEngine;
import org.lengyan.crawler.annotation.Gecco;
import org.lengyan.crawler.annotation.HtmlField;
import org.lengyan.crawler.annotation.Request;
import org.lengyan.crawler.annotation.RequestParameter;
import org.lengyan.crawler.request.HttpGetRequest;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.spider.HtmlBean;
import org.lengyan.crawler.store.model.po.Poetry;

/**
 * 古诗文-诗词
 * @author kangtiancheng
 * @date 2017年6月20日
 */
@Gecco(matchUrl="http://so.gushiwen.org/authors/authorsw_{authorId}A{page}.aspx", pipelines={"authorPoetryListPipeline"})
public class GSWPoetryAuthor implements HtmlBean{

	private static final long serialVersionUID = 3272910350864796373L;

	@Request
	private HttpRequest request;
	
	@RequestParameter
	private Integer page;
	
	@RequestParameter
	private Integer authorId;
	
	@HtmlField(cssPath=".main3 .left .sons")
	private List<Poetry> poetrys;
	
	public List<Poetry> getPoetrys() {
		return poetrys;
	}
	
	public void setPoetrys(List<Poetry> poetrys) {
		this.poetrys = poetrys;
	}

	public Integer getPage() {
		return page;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}
	
	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		HttpGetRequest start = new HttpGetRequest("http://so.gushiwen.org/authors/authorsw_2613A1.aspx");
		start.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		start.addHeader("Accept-Encoding", "gzip, deflate, sdch");
		start.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		start.addHeader("Cache-Control", "no-cache");
		start.addHeader("Connection", "keep-alive");
		start.addHeader("Host", "so.gushiwen.org");
		start.addHeader("Pragma", "no-cache");
//		start.addHeader("Referer", "http://so.gushiwen.org/authors/authorsw_135A48.aspx");
		start.addHeader("Upgrade-Insecure-Requests", "1");
		start.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
		
		start.addCookie("__cfduid", "d0a1a830bbdf6231cfd6cd5535db45a7c1497841525");
		start.addCookie("ASP.NET_SessionId", "ggux5pu3lykdhbrseh5a1vqr");
		start.addCookie("Hm_lvt_04660099568f561a75456483228a9516", "1498790870,1499051413,1499392091,1499410664");
		start.addCookie("Hm_lpvt_04660099568f561a75456483228a9516", System.currentTimeMillis()/1000 + "");
		System.out.println(System.currentTimeMillis()/1000 + "");
		
		GeccoEngine.create()
		.classpath("org.lengyan.crawler.demo.poetry.GSWPoetryAuthor")
		.start(start)
		.interval(5000)
		.proxy(true)
		.run();
	}
}
