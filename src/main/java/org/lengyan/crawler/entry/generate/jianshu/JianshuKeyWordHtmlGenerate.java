package org.lengyan.crawler.entry.generate.jianshu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.lengyan.crawler.generate.GenerateHTMLUtils;
import org.lengyan.crawler.store.model.po.xmlpo.jianshu.JianshuKeyword;
import org.lengyan.crawler.store.service.service.jianshu.IJianshuKeywordService;
import org.lengyan.crawler.store.service.serviceimpl.jianshu.JianshuKeywordServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JianshuKeyWordHtmlGenerate {
	
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");
	
	public static void main(String[] args) {
		//保存到数据库
		context.start();
		IJianshuKeywordService keywordService = (JianshuKeywordServiceImpl)context.getBean("jianshuKeywordService");
		List<JianshuKeyword> jianshuKeywords = keywordService.selectAllJianshuKeyword();
		
		
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("charset", "<meta charset='UTF-8'>");
		map.put("title", "简书用户信息");
		StringBuilder hrefBuilder = new StringBuilder();
//		for (int i = 1; i < 60; i++) {
//			hrefBuilder.append("<a href='http://www.jianshu.com/search?q=%E6%95%B0%E6%8D%AE&page=")
//						.append(i)
//						.append("&type=user'>第")
//						.append(i)
//						.append("页</a><br/>");
//		}
		
		for (JianshuKeyword jianshuKeyword : jianshuKeywords) {
			hrefBuilder.append("<a href='").append(jianshuKeyword.getKeywordUrl()).append("'>")
			.append(jianshuKeyword.getKeyword()).append("</a><br/>");
		}
		
		map.put("content", hrefBuilder.toString());
		params.add(map);
		
		Map<String, String> urls = GenerateHTMLUtils.generate("E:/workspace/lengyan_git/ly_crawler/file/HtmlTemplate.html",
				"E:/workspace/lengyan_git/ly_crawler/file/html", params);
		for (Entry<String, String> en : urls.entrySet()) {
			System.out.println(en.getKey() + "-->" + en.getValue());
		}
	}
}
