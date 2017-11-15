package org.lengyan.crawler.entry.generate.jianshu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.lengyan.crawler.generate.GenerateHTMLUtils;
import org.lengyan.crawler.store.model.po.xmlpo.jianshu.JianshuArticle;
import org.lengyan.crawler.store.service.service.jianshu.IJianshuArticleService;
import org.lengyan.crawler.store.service.serviceimpl.jianshu.JianshuArticleServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JianshuUserHtmlGenerate {
	
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");
	
	public static void main(String[] args) {
		//保存到数据库
		context.start();
		IJianshuArticleService articleService = (JianshuArticleServiceImpl)context.getBean("jianshuArticleService");
		List<JianshuArticle> jianshuArticles = articleService.selectAllUserList();
		
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("charset", "<meta charset='UTF-8'>");
		map.put("title", "简书用户关系信息");
		StringBuilder hrefBuilder = new StringBuilder();
		
		for (JianshuArticle jianshuArticle : jianshuArticles) {
			hrefBuilder.append("<a href='").append(jianshuArticle.getUserUrl()).append("'>")
			.append(jianshuArticle.getUserName()).append("</a><br/>");
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
