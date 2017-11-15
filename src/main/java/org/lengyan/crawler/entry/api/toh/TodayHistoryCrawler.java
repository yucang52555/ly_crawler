package org.lengyan.crawler.entry.api.toh;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.lengyan.crawler.store.service.service.juhe.IJuheTodayHistoryService;
import org.lengyan.crawler.store.service.serviceimpl.juhe.JuheTodayHistoryServiceImpl;
import org.lengyan.crawler.utils.api.HttpClientUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 历史上的今天数据采集
 * @author kangtiancheng
 * @date 2017年11月7日
 */
public class TodayHistoryCrawler {
	
	static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");
	
	public static void main(String[] args) {
		//保存到数据库
		context.start();
		IJuheTodayHistoryService todayHistoryService = (JuheTodayHistoryServiceImpl)context.getBean("juheTodayHistoryService");
		String url = "http://api.juheapi.com/japi/toh";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//v=1.0&month=11&day=6&key=7a89c5ecc3b2742edf32fd388c58298b
		paramMap.put("v", "1.0");
		paramMap.put("key", "7a89c5ecc3b2742edf32fd388c58298b");
		Calendar calendar = Calendar.getInstance();
		calendar.set(2017, 12, 31);
		
		for (int i = 0; i < 1; i++) {
			paramMap.put("month", 12);
			paramMap.put("day", calendar.get(Calendar.DATE));
			System.out.println(paramMap);
			calendar.set(Calendar.DATE, (Integer)paramMap.get("day") + 1);
			String response = HttpClientUtils.doGet(url, paramMap);
			System.out.println(response);
			todayHistoryService.saveApiResult(response);
		}
	}
}
