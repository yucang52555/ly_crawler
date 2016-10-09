package org.lengyan.crawler.pipeline;

import org.lengyan.crawler.spider.SpiderBean;

public interface Pipeline<T extends SpiderBean> {

	public void process(T bean);

}
