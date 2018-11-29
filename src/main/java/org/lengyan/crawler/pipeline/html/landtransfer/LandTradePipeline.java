package org.lengyan.crawler.pipeline.html.landtransfer;

import com.alibaba.fastjson.JSON;
import org.lengyan.crawler.annotation.PipelineName;
import org.lengyan.crawler.entry.html.landtransfer.LandTradeEntry;
import org.lengyan.crawler.pipeline.Pipeline;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.scheduler.DeriveSchedulerContext;
import org.lengyan.crawler.store.service.service.landtransfer.ILandTradeService;
import org.lengyan.crawler.store.service.serviceimpl.landtransfer.LandTradeServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;

@PipelineName("landTradePipeline")
public class LandTradePipeline implements Pipeline<LandTradeEntry> {
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");

	@Override
	public void process(LandTradeEntry landTradeEntry) {
		System.out.println(JSON.toJSONString(landTradeEntry));
		if (CollectionUtils.isEmpty(landTradeEntry.getLandTrades()) && CollectionUtils.isEmpty(landTradeEntry.getLandTrades2())){
			System.out.println("数据为空,暂停采集......");
		} else {
			//保存到数据库
			context.start();
			ILandTradeService landTradeService = (LandTradeServiceImpl)context.getBean("landTradeService");
			if (!CollectionUtils.isEmpty(landTradeEntry.getLandTrades())) {
				landTradeService.saveLandTrades(landTradeEntry.getLandTrades());
			}
			if (!CollectionUtils.isEmpty(landTradeEntry.getLandTrades2())) {
				landTradeService.saveLandTrades(landTradeEntry.getLandTrades2());
			}
			HttpRequest currRequest = landTradeEntry.getRequest();
			//下一页继续抓取
			int currPage = Integer.parseInt(currRequest.getParameter("TAB_QuerySubmitPagerData"));
			int nextPage = currPage + 1;
			HttpRequest request = landTradeEntry.getRequest();
			request.addParameter("TAB_QuerySubmitPagerData",nextPage + "");
			request.addCookie("Hm_lpvt_83853859c7247c5b03b527894622d3fa", String.valueOf(System.currentTimeMillis() / 1000));
			DeriveSchedulerContext.into(currRequest);
		}
	}

}
