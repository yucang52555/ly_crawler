package org.lengyan.crawler.entry.html.landtransfer;

import org.lengyan.crawler.GeccoEngine;
import org.lengyan.crawler.annotation.Gecco;
import org.lengyan.crawler.annotation.Request;
import org.lengyan.crawler.annotation.html.HtmlField;
import org.lengyan.crawler.request.HttpPostRequest;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.spider.HtmlBean;
import org.lengyan.crawler.store.model.po.htmlpo.landtransfer.LandTrade;

import java.util.List;

/**
 * 土地交易市场
 * @author kangtiancheng
 * @date 2018年10月19日
 */
@Gecco(matchUrl="http://www.landchina.com/default.aspx?tabid=263", pipelines={"landTradePipeline"})
public class LandTradeEntry implements HtmlBean{

	private static final long serialVersionUID = 295833399632474658L;
	
	@Request
	private HttpRequest request;
	
//	@RequestParameter
//	private Integer page;

	@HtmlField(cssPath="#TAB_contentTable .gridItem")
	private List<LandTrade> landTrades;

	@HtmlField(cssPath="#TAB_contentTable .gridAlternatingItem")
	private List<LandTrade> landTrades2;

//	public Integer getPage() {
//		return page;
//	}
//
//	public void setPage(Integer page) {
//		this.page = page;
//	}

	public List<LandTrade> getLandTrades() {
		return landTrades;
	}

	public void setLandTrades(List<LandTrade> landTrades) {
		this.landTrades = landTrades;
	}

	public List<LandTrade> getLandTrades2() {
		return landTrades2;
	}

	public void setLandTrades2(List<LandTrade> landTrades2) {
		this.landTrades2 = landTrades2;
	}

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public static void main(String[] args) {
//		HttpGetRequest start = new HttpGetRequest("http://www.landchina.com/default.aspx?tabid=263");
		HttpPostRequest start = new HttpPostRequest("http://www.landchina.com/default.aspx?tabid=263");

		start.addHeader("Referrer Policy", "no-referrer-when-downgrade");
		start.addHeader("Remote Address", "218.246.22.166:80");

		start.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		start.addHeader("Accept-Encoding", "gzip, deflate");
		start.addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
		start.addHeader("Cache-Control", "max-age=0");
		start.addHeader("Connection", "keep-alive");
//		start.addHeader("Content-Length", "");
		start.addHeader("Content-Type", "application/x-www-form-urlencoded;text/html");

		start.addHeader("Host", "www.landchina.com");
		start.addHeader("Origin", "http://www.landchina.com");
		start.addHeader("Referer", "http://www.landchina.com/default.aspx?tabid=263");
		start.addHeader("Upgrade-Insecure-Requests", "1");
		start.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");

		start.addCookie("yunsuo_session_verify", "059d29def8e41970c2eb0472fa5ef0f9");
		start.addCookie("ASP.NET_SessionId", "v5kj2b02ij2xggvtj25ukkm3");
		start.addCookie("Hm_lvt_83853859c7247c5b03b527894622d3fa", "1540177204,1540178958,1540287349");
		start.addCookie("Hm_lpvt_83853859c7247c5b03b527894622d3fa", String.valueOf(System.currentTimeMillis() / 1000));

		start.addParameter("__VIEWSTATE","/wEPDwUJNjkzNzgyNTU4D2QWAmYPZBYIZg9kFgICAQ9kFgJmDxYCHgdWaXNpYmxlaGQCAQ9kFgICAQ8WAh4Fc3R5bGUFIEJBQ0tHUk9VTkQtQ09MT1I6I2YzZjVmNztDT0xPUjo7ZAICD2QWAgIBD2QWAmYPZBYCZg9kFgJmD2QWBGYPZBYCZg9kFgJmD2QWAmYPZBYCZg9kFgJmDxYEHwEFIENPTE9SOiNEM0QzRDM7QkFDS0dST1VORC1DT0xPUjo7HwBoFgJmD2QWAgIBD2QWAmYPDxYCHgRUZXh0ZWRkAgEPZBYCZg9kFgJmD2QWAmYPZBYEZg9kFgJmDxYEHwEFhwFDT0xPUjojRDNEM0QzO0JBQ0tHUk9VTkQtQ09MT1I6O0JBQ0tHUk9VTkQtSU1BR0U6dXJsKGh0dHA6Ly93d3cubGFuZGNoaW5hLmNvbS9Vc2VyL2RlZmF1bHQvVXBsb2FkL3N5c0ZyYW1lSW1nL3hfdGRzY3dfc3lfamhnZ18wMDAuZ2lmKTseBmhlaWdodAUBMxYCZg9kFgICAQ9kFgJmDw8WAh8CZWRkAgIPZBYCZg9kFgJmD2QWAmYPZBYCZg9kFgJmD2QWAmYPZBYEZg9kFgJmDxYEHwEFIENPTE9SOiNEM0QzRDM7QkFDS0dST1VORC1DT0xPUjo7HwBoFgJmD2QWAgIBD2QWAmYPDxYCHwJlZGQCAg9kFgJmD2QWBGYPZBYCZg9kFgJmD2QWAmYPZBYCZg9kFgJmD2QWAmYPFgQfAQUgQ09MT1I6I0QzRDNEMztCQUNLR1JPVU5ELUNPTE9SOjsfAGgWAmYPZBYCAgEPZBYCZg8PFgIfAmVkZAICD2QWBGYPZBYCZg9kFgJmD2QWAmYPZBYCAgEPZBYCZg8WBB8BBYYBQ09MT1I6I0QzRDNEMztCQUNLR1JPVU5ELUNPTE9SOjtCQUNLR1JPVU5ELUlNQUdFOnVybChodHRwOi8vd3d3LmxhbmRjaGluYS5jb20vVXNlci9kZWZhdWx0L1VwbG9hZC9zeXNGcmFtZUltZy94X3Rkc2N3X3p5X2pnZ2dfMDEuZ2lmKTsfAwUCNDYWAmYPZBYCAgEPZBYCZg8PFgIfAmVkZAIBD2QWAmYPZBYCZg9kFgJmD2QWAgIBD2QWAmYPFgQfAQUgQ09MT1I6I0QzRDNEMztCQUNLR1JPVU5ELUNPTE9SOjsfAGgWAmYPZBYCAgEPZBYCZg8PFgIfAmVkZAIDD2QWAgIDDxYEHglpbm5lcmh0bWwF+gY8cCBhbGlnbj0iY2VudGVyIj48c3BhbiBzdHlsZT0iZm9udC1zaXplOiB4LXNtYWxsIj4mbmJzcDs8YnIgLz4NCiZuYnNwOzxhIHRhcmdldD0iX3NlbGYiIGhyZWY9Imh0dHA6Ly93d3cubGFuZGNoaW5hLmNvbS8iPjxpbWcgYm9yZGVyPSIwIiBhbHQ9IiIgd2lkdGg9IjI2MCIgaGVpZ2h0PSI2MSIgc3JjPSIvVXNlci9kZWZhdWx0L1VwbG9hZC9mY2svaW1hZ2UvdGRzY3dfbG9nZS5wbmciIC8+PC9hPiZuYnNwOzxiciAvPg0KJm5ic3A7PHNwYW4gc3R5bGU9ImNvbG9yOiAjZmZmZmZmIj5Db3B5cmlnaHQgMjAwOC0yMDE4IERSQ25ldC4gQWxsIFJpZ2h0cyBSZXNlcnZlZCZuYnNwOyZuYnNwOyZuYnNwOyA8c2NyaXB0IHR5cGU9InRleHQvamF2YXNjcmlwdCI+DQp2YXIgX2JkaG1Qcm90b2NvbCA9ICgoImh0dHBzOiIgPT0gZG9jdW1lbnQubG9jYXRpb24ucHJvdG9jb2wpID8gIiBodHRwczovLyIgOiAiIGh0dHA6Ly8iKTsNCmRvY3VtZW50LndyaXRlKHVuZXNjYXBlKCIlM0NzY3JpcHQgc3JjPSciICsgX2JkaG1Qcm90b2NvbCArICJobS5iYWlkdS5jb20vaC5qcyUzRjgzODUzODU5YzcyNDdjNWIwM2I1Mjc4OTQ2MjJkM2ZhJyB0eXBlPSd0ZXh0L2phdmFzY3JpcHQnJTNFJTNDL3NjcmlwdCUzRSIpKTsNCjwvc2NyaXB0PiZuYnNwOzxiciAvPg0K54mI5p2D5omA5pyJJm5ic3A7IOS4reWbveWcn+WcsOW4guWcuue9kSZuYnNwOyZuYnNwO+aKgOacr+aUr+aMgTrmtZnmsZ/oh7vlloTnp5HmioDogqHku73mnInpmZDlhazlj7gmbmJzcDs8YnIgLz4NCuWkh+ahiOWPtzog5LqsSUNQ5aSHMDkwNzQ5OTLlj7cg5Lqs5YWs572R5a6J5aSHMTEwMTAyMDAwNjY2KDIpJm5ic3A7PGJyIC8+DQo8L3NwYW4+Jm5ic3A7Jm5ic3A7Jm5ic3A7PGJyIC8+DQombmJzcDs8L3NwYW4+PC9wPh8BBWRCQUNLR1JPVU5ELUlNQUdFOnVybChodHRwOi8vd3d3LmxhbmRjaGluYS5jb20vVXNlci9kZWZhdWx0L1VwbG9hZC9zeXNGcmFtZUltZy94X3Rkc2N3MjAxM195d18xLmpwZyk7ZGR/7GXN5DIIOV/26iXPadE6/H1NZChSKWznh9WkA9yZZg==");
		start.addParameter("__EVENTVALIDATION","/wEWAgKZ8cmUBALN3cj/BKkUPcv3bDTFfdWn64G1DimV/gX9Bg6y+rvXVCgJ5LZz");
		start.addParameter("hidComName","default");
		start.addParameter("TAB_QueryConditionItem","9f2c3acd-0256-4da2-a659-6949c4671a2a");
		start.addParameter("TAB_QuerySortItemList","282:False");
		start.addParameter("TAB_QuerySubmitConditionData","9f2c3acd-0256-4da2-a659-6949c4671a2a:1950-1-1~2000-12-31");
		start.addParameter("TAB_QuerySubmitOrderData","282:False");
		start.addParameter("TAB_RowButtonActionControl","");
		start.addParameter("TAB_QuerySubmitPagerData","1");
		start.addParameter("TAB_QuerySubmitSortData","");

		GeccoEngine.create()
		.classpath("org.lengyan.crawler.entry.html.landtransfer.LandTradeEntry")
		.start(start)
		.interval(5000)
		.run();
	}
}
