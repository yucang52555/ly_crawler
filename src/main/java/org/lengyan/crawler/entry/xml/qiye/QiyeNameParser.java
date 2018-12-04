package org.lengyan.crawler.entry.xml.qiye;

import org.dom4j.Document;
import org.dom4j.Element;
import org.lengyan.crawler.spider.XmlBean;
import org.lengyan.crawler.utils.FileUtils;
import org.lengyan.crawler.utils.PropertiesUtil;
import org.lengyan.crawler.utils.xml.XMLParseUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.List;

/**
 * 帮忙-企业名称数据
 * @author kangtiancheng
 * @date 2018年11月6日
 */
public class QiyeNameParser implements XmlBean {
	
	private static final long serialVersionUID = -3348562176411353430L;
	
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");
	
	static String sourceFile = PropertiesUtil.getProperties("crawler", "xml.qiye.source.trade");
	static String backupFile = PropertiesUtil.getProperties("crawler", "xml.qiye.backup.trade");
	static String errorFile = PropertiesUtil.getProperties("crawler", "xml.qiye.error.trade");
	
	public static void main(String[] args) {
		//1：循环获取文件列表
		while (!FileUtils.isEmpty(new File(sourceFile))) {
			try {
				//2：获取文件列表
				List<File> xmlFileList = FileUtils.getFiles(sourceFile);
				//3：循环文件列表
				for (File file : xmlFileList) {
					try {
						if (FileUtils.getFileName(file).startsWith("帮忙-企业名称抓取后")) {
//							System.out.println(file.getAbsolutePath());
							//读取文件
							Document document = XMLParseUtil.getDocument(file);
							Element rootElement = document.getRootElement();
							List<Element> qiyeElements = rootElement.element("企业").elements("item");
							for (Element element : qiyeElements) {
								String qiyeName = element.elementText("名称");
								System.out.println(qiyeName);
							}
							//文件迁移到备份文件夹
//							FileUtils.delFile(file.getAbsolutePath());
							FileUtils.moveFile(file.getAbsolutePath(), backupFile + file.getName());
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
