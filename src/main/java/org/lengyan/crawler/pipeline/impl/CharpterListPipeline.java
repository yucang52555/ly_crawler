package org.lengyan.crawler.pipeline.impl;

import org.apache.commons.lang3.StringUtils;
import org.lengyan.crawler.annotation.PipelineName;
import org.lengyan.crawler.demo.poetry.GSWCharpter;
import org.lengyan.crawler.pipeline.Pipeline;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.scheduler.DeriveSchedulerContext;
import org.lengyan.crawler.store.model.po.htmlpo.gushiwen.GushiwenChapterWithBLOBs;
import org.lengyan.crawler.store.service.service.gushiwen.IChapterService;
import org.lengyan.crawler.store.service.serviceimpl.gushiwen.ChapterServiceImpl;
import org.lengyan.crawler.utils.CommonUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

@PipelineName("charpterListPipeline")
public class CharpterListPipeline implements Pipeline<GSWCharpter> {
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");

	@Override
	public void process(GSWCharpter gswCharpter) {
		System.out.println(JSON.toJSONString(gswCharpter));
		//保存章节到数据库
		context.start();
		IChapterService chapterService = (ChapterServiceImpl)context.getBean("chapterService");
		//循环拼装数据
		Integer csize = CommonUtils.sizeOf(gswCharpter.getChapters());
		for (int j = 0; j < csize; j++) {
			GushiwenChapterWithBLOBs chapter = gswCharpter.getChapters().get(j);
			chapter.setBookId(Long.valueOf(gswCharpter.getBookId()));
			chapter.setCharpterClass(gswCharpter.getCharpterClass());
			chapterService.saveChapter(chapter);
		}
		
		HttpRequest currRequest = gswCharpter.getRequest();
		//查询下一个抓取的url(数据库表t_gushiwen_chapter)
		Integer totalBook = 163;
		if(gswCharpter.getBookId() <= totalBook) {
			String nextUrl = "";
			String currUrl = currRequest.getUrl();
			nextUrl = StringUtils.replaceOnce(currUrl, "book_" + gswCharpter.getBookId() + ".aspx", "book_" + (gswCharpter.getBookId() + 1) + ".aspx");
			DeriveSchedulerContext.into(currRequest.subRequest(nextUrl));
		}
	}

}
