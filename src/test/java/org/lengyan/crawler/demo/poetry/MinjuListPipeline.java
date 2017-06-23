package org.lengyan.crawler.demo.poetry;

import org.apache.commons.lang3.StringUtils;
import org.lengyan.crawler.annotation.PipelineName;
import org.lengyan.crawler.pipeline.Pipeline;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.scheduler.DeriveSchedulerContext;
import org.lengyan.crawler.store.service.IMinjuService;
import org.lengyan.crawler.store.service.impl.MinjuServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

@PipelineName("minjuListPipeline")
public class MinjuListPipeline implements Pipeline<GSWMinju> {
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");

	@Override
	public void process(GSWMinju gswMinjus) {
		System.out.println(JSON.toJSONString(gswMinjus));
		//保存到数据库
		context.start();
		IMinjuService minjuService = (MinjuServiceImpl)context.getBean("minjuService");
		minjuService.saveMinjus(gswMinjus.getMinjus());
		
		HttpRequest currRequest = gswMinjus.getRequest();
		//下一页继续抓取
		int currPage = gswMinjus.getPage();
		int nextPage = currPage + 1;
		int totalPage = 115;
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
