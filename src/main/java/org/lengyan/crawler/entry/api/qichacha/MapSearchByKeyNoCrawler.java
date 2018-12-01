package org.lengyan.crawler.entry.api.qichacha;

import java.io.IOException;

import org.lengyan.crawler.GeccoEngine;
import org.lengyan.crawler.request.HttpPostRequest;

import com.alibaba.fastjson.JSONException;

/**
 * 企查查附近企业
 * 
 * @author kangtiancheng
 * @date 2018年12月1日
 */
public class MapSearchByKeyNoCrawler {
	
	
//	static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");
	
	public static void main(String[] args) throws IOException, JSONException {
		//保存到数据库
//		context.start();
//		IQichaQiyeService qichaQiyeService = (QichaQiyeServiceImpl)context.getBean("qichaQiyeService");
		HttpPostRequest start = new HttpPostRequest("https://www.qichacha.com/map_searchByKeyNo");
		start.addHeader(":authority", "www.qichacha.com");
		start.addHeader(":method", "POST");
		start.addHeader(":path", "/map_searchByKeyNo");
		start.addHeader(":scheme", "https");
		
		start.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		start.addHeader("Accept-Encoding", "gzip, deflate, br");
		start.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		start.addHeader("Cache-Control", "no-cache");
		start.addHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		start.addHeader("origin", "https://www.qichacha.com");
		start.addHeader("Pragma", "no-cache");
		start.addHeader("Referer", "https://www.qichacha.com/map?keyNo=5090d6738664642eb494606fbeac52ab");
		start.addHeader("Upgrade-Insecure-Requests", "1");
		start.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
		start.addHeader("x-requested-with", "XMLHttpRequest");
		
		start.addCookie("UM_distinctid", "164da9502f37b5-0c6d75269e73ab-5b123112-1fa400-164da9502f59fd");
		start.addCookie("_uab_collina", "153267555673556360313201");
		
		start.addParameter("searchType", "keyNo");
		start.addParameter("keyNo", "5090d6738664642eb494606fbeac52ab");
		
		GeccoEngine.create()
		.classpath("org.lengyan.crawler.demo.poetry.GSWAuthor")
		.start(start)
		.interval(5000)
		.run();
		
	}
	
}
