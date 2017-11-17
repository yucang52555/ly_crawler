package org.lengyan.crawler.entry.generate.sina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.lengyan.crawler.generate.GenerateHTMLUtils;
import org.lengyan.crawler.store.model.po.xmlpo.sina.SinaUser;
import org.lengyan.crawler.store.service.service.sina.ISinaUserService;
import org.lengyan.crawler.store.service.serviceimpl.sina.SinaUserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 新浪用户采集页面
 * @author kangtiancheng
 * @date 2017年11月13日
 */
public class SinaUserGenerate {
	
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");
	
	private static Integer pageSize = 2000;
	
	public static void main(String[] args) {
		//保存到数据库
		context.start();
		ISinaUserService sinaUserService = (SinaUserServiceImpl)context.getBean("sinaUserService");
		List<SinaUser> sinaUsers = sinaUserService.selectAllUserList();
		for (int i = 0; i <= sinaUsers.size() / pageSize; i++) {
			List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
			StringBuilder hrefBuilder = new StringBuilder();
			for (int j = 0; j < pageSize; j++) {
				if (i * pageSize + j < sinaUsers.size()) {
					SinaUser sinaUser = sinaUsers.get(i * pageSize + j);
					hrefBuilder.append("<a href='").append(sinaUser.getUserUrl()).append("'>")
					.append(sinaUser.getUserName()).append("</a><br/>");
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("charset", "<meta charset='UTF-8'>");
			map.put("title", "新浪用户微博信息" + i);
			map.put("content", hrefBuilder.toString());
			params.add(map);
			
			Map<String, String> urls = GenerateHTMLUtils.generate("E:\\workspace\\lengyan_git\\ly_crawler\\file\\HtmlTemplate.html",
					"E:\\workspace\\lengyan_git\\ly_crawler\\file\\html\\sina", params);
			for (Entry<String, String> en : urls.entrySet()) {
				System.out.println(en.getKey() + "-->" + en.getValue());
			}
		}
	}
}
