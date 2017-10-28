package org.lengyan.crawler.pipeline;

import com.alibaba.fastjson.JSON;

import org.lengyan.crawler.annotation.PipelineName;
import org.lengyan.crawler.spider.SpiderBean;

@PipelineName("consolePipeline")
public class ConsolePipeline implements Pipeline<SpiderBean> {

	@Override
	public void process(SpiderBean bean) {
		System.out.println(JSON.toJSONString(bean));
	}

}
