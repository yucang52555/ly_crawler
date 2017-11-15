package org.lengyan.crawler.entry.html.sina;

import java.util.List;

import org.lengyan.crawler.GeccoEngine;
import org.lengyan.crawler.annotation.Gecco;
import org.lengyan.crawler.annotation.Request;
import org.lengyan.crawler.annotation.html.HtmlField;
import org.lengyan.crawler.request.HttpGetRequest;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.spider.HtmlBean;
import org.lengyan.crawler.store.model.po.htmlpo.sina.UserTag;


@Gecco(matchUrl="https://weibo.com/?category=0", pipelines={"sinaUserTagPipeline"})
public class SinaUserTag implements HtmlBean{

	private static final long serialVersionUID = -7235477047148963880L;

	@Request
	private HttpRequest request;
	
	@HtmlField(cssPath=".FRAME_login .B_unlog .WB_miniblog .WB_miniblog_fb #plc_frame .WB_frame #plc_main #plc_unlogin_home_main .WB_main_r #pl_unlogin_home_hotpersoncategory .UG_box_l .UG_contents .UG_tag_list .clearfix li")
	private List<UserTag> tags;
	
	public List<UserTag> getTags() {
		return tags;
	}
	
	public void setTags(List<UserTag> tags) {
		this.tags = tags;
	}

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public static void main(String[] args) {
		HttpGetRequest start = new HttpGetRequest("https://weibo.com/?category=0");
		start.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		start.addHeader("Accept-Encoding", "gzip, deflate, sdch, br");
		start.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		start.addHeader("Cache-Control", "no-cache");
		start.addHeader("Connection", "keep-alive");
		start.addHeader("Host", "weibo.com");
		start.addHeader("Pragma", "no-cache");
		start.addHeader("Upgrade-Insecure-Requests", "1");
		start.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
		
		start.addCookie("SINAGLOBAL", "5616606952435.641.1481866959217");
		start.addCookie("UM_distinctid", "15c8acc7844ae9-068d899be70b2f-5b123112-1fa400-15c8acc784569f");
		start.addCookie("SCF", "AnexsUEITM4jzdL0eLyju-p6Hhu71HgvURTjBGaE5hyoMDPr482sRWO52VJxUDbOsfnED5vdhAH4fmP9ZrLDt9E.");
		start.addCookie("SUHB", "02MumPkKyQYvsK");
		start.addCookie("SUB", "_2AkMumEFJf8NxqwJRmPAVyGnjaIt_yg3EieKYxLCSJRMxHRl-yT83qhYFtRDPAPwMr4yJmfopfp1J_fwk_rj7Hw..");
		start.addCookie("SUBP", "0033WrSXqPxfM72-Ws9jqgMF55529P9D9Wh2V0oSH7JieOviwxafz1Ad");
		start.addCookie("_s_tentry", "www.appinventor.cn");
		start.addCookie("YF-V5-G0", "c948c7abbe2dbb5da556924587966312");
		start.addCookie("UOR", "os.qudong.com,widget.weibo.com,news.youth.cn");
		start.addCookie("login_sid_t", "dd4f8f0d8f726e3eb92f0efa3a552ed1");
		start.addCookie("YF-Ugrow-G0", "ad83bc19c1269e709f753b172bddb094");
		start.addCookie("Apache", "505928423773.3319.1510373234217");
		start.addCookie("ULV", "1510373234221:7:1:1:505928423773.3319.1510373234217:1504083153374");
		start.addCookie("YF-Page-G0", "fc0a6021b784ae1aaff2d0aa4c9d1f17");
		start.addCookie("cross_origin_proto", "SSL");
		
		GeccoEngine.create()
		.classpath("org.lengyan.crawler.html.sina.SinaUserTag")
		.start(start)
		.interval(5000)
		.run();
	}
}
