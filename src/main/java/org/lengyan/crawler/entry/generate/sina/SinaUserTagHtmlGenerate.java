package org.lengyan.crawler.entry.generate.sina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.lengyan.crawler.generate.GenerateHTMLUtils;
import org.lengyan.crawler.store.model.po.htmlpo.sina.UserTag;
import org.lengyan.crawler.store.model.po.xmlpo.jianshu.JianshuArticle;
import org.lengyan.crawler.store.service.service.jianshu.IJianshuArticleService;
import org.lengyan.crawler.store.service.service.sina.IUserTagService;
import org.lengyan.crawler.store.service.serviceimpl.jianshu.JianshuArticleServiceImpl;
import org.lengyan.crawler.store.service.serviceimpl.sina.UserTagServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 新浪用户标签列表页面
 * @author kangtiancheng
 * @date 2017年11月13日
 */
public class SinaUserTagHtmlGenerate {
	
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");
	
	public static void main(String[] args) {
		//保存到数据库
		context.start();
		IUserTagService userTagService = (UserTagServiceImpl)context.getBean("userTagService");
		List<UserTag> userTags = userTagService.selectAllUserTagList();
		
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("charset", "<meta charset='UTF-8'>");
		map.put("title", "新浪用户标签列表页面");
		StringBuilder hrefBuilder = new StringBuilder();
		
		for (UserTag userTag : userTags) {
			hrefBuilder.append("<a href='").append(userTag.getTagUrl()).append("'>")
			.append(userTag.getTagName()).append("</a><br/>");
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
