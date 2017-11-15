package org.lengyan.crawler.entry.html.wx;

import org.lengyan.crawler.annotation.Gecco;
import org.lengyan.crawler.annotation.html.HtmlField;
import org.lengyan.crawler.annotation.html.Text;
import org.lengyan.crawler.spider.HtmlBean;


@Gecco(matchUrl="http://mp.weixin.qq.com/s?{params}", pipelines="consolePipeline")
public class Article implements HtmlBean {

	private static final long serialVersionUID = 5310736056776105882L;

	@Text(own=false)
	@HtmlField(cssPath="body")
	private String body;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}
