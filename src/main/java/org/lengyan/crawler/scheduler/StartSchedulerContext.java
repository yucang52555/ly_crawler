package org.lengyan.crawler.scheduler;

import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.spider.SpiderThreadLocal;

/**
 * 初始队列队列上下文，可以在运行时将请求放入初始队列
 * 
 * @author huchengyi
 *
 */
public class StartSchedulerContext {
	
	public static void into(HttpRequest request) {
		SpiderThreadLocal.get().getEngine().getScheduler().into(request);
	}

}
