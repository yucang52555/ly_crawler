package org.lengyan.crawler.entry.generate.sina;

import org.lengyan.crawler.generate.GenerateHTMLUtils;
import org.lengyan.crawler.store.model.po.xmlpo.jianshu.JianshuArticle;
import org.lengyan.crawler.store.model.po.xmlpo.sina.SinaUser;
import org.lengyan.crawler.store.service.service.jianshu.IJianshuArticleService;
import org.lengyan.crawler.store.service.service.sina.ISinaUserService;
import org.lengyan.crawler.store.service.serviceimpl.jianshu.JianshuArticleServiceImpl;
import org.lengyan.crawler.store.service.serviceimpl.sina.SinaUserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 新浪用户采集页面
 * @author kangtiancheng
 * @date 2017年11月13日
 */
public class SinaUserGenerate {
	
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");
	
	public static void main(String[] args) {
		//保存到数据库
		context.start();
		ISinaUserService sinaUserService = (SinaUserServiceImpl)context.getBean("sinaUserService");
		List<SinaUser> sinaUsers = sinaUserService.selectAllUserList();
		
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("charset", "<meta charset='UTF-8'>");
		map.put("title", "新浪用户微博信息");
		StringBuilder hrefBuilder = new StringBuilder();
		
		for (SinaUser sinaUser : sinaUsers) {
			hrefBuilder.append("<a href='").append(sinaUser.getUserUrl()).append("'>")
			.append(sinaUser.getUserName()).append("</a><br/>");
		}
		
		map.put("content", hrefBuilder.toString());
		params.add(map);
		
		Map<String, String> urls = GenerateHTMLUtils.generate("F:\\kingkang\\workspace\\ly_crawler\\file\\HtmlTemplate.html",
				"F:\\kingkang\\workspace\\ly_crawler\\file\\html", params);
		for (Entry<String, String> en : urls.entrySet()) {
			System.out.println(en.getKey() + "-->" + en.getValue());
		}
	}
}
