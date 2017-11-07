package org.lengyan.crawler.pipeline.impl;

import org.lengyan.crawler.annotation.PipelineName;
import org.lengyan.crawler.demo.qq.GSWQqInfo;
import org.lengyan.crawler.pipeline.Pipeline;
import org.lengyan.crawler.store.model.po.htmlpo.qq.QQAccount;
import org.lengyan.crawler.store.service.service.qq.IQQAccountService;
import org.lengyan.crawler.store.service.serviceimpl.qq.QQAcountServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

@PipelineName("qqInfoPipeline")
public class QqInfoPipeline implements Pipeline<GSWQqInfo>{
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");

	@Override
	public void process(GSWQqInfo gswQqInfo) {
		System.out.println(JSON.toJSONString(gswQqInfo));
		//保存到数据库
		context.start();
		IQQAccountService qqAccountService = (QQAcountServiceImpl)context.getBean("qqAccountService");
		QQAccount qqAccount = gswQqInfo.getQqAccount();
		qqAccount.setQqAccount(gswQqInfo.getQq());
		qqAccountService.updateQQAccount(qqAccount);
		
//		HttpRequest currRequest = gswQqAccounts.getRequest();
//		//下一页继续抓取
//		int currPage = gswQqAccounts.getPage();
//		int firstLetter = gswQqAccounts.getFirstLetter();
//		String currUrl = currRequest.getUrl();
//		String nextUrl = "";
//		int nextPage = currPage + 1;
//		int totalPage = 7300;
//		int totalLetter = 10;
//		int nextLetter = firstLetter + 1;
//		if (CommonUtils.sizeOf(gswQqAccounts.getQqAccounts()) < 100) {
//			totalPage = 0;
//			nextPage = 1;
//		}
//		if(nextPage <= totalPage) {
//			nextUrl = StringUtils.replaceOnce(currUrl, "_" + firstLetter + "_" + currPage, "_" + firstLetter + "_" + nextPage);
//		} else if (firstLetter <= totalLetter) {
//			nextUrl = StringUtils.replaceOnce(currUrl, "_" + firstLetter + "_" + currPage, "_" + nextLetter + "_" + nextPage);
//		}
//		DeriveSchedulerContext.into(currRequest.subRequest(nextUrl));
	}
}
