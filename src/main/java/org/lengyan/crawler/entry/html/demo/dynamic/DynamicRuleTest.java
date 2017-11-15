package org.lengyan.crawler.entry.html.demo.dynamic;

import org.lengyan.crawler.GeccoEngine;
import org.lengyan.crawler.dynamic.DynamicGecco;
import org.lengyan.crawler.request.HttpGetRequest;

/**
 * 本demo是一个在线修改抓取规则的例子，DyncmicGecco支持规则类的重新加载，不需要重启应用
 * 
 * @author huchengyi
 *
 */
public class DynamicRuleTest {

	public static void main(String[] args) throws Exception {
		
		//初始化爬虫引擎，此时由于没有初始请求，爬虫引擎会阻塞初始队列，直到获取到初始请求
		GeccoEngine ge = GeccoEngine.create("org.lengyan.crawler.demo.dynamic")		
		.interval(5000)
		.loop(true)
		.engineStart();
		
		//定义爬取规则
		Class<?> rule = DynamicGecco
		.html()
		.gecco("https://github.com/xtuhcy/gecco", "consolePipeline")
		.stringField("title").csspath(".repository-meta-content").text(false).build()
		.intField("star").csspath(".pagehead-actions li:nth-child(2) .social-count").text(false).build()
		.intField("fork").csspath(".pagehead-actions li:nth-child(3) .social-count").text().build()
		.loadClass();
		
		//注册规则
		ge.register(rule);
		
		//加入初始请求，爬虫引擎开始工作
		ge.getScheduler().into(new HttpGetRequest("https://github.com/xtuhcy/gecco"));
		
		Thread.sleep(5000);
		
		System.out.println("修改规则");
		
		try {
			//开始更新规则
			ge.beginUpdateRule();
			//修改规则
			Class<?> newRule = DynamicGecco
			.html(rule.getName())
			.gecco("https://github.com/xtuhcy/gecco", "consolePipeline")
			.intField("fork").csspath(".pagehead-actions li:nth-child(3) .social-count").text().build()
			.removeField("star")
			.loadClass();
			//注册新规则
			ge.register(newRule);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			//规则更新完毕
			ge.endUpdateRule();
		}
		
		Thread.sleep(5000);
		
		System.out.println("下线规则");
		try {
			//开始更新规则
			ge.beginUpdateRule();
			//下线之前的规则（也支持不下线规则，直接修改）
			ge.unregister(rule);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			//规则更新完毕
			ge.endUpdateRule();
		}
	}

}
