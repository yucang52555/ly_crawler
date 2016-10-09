package org.lengyan.crawler.downloader.proxy;

import org.lengyan.crawler.spider.SpiderThreadLocal;

public class ProxysContext {
	
	public static Proxys get() {
		return SpiderThreadLocal.get().getEngine().getProxysLoader();
	}
	
	public static boolean isEnableProxy() {
		return SpiderThreadLocal.get().getEngine().isProxy();
	}

}
