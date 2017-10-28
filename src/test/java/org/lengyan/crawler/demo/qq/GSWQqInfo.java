package org.lengyan.crawler.demo.qq;

import java.util.List;

import org.lengyan.crawler.GeccoEngine;
import org.lengyan.crawler.annotation.Gecco;
import org.lengyan.crawler.annotation.Request;
import org.lengyan.crawler.annotation.RequestParameter;
import org.lengyan.crawler.annotation.html.HtmlField;
import org.lengyan.crawler.request.HttpGetRequest;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.spider.HtmlBean;
import org.lengyan.crawler.store.model.po.QQAccount;

/**
 * 古诗文-标签
 * @author kangtiancheng
 * @date 2017年6月20日
 */

@Gecco(matchUrl="http://qq.ico.la/qq{qq}.html", pipelines={"qqInfoPipeline"})
public class GSWQqInfo implements HtmlBean{

	private static final long serialVersionUID = 3192352232266233201L;

	@Request
	private HttpRequest request;
	
	@RequestParameter
	private String qq;
	
	@HtmlField(cssPath="#qq_recent div")
	private QQAccount qqAccount;
	
	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}
	
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	public QQAccount getQqAccount() {
		return qqAccount;
	}
	
	public void setQqAccount(QQAccount qqAccount) {
		this.qqAccount = qqAccount;
	}

	public static void main(String[] args) {
		HttpGetRequest start = new HttpGetRequest("http://qq.ico.la/qq979304557.html");
		start.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		start.addHeader("Accept-Encoding", "gzip, deflate, sdch");
		start.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		start.addHeader("Cache-Control", "no-cache");
		start.addHeader("Connection", "keep-alive");
		start.addHeader("Host", "qq.ico.la");
		start.addHeader("Pragma", "no-cache");
		start.addHeader("Upgrade-Insecure-Requests", "1");
		start.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
		
		start.addCookie("UM_distinctid", "15d348eae88196-0c451617ce4045-5b123112-1fa400-15d348eae89a9c");
		start.addCookie("bdshare_firstime", "1499825352679");
		start.addCookie("CNZZDATA2338246", "cnzz_eid%3D325654568-1499821784-%26ntime%3D1499843447");
		
		GeccoEngine.create()
		.classpath("org.lengyan.crawler.demo.qq.GSWQqInfo")
		.start(start)
		.interval(5000)
		.run();
	}
}
