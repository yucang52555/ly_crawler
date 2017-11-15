package org.lengyan.crawler.pipeline.html.sina;

import java.util.List;

import org.lengyan.crawler.annotation.PipelineName;
import org.lengyan.crawler.entry.html.sina.SinaUserTag;
import org.lengyan.crawler.pipeline.Pipeline;
import org.lengyan.crawler.store.model.po.htmlpo.sina.UserTag;
import org.lengyan.crawler.store.service.service.sina.IUserTagService;
import org.lengyan.crawler.store.service.serviceimpl.sina.UserTagServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

@PipelineName("sinaUserTagPipeline")
public class SinaUserTagPipeline implements Pipeline<SinaUserTag>{
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");

	@Override
	public void process(SinaUserTag sinaUserTag) {
		System.out.println(JSON.toJSONString(sinaUserTag));
		//保存到数据库
		context.start();
		List<UserTag> userTags = sinaUserTag.getTags();
		
		IUserTagService userTagService = (UserTagServiceImpl)context.getBean("userTagService");
		for (UserTag userTag : userTags) {
			userTagService.saveUserTag(userTag);
		}
	}
}
