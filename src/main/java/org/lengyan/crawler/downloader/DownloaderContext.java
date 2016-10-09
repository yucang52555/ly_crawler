package org.lengyan.crawler.downloader;

import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.response.HttpResponse;
import org.lengyan.crawler.spider.SpiderBeanContext;
import org.lengyan.crawler.spider.SpiderThreadLocal;

/**
 * 获得当前线程，正在抓取的SpiderBean的下载器
 * 
 * @author huchengyi
 *
 */
public class DownloaderContext {
	
	public static HttpResponse download(HttpRequest request) throws DownloadException {
		SpiderBeanContext context = SpiderThreadLocal.get().getSpiderBeanContext();
		return context.getDownloader().download(request, context.getTimeout());
	}
	
	public static HttpResponse defaultDownload(HttpRequest request) throws DownloadException {
		SpiderBeanContext context = SpiderThreadLocal.get().getSpiderBeanContext();
		Downloader downloader = SpiderThreadLocal.get().getEngine().getSpiderBeanFactory().getDownloaderFactory().defaultDownloader();
		return downloader.download(request, context.getTimeout());
	}
	

}
