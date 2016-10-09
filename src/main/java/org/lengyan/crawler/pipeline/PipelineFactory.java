package org.lengyan.crawler.pipeline;

import org.lengyan.crawler.spider.SpiderBean;

public interface PipelineFactory {
	
	public Pipeline<? extends SpiderBean> getPipeline(String name);

}
