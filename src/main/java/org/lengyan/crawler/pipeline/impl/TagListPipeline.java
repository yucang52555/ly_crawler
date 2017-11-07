package org.lengyan.crawler.pipeline.impl;

import org.lengyan.crawler.annotation.PipelineName;
import org.lengyan.crawler.demo.poetry.GSWTag;
import org.lengyan.crawler.pipeline.Pipeline;
import org.lengyan.crawler.store.service.service.gushiwen.ITagService;
import org.lengyan.crawler.store.service.serviceimpl.gushiwen.TagServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

@PipelineName("tagListPipeline")
public class TagListPipeline implements Pipeline<GSWTag> {
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");

	@Override
	public void process(GSWTag gswTags) {
		System.out.println(JSON.toJSONString(gswTags));
		//保存到数据库
		context.start();
		ITagService tagService = (TagServiceImpl)context.getBean("tagService");
		tagService.saveTags(gswTags.getTags());
	}

}
