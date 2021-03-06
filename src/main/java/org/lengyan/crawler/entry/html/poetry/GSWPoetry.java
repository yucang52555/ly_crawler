package org.lengyan.crawler.entry.html.poetry;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.lengyan.crawler.GeccoEngine;
import org.lengyan.crawler.annotation.Gecco;
import org.lengyan.crawler.annotation.Request;
import org.lengyan.crawler.annotation.RequestParameter;
import org.lengyan.crawler.annotation.html.HtmlField;
import org.lengyan.crawler.request.HttpGetRequest;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.spider.HtmlBean;
import org.lengyan.crawler.store.model.po.htmlpo.gushiwen.Poetry;

/**
 * 古诗文-诗词
 * @author kangtiancheng
 * @date 2017年6月20日
 */
@Gecco(matchUrl="http://so.gushiwen.org/type.aspx?p={page}&t={tag}", pipelines={"poetryListPipeline"})
public class GSWPoetry implements HtmlBean{

	private static final long serialVersionUID = -5393179128629256424L;
	
	@Request
	private HttpRequest request;
	
	@RequestParameter
	private Integer page;
	
	@RequestParameter
	private String tag;
	
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
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String requestEncodeKey = URLEncoder.encode("早春","UTF-8");
		System.out.println(requestEncodeKey);
		HttpGetRequest start = new HttpGetRequest("http://so.gushiwen.org/type.aspx?p=1&t=" + requestEncodeKey);
		start.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		start.addHeader("Accept-Encoding", "gzip, deflate, sdch");
		start.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		start.addHeader("Cache-Control", "no-cache");
		start.addHeader("Connection", "keep-alive");
		start.addHeader("Host", "so.gushiwen.org");
		start.addHeader("Pragma", "no-cache");
		start.addHeader("Referer", "http://so.gushiwen.org/type.aspx?p=1&t=" + requestEncodeKey);
		start.addHeader("Upgrade-Insecure-Requests", "1");
		start.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
		
		start.addCookie("__cfduid", "d0a1a830bbdf6231cfd6cd5535db45a7c1497841525");
		start.addCookie("ASP.NET_SessionId", "ggux5pu3lykdhbrseh5a1vqr");
		start.addCookie("Hm_lvt_04660099568f561a75456483228a9516", "1498725778,1498790870,1499051413");
		start.addCookie("Hm_lpvt_04660099568f561a75456483228a9516", System.currentTimeMillis()/1000 + "");
		System.out.println(System.currentTimeMillis()/1000 + "");
		
		GeccoEngine.create()
		.classpath("org.lengyan.crawler.demo.poetry.GSWPoetry")
		.start(start)
		.interval(5000)
		.proxy(true)
		.run();
		
	}
}
