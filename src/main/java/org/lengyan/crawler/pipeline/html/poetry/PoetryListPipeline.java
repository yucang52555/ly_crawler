package org.lengyan.crawler.pipeline.html.poetry;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.lengyan.crawler.annotation.PipelineName;
import org.lengyan.crawler.entry.html.poetry.GSWPoetry;
import org.lengyan.crawler.pipeline.Pipeline;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.scheduler.DeriveSchedulerContext;
import org.lengyan.crawler.store.model.po.htmlpo.gushiwen.Tag;
import org.lengyan.crawler.store.service.service.gushiwen.IPoetryService;
import org.lengyan.crawler.store.service.service.gushiwen.ITagService;
import org.lengyan.crawler.store.service.serviceimpl.gushiwen.PoetryServiceImpl;
import org.lengyan.crawler.store.service.serviceimpl.gushiwen.TagServiceImpl;
import org.lengyan.crawler.utils.CommonUtils;
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
		ITagService tagService = (TagServiceImpl)context.getBean("tagService");
		List<Tag> tags = tagService.selectAllTags();
		
		poetryService.savePoetrys(gswPoetrys.getPoetrys());
		
		HttpRequest currRequest = gswPoetrys.getRequest();
		//下一页继续抓取
		int currPage = gswPoetrys.getPage();
		int nextPage = currPage + 1;
		int totalPage = 7240;
		if (CommonUtils.sizeOf(gswPoetrys.getPoetrys()) <= 0) {
			totalPage = 0;
		}
		String currUrl = currRequest.getUrl();
		String nextUrl = "";
		
		String tagName = gswPoetrys.getTag();
		Integer tagIndex = 0;
		for (int i = 0; i < CommonUtils.sizeOf(tags); i++) {
			Tag tag = tags.get(i);
			if (StringUtils.equals(tag.getTagName(), tagName)) {
				tagIndex = i;
				break;
			}
		}
		currRequest.addHeader("Referer", currUrl);
		if(nextPage <= totalPage) {
			if(currUrl.indexOf("p=") != -1) {
				nextUrl = StringUtils.replaceOnce(currUrl, "p=" + currPage, "p=" + nextPage);
			} else {
				nextUrl = currUrl + "&" + "p=" + nextPage;
			}
		} else if (CommonUtils.sizeOf(tags) > tagIndex) {
			if(currUrl.indexOf("t=") != -1) {
				nextUrl = StringUtils.replaceOnce(currUrl, "p=" + currPage, "p=" + 1);
				try {
					nextUrl = StringUtils.replaceOnce(nextUrl, "t=" + URLEncoder.encode(tagName, "UTF-8"), "t=" + URLEncoder.encode(tags.get(tagIndex + 1).getTagName(), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else {
				nextUrl = currUrl + "&" + "t=" + tags.get(0).getTagName();
			}
		}
		System.out.println(nextUrl);
		DeriveSchedulerContext.into(currRequest.subRequest(nextUrl));
	}

}
