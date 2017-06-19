package org.lengyan.crawler.demo.poetry;

import org.apache.commons.lang3.StringUtils;
import org.lengyan.crawler.annotation.PipelineName;
import org.lengyan.crawler.pipeline.Pipeline;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.scheduler.DeriveSchedulerContext;
import org.lengyan.crawler.store.service.IAuthorService;
import org.lengyan.crawler.store.service.impl.AuthorServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

@PipelineName("authorListPipeline")
public class AuthorListPipeline implements Pipeline<GSWAuthor> {
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");

	@Override
	public void process(GSWAuthor gswAuthors) {
		System.out.println(JSON.toJSONString(gswAuthors));
		//保存到数据库
		context.start();
		IAuthorService authorService = (AuthorServiceImpl)context.getBean("authorService");
		authorService.saveAuthors(gswAuthors.getAuthors());
		
		HttpRequest currRequest = gswAuthors.getRequest();
		//下一页继续抓取
		int currPage = gswAuthors.getPage();
		int nextPage = currPage + 1;
		int totalPage = 316;
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
