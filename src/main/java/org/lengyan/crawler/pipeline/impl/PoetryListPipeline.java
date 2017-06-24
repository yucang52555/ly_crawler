package org.lengyan.crawler.pipeline.impl;

import org.apache.commons.lang3.StringUtils;
import org.lengyan.crawler.annotation.PipelineName;
import org.lengyan.crawler.demo.poetry.GSWPoetry;
import org.lengyan.crawler.pipeline.Pipeline;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.scheduler.DeriveSchedulerContext;
import org.lengyan.crawler.store.service.IPoetryService;
import org.lengyan.crawler.store.service.impl.PoetryServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

@PipelineName("poetryListPipeline")
public class PoetryListPipeline implements Pipeline<GSWPoetry> {
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");

	@Override
	public void process(GSWPoetry gswPoetrys) {
		System.out.println(JSON.toJSONString(gswPoetrys));
		//保存到数据库
		context.start();
		IPoetryService poetryService = (PoetryServiceImpl)context.getBean("poetryService");
		poetryService.savePoetrys(gswPoetrys.getPoetrys());
		
		HttpRequest currRequest = gswPoetrys.getRequest();
		//下一页继续抓取
		int currPage = gswPoetrys.getPage();
		int nextPage = currPage + 1;
		int totalPage = 7240;
		if(nextPage <= totalPage) {
			String nextUrl = "";
			String currUrl = currRequest.getUrl();
			if(currUrl.indexOf("p=") != -1) {
				nextUrl = StringUtils.replaceOnce(currUrl, "p=" + currPage, "p=" + nextPage);
			} else {
				nextUrl = currUrl + "&" + "page=" + nextPage;
			}
			DeriveSchedulerContext.into(currRequest.subRequest(nextUrl));
		}
	}

}
