package org.lengyan.crawler.xml.jianshu;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.lengyan.crawler.annotation.xml.XmlField;
import org.lengyan.crawler.spider.XmlBean;
import org.lengyan.crawler.store.model.po.xmlpo.jianshu.JianshuKeyword;
import org.lengyan.crawler.store.service.service.jianshu.IJianshuKeywordService;
import org.lengyan.crawler.store.service.serviceimpl.jianshu.JianshuKeywordServiceImpl;
import org.lengyan.crawler.utils.FileUtils;
import org.lengyan.crawler.utils.PropertiesUtil;
import org.lengyan.crawler.utils.xml.XMLParseUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 关键词解析
 * @author kangtiancheng
 * @date 2017年10月9日
 */
public class KeyWordParser implements XmlBean {
	
	private static final long serialVersionUID = -3348562176411353430L;
	
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");
	
	static String sourceFile = PropertiesUtil.getProperties("crawler", "xml.jianshu.source.keyword");
	static String backupFile = PropertiesUtil.getProperties("crawler", "xml.jianshu.backup.keyword");
	static String errorFile = PropertiesUtil.getProperties("crawler", "xml.jianshu.error.keyword");
	
	@XmlField(xPath="//keyword/item")
	private List<JianshuKeyword> jianshuKeywords;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		//
		String baseUrl = "http://www.jianshu.com";
		//保存到数据库
		context.start();
		IJianshuKeywordService keywordService = (JianshuKeywordServiceImpl)context.getBean("jianshuKeywordService");
		
		//1：循环获取文件列表
		while (!FileUtils.isEmpty(new File(sourceFile))) {
			try {
				//2：获取文件列表
				List<File> xmlFileList = FileUtils.getFiles(sourceFile);
				//3：循环文件列表
				for (File file : xmlFileList) {
					try {
						if (FileUtils.getFileName(file).startsWith("简书热搜关键词")) {
							System.out.println(file.getAbsolutePath());
							//读取文件
							Document document = XMLParseUtil.getDocument(file);
							Element rootElement = document.getRootElement();
							List<Element> keyElements = rootElement.element("keyword").elements("item");
//							List<Node> keyElements = document.selectNodes("//keyword/item");u
							for (Element element : keyElements) {
								System.out.println(element.asXML());
								String keyword = element.elementText("keyname");
								JianshuKeyword jianshuKeyword = keywordService.selectJianshuKeyword(keyword);
								if (jianshuKeyword == null) {
									jianshuKeyword = new JianshuKeyword();
									jianshuKeyword.setKeyword(keyword);
									jianshuKeyword.setCollectTimes(1);
									jianshuKeyword.setCreateTime(new Date());
									jianshuKeyword.setKeywordUrl(baseUrl + element.elementText("keyurl"));
									System.out.println(jianshuKeyword.toString());
									keywordService.saveKeyword(jianshuKeyword);
								} else {
									jianshuKeyword.setCollectTimes(jianshuKeyword.getCollectTimes() + 1);
									jianshuKeyword.setUpdateTime(new Date());
									keywordService.updateKeyword(jianshuKeyword);
								}
								jianshuKeyword = null;
								keyword = null;
							}
							//文件迁移到备份文件夹
							FileUtils.delFile(file.getAbsolutePath());
//							FileUtils.moveFile(file.getAbsolutePath(), backupFile + file.getName());
							//render
//							Render render = new org.lengyan.crawler.spider.render.xml.XmlRender();
//							SpiderBean spiderBean = render.inject(org.lengyan.crawler.xml.jianshu.KeyWordParser.class, document);
						}else {
							//文件迁移到备份文件夹
							FileUtils.moveFile(file.getAbsolutePath(), errorFile + file.getName());
						}
					} catch (Exception e) {
						//文件迁移到异常文件夹
						try {
							FileUtils.moveFile(file.getAbsolutePath(), errorFile + file.getName());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				}
				Thread.sleep(3*60*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
