package org.lengyan.crawler.entry.xml.land;

import org.dom4j.Document;
import org.dom4j.Element;
import org.lengyan.crawler.annotation.xml.XmlField;
import org.lengyan.crawler.spider.XmlBean;
import org.lengyan.crawler.store.model.po.htmlpo.landtransfer.LandTrade;
import org.lengyan.crawler.store.service.service.landtransfer.ILandTradeService;
import org.lengyan.crawler.store.service.serviceimpl.landtransfer.LandTradeServiceImpl;
import org.lengyan.crawler.utils.FileUtils;
import org.lengyan.crawler.utils.PropertiesUtil;
import org.lengyan.crawler.utils.xml.XMLParseUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 土地数据解析
 * @author kangtiancheng
 * @date 2018年11月6日
 */
public class LandTradeParser implements XmlBean {
	
	private static final long serialVersionUID = -3348562176411353430L;
	
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");
	
	static String sourceFile = PropertiesUtil.getProperties("crawler", "xml.land.source.trade");
	static String backupFile = PropertiesUtil.getProperties("crawler", "xml.land.backup.trade");
	static String errorFile = PropertiesUtil.getProperties("crawler", "xml.land.error.trade");
	
	@XmlField(xPath="//结果公告/item")
	private List<LandTrade> landTradeList;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		//
		String baseUrl = "http://www.landchina.com/";
		//保存到数据库
		context.start();
		ILandTradeService landTradeService = (LandTradeServiceImpl)context.getBean("landTradeService");
		
		//1：循环获取文件列表
		while (!FileUtils.isEmpty(new File(sourceFile))) {
			try {
				//2：获取文件列表
				List<File> xmlFileList = FileUtils.getFiles(sourceFile);
				//3：循环文件列表
				for (File file : xmlFileList) {
					try {
						if (FileUtils.getFileName(file).startsWith("中国土地")) {
							System.out.println(file.getAbsolutePath());
							//读取文件
							Document document = XMLParseUtil.getDocument(file);
							Element rootElement = document.getRootElement();
							List<Element> tradeElements = rootElement.element("结果公告").elements("item");
//							List<Node> keyElements = document.selectNodes("//keyword/item");
							for (Element element : tradeElements) {
								System.out.println(element.asXML());
								String detailUrl = element.elementText("详情url");
								LandTrade landTrade = landTradeService.selectLandTradeByDetailUrl(detailUrl);
								if (landTrade == null) {
									landTrade = new LandTrade();
									landTrade.setLandTradeurl(baseUrl + detailUrl);
									landTrade.setLandArea(element.elementText("行政区"));
									landTrade.setLandPosition(element.elementText("土地坐落"));
									landTrade.setLandAcreage(element.elementText("总面积"));
									landTrade.setLandPurpose(element.elementText("土地用途"));
									landTrade.setLandSupplymode(element.elementText("供应方式"));
									landTrade.setLandTradedate(element.elementText("签订日期"));
									landTrade.setCreateTime(new Date());
									System.out.println(landTrade.toString());
									landTradeService.saveLandTrade(landTrade);
								} else {
									landTrade.setUpdateTime(new Date());
									landTradeService.updateLandTrade(landTrade);
								}
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
