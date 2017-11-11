package org.lengyan.crawler.pipeline.html.qq;

import org.apache.commons.lang3.StringUtils;
import org.lengyan.crawler.annotation.PipelineName;
import org.lengyan.crawler.demo.qq.GSWQqAccount;
import org.lengyan.crawler.pipeline.Pipeline;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.scheduler.DeriveSchedulerContext;
import org.lengyan.crawler.store.service.service.qq.IQQAccountService;
import org.lengyan.crawler.store.service.serviceimpl.qq.QQAcountServiceImpl;
import org.lengyan.crawler.utils.CommonUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

@PipelineName("qqListPipeline")
public class QqListPipeline implements Pipeline<GSWQqAccount> {
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");

	@Override
	public void process(GSWQqAccount gswQqAccounts) {
		System.out.println(JSON.toJSONString(gswQqAccounts));
		//保存到数据库
		context.start();
		IQQAccountService qqAccountService = (QQAcountServiceImpl)context.getBean("qqAccountService");
		qqAccountService.saveQQAccounts(gswQqAccounts.getQqAccounts());
		
		HttpRequest currRequest = gswQqAccounts.getRequest();
		//下一页继续抓取
		int currPage = gswQqAccounts.getPage();
		int firstLetter = gswQqAccounts.getFirstLetter();
		String currUrl = currRequest.getUrl();
		String nextUrl = "";
		int nextPage = currPage + 1;
		int totalPage = 7300;
		int totalLetter = 10;
		int nextLetter = firstLetter + 1;
		if (CommonUtils.sizeOf(gswQqAccounts.getQqAccounts()) < 100) {
			totalPage = 0;
			nextPage = 1;
		}
		if(nextPage <= totalPage) {
			nextUrl = StringUtils.replaceOnce(currUrl, "_" + firstLetter + "_" + currPage, "_" + firstLetter + "_" + nextPage);
		} else if (firstLetter <= totalLetter) {
			nextUrl = StringUtils.replaceOnce(currUrl, "_" + firstLetter + "_" + currPage, "_" + nextLetter + "_" + nextPage);
		}
		DeriveSchedulerContext.into(currRequest.subRequest(nextUrl));
	}

}
