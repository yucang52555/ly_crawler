package org.lengyan.crawler.entry.xml.sina;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.lengyan.crawler.annotation.xml.XmlField;
import org.lengyan.crawler.spider.XmlBean;
import org.lengyan.crawler.store.model.po.htmlpo.sina.UserTag;
import org.lengyan.crawler.store.model.po.xmlpo.jianshu.JianshuKeyword;
import org.lengyan.crawler.store.service.service.sina.IUserTagService;
import org.lengyan.crawler.store.service.serviceimpl.sina.UserTagServiceImpl;
import org.lengyan.crawler.utils.FileUtils;
import org.lengyan.crawler.utils.PropertiesUtil;
import org.lengyan.crawler.utils.xml.XMLParseUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 新浪用户标签解析
 * @author kangtiancheng
 * @date 2017年11月11日
 */
public class SinaUserTagParser implements XmlBean {
	
	private static final long serialVersionUID = -3348562176411353430L;
	
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");
	
	static String sourceFile = PropertiesUtil.getProperties("crawler", "xml.sina.source.usertag");
	static String backupFile = PropertiesUtil.getProperties("crawler", "xml.sina.backup.usertag");
	static String errorFile = PropertiesUtil.getProperties("crawler", "xml.sina.error.usertag");
	
	@XmlField(xPath="//keyword/item")
	private List<JianshuKeyword> jianshuKeywords;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		//保存到数据库
		context.start();
		IUserTagService userTagService = (UserTagServiceImpl)context.getBean("userTagService");
		
		//1：循环获取文件列表
		while (!FileUtils.isEmpty(new File(sourceFile))) {
			try {
				//2：获取文件列表
				List<File> xmlFileList = FileUtils.getFiles(sourceFile);
				//3：循环文件列表
				for (File file : xmlFileList) {
					try {
						if (FileUtils.getFileName(file).startsWith("sina_user_tag")) {
							System.out.println(file.getAbsolutePath());
							//读取文件
							Document document = XMLParseUtil.getDocument(file);
							Element rootElement = document.getRootElement();
							List<Element> typeElements = rootElement.element("UserTag").elements("item");
							for (Element element : typeElements) {
								System.out.println(element.asXML());
								String tagType = element.elementText("tagType");
								List<Element> tagElements = element.element("tag").elements("item");
								for (Element tagElement : tagElements) {
									UserTag userTag = new UserTag();
									userTag.setTagName(tagElement.elementText("tagName"));
									userTag.setTagUrl(tagElement.elementText("tagUrl"));
									userTag.setTagType(tagType);
									userTagService.saveUserTag(userTag);
								}
							}
							//文件迁移到备份文件夹
							FileUtils.delFile(file.getAbsolutePath());
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
