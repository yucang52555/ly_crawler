package org.lengyan.crawler.entry.generate.qicha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.lengyan.crawler.generate.GenerateHTMLUtils;
import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaQiye;
import org.lengyan.crawler.store.service.service.qicha.IQichaQiyeService;
import org.lengyan.crawler.store.service.serviceimpl.qicha.QichaQiyeServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 新浪用户采集页面
 * @author kangtiancheng
 * @date 2017年11月13日
 */
public class QichaQiyeGenerate {
	
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");
	
	private static Integer pageSize = 3000;
	
	public static void main(String[] args) {
		//保存到数据库
		context.start();
		IQichaQiyeService qichaQiyeService = (QichaQiyeServiceImpl)context.getBean("qichaQiyeService");
		List<QichaQiye> qichaQiyes = qichaQiyeService.selectAllQiyeList();
		for (int i = 0; i <= qichaQiyes.size() / pageSize; i++) {
			List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
			StringBuilder hrefBuilder = new StringBuilder();
			for (int j = 0; j < pageSize; j++) {
				if (i * pageSize + j < qichaQiyes.size()) {
					QichaQiye qichaQiye = qichaQiyes.get(i * pageSize + j);
					hrefBuilder.append("<a href='").append(qichaQiye.getQiyeUrl()).append("'>")
					.append(qichaQiye.getQiyeName()).append("</a><br/>");
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("charset", "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
			map.put("title", "企查查企业列表" + i);
			map.put("content", hrefBuilder.toString());
			params.add(map);
			
			Map<String, String> urls = GenerateHTMLUtils.generate("E:\\workspace\\lengyan_git\\ly_crawler\\file\\HtmlTemplate.html",
					"E:\\workspace\\lengyan_git\\ly_crawler\\file\\html\\qiye", params);
			for (Entry<String, String> en : urls.entrySet()) {
				System.out.println(en.getKey() + "-->" + en.getValue());
			}
		}
	}
}
