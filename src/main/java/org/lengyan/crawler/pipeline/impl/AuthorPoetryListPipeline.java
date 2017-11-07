package org.lengyan.crawler.pipeline.impl;

import org.apache.commons.lang3.StringUtils;
import org.lengyan.crawler.annotation.PipelineName;
import org.lengyan.crawler.demo.poetry.GSWPoetryAuthor;
import org.lengyan.crawler.pipeline.Pipeline;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.scheduler.DeriveSchedulerContext;
import org.lengyan.crawler.store.service.service.gushiwen.IPoetryService;
import org.lengyan.crawler.store.service.serviceimpl.gushiwen.PoetryServiceImpl;
import org.lengyan.crawler.utils.CommonUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

@PipelineName("authorPoetryListPipeline")
public class AuthorPoetryListPipeline implements Pipeline<GSWPoetryAuthor> {
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");

	@Override
	public void process(GSWPoetryAuthor gswPoetryAuthors) {
		System.out.println(JSON.toJSONString(gswPoetryAuthors));
		//保存到数据库
		context.start();
		IPoetryService poetryService = (PoetryServiceImpl)context.getBean("poetryService");
		poetryService.savePoetrys(gswPoetryAuthors.getPoetrys());
		
		HttpRequest currRequest = gswPoetryAuthors.getRequest();
		String currUrl = currRequest.getUrl();
		Integer authorId = gswPoetryAuthors.getAuthorId();
		//下一页继续抓取
		int currPage = gswPoetryAuthors.getPage();
		int nextPage = currPage + 1;
		int totalPage = 3154;
		if (CommonUtils.sizeOf(gswPoetryAuthors.getPoetrys()) <= 0) {
			totalPage = 0;
		}
		
		String nextUrl = "";
		if(nextPage <= totalPage) {
			nextUrl = StringUtils.replaceOnce(currUrl, "A" + currPage + ".aspx", "A" + nextPage + ".aspx");
		} else {
			nextUrl = StringUtils.replaceOnce(currUrl, "authorsw_" + authorId + "A" + currPage + ".aspx", "authorsw_" + (authorId + 1) + "A1.aspx");
		}
		System.out.println(nextUrl);
		DeriveSchedulerContext.into(currRequest.subRequest(nextUrl));
	}

}
