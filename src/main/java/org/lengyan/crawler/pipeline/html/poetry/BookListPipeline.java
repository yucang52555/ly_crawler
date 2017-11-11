package org.lengyan.crawler.pipeline.html.poetry;

import org.apache.commons.lang3.StringUtils;
import org.lengyan.crawler.annotation.PipelineName;
import org.lengyan.crawler.html.poetry.GSWBook;
import org.lengyan.crawler.pipeline.Pipeline;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.scheduler.DeriveSchedulerContext;
import org.lengyan.crawler.store.service.service.gushiwen.IBookService;
import org.lengyan.crawler.store.service.serviceimpl.gushiwen.BookServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

@PipelineName("bookListPipeline")
public class BookListPipeline implements Pipeline<GSWBook> {
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");

	@Override
	public void process(GSWBook gswBooks) {
		System.out.println(JSON.toJSONString(gswBooks));
		//保存到数据库
		context.start();
		IBookService bookService = (BookServiceImpl)context.getBean("bookService");
		bookService.saveBooks(gswBooks.getBooks());
		
		HttpRequest currRequest = gswBooks.getRequest();
		//下一页继续抓取
		int currPage = gswBooks.getPage();
		int nextPage = currPage + 1;
		int totalPage = 20;
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
