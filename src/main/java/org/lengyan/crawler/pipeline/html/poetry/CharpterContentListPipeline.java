package org.lengyan.crawler.pipeline.html.poetry;

import org.apache.commons.lang3.StringUtils;
import org.lengyan.crawler.annotation.PipelineName;
import org.lengyan.crawler.html.poetry.GSWCharpterContent;
import org.lengyan.crawler.pipeline.Pipeline;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.scheduler.DeriveSchedulerContext;
import org.lengyan.crawler.store.model.po.htmlpo.gushiwen.GushiwenChapterWithBLOBs;
import org.lengyan.crawler.store.service.service.gushiwen.IChapterService;
import org.lengyan.crawler.store.service.serviceimpl.gushiwen.ChapterServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

@PipelineName("charpterContentListPipeline")
public class CharpterContentListPipeline implements Pipeline<GSWCharpterContent> {
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");

	@Override
	public void process(GSWCharpterContent charpterContent) {
		System.out.println(JSON.toJSONString(charpterContent));
		//保存章节到数据库
		context.start();
		IChapterService chapterService = (ChapterServiceImpl)context.getBean("chapterService");
		GushiwenChapterWithBLOBs chapter = charpterContent.getChapter();
//		System.out.println(chapter.getCharpterContent().length());
//		System.out.println(chapter.getTranslateContent().length());
		chapter.setId(Long.valueOf(charpterContent.getCharpterId()));
		chapterService.updateChapter(charpterContent.getChapter());
		
		HttpRequest currRequest = charpterContent.getRequest();
		//查询下一个抓取的url(数据库表t_gushiwen_chapter)
		Integer totalCharpter = 14142;
		if(charpterContent.getCharpterId() <= totalCharpter) {
			String nextUrl = "";
			String currUrl = currRequest.getUrl();
			nextUrl = StringUtils.replaceOnce(currUrl, "bookv_" + charpterContent.getCharpterId() + ".aspx", "bookv_" + (charpterContent.getCharpterId() + 1) + ".aspx");
			System.out.println(nextUrl);
			DeriveSchedulerContext.into(currRequest.subRequest(nextUrl));
		}
	}

}
