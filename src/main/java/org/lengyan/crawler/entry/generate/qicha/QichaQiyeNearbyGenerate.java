package org.lengyan.crawler.entry.generate.qicha;

import org.lengyan.crawler.generate.GenerateHTMLUtils;
import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaQiye;
import org.lengyan.crawler.store.service.service.qicha.IQichaQiyeService;
import org.lengyan.crawler.store.service.serviceimpl.qicha.QichaQiyeServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 企查查企业附近企业查询
 * @author kangtiancheng
 * @date 2017年11月13日
 */
public class QichaQiyeNearbyGenerate {
	
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");
	
	private static Integer pageSize = 2000;
	
	public static void main(String[] args) {
		String baseUrl = "https://www.qichacha.com/map?keyNo=";
		//保存到数据库
		context.start();
		IQichaQiyeService qichaQiyeService = (QichaQiyeServiceImpl)context.getBean("qichaQiyeService");
		List<QichaQiye> qichaQiyes = qichaQiyeService.selectGenNearQiyeList();
		for (int i = 0; i <= qichaQiyes.size() / pageSize; i++) {
			List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
			StringBuilder hrefBuilder = new StringBuilder();
			for (int j = 0; j < pageSize; j++) {
				if (i * pageSize + j < qichaQiyes.size()) {
					QichaQiye qichaQiye = qichaQiyes.get(i * pageSize + j);
					String qiyeUrl = qichaQiye.getQiyeUrl();
					Integer begin = qiyeUrl.indexOf("_") + 1;
					Integer end = qiyeUrl.lastIndexOf(".");
					String qiyeKey = qichaQiye.getQiyeUrl().substring(begin, end);

					hrefBuilder.append("<a href='").append(baseUrl).append(qiyeKey).append("'>")
					.append(qichaQiye.getQiyeName()).append("</a><br/>");
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("charset", "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
			map.put("title", "企查查企业列表" + i);
			map.put("content", hrefBuilder.toString());
			params.add(map);

			Map<String, String> urls = GenerateHTMLUtils.generate("F:\\kingkang\\workspace\\ly_crawler\\file\\HtmlTemplate.html",
					"F:\\kingkang\\workspace\\ly_crawler\\file\\html\\qiyeNear", params);
			for (Entry<String, String> en : urls.entrySet()) {
				System.out.println(en.getKey() + "-->" + en.getValue());
			}
		}
	}
}
