package org.lengyan.crawler.entry.xml.qicha;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.lengyan.crawler.spider.XmlBean;
import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaQiye;
import org.lengyan.crawler.store.service.service.qicha.IQichaQiyeService;
import org.lengyan.crawler.store.service.serviceimpl.qicha.QichaQiyeServiceImpl;
import org.lengyan.crawler.utils.FileUtils;
import org.lengyan.crawler.utils.PropertiesUtil;
import org.lengyan.crawler.utils.xml.XMLParseUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.List;

/**
 * 企查查附近企业列表解析
 */
public class QichaQiyeNearbyListParser implements XmlBean {

    private static final long serialVersionUID = -3348562176411453430L;

    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");

    static String sourceFile = PropertiesUtil.getProperties("crawler", "xml.qicha.source.qiyenearby");
    static String backupFile = PropertiesUtil.getProperties("crawler", "xml.qicha.backup.qiyenearby");
    static String errorFile = PropertiesUtil.getProperties("crawler", "xml.qicha.error.qiyenearby");

    public static void main(String[] args) {
        //保存到数据库
        context.start();
        IQichaQiyeService qichaQiyeService = (QichaQiyeServiceImpl)context.getBean("qichaQiyeService");
        String baseUrl = "http://www.qichacha.com/firm_";

        //1：循环获取文件列表
        while (!FileUtils.isEmpty(new File(sourceFile))) {
            try {
                //2：获取文件列表
                List<File> xmlFileList = FileUtils.getFiles(sourceFile);
                //3：循环文件列表
                for (File file : xmlFileList) {
                    try {
                        System.out.println(file.getAbsolutePath());
                        //读取文件
                        Document document = XMLParseUtil.getDocument(file);
                        Element rootElement = document.getRootElement();
                        List<Element> typeElements = rootElement.element("qiye").elements("item");
                        for (Element element : typeElements) {
                            System.out.println(element.asXML());
                            QichaQiye qichaQiye = new QichaQiye();
                            qichaQiye.setQiyeName(element.elementText("qiye_name"));
                            String qiyeKeyElementText = element.elementText("qiye_url");
                            String qiyeKey = StringUtils.substring(qiyeKeyElementText, qiyeKeyElementText.indexOf("'") + 1, qiyeKeyElementText.lastIndexOf("'"));
                            qichaQiye.setQiyeUrl(baseUrl + qiyeKey + ".html");
                            qichaQiye.setQiyeStatus(element.elementText("qiye_status"));
                            qichaQiye.setLegalPerson(element.elementText("legal_person"));
//                                qichaQiye.setQiyeBirthday(element.elementText("pubtime"));
                            qichaQiye.setRegisterCapital(element.elementText("register_capital"));
                            qichaQiye.setQiyeIndustry(element.elementText("qiye_industry"));
                            qichaQiyeService.saveQichaQiye(qichaQiye);
                        }
                        //文件迁移到备份文件夹
//                      FileUtils.delFile(file.getAbsolutePath());
                        FileUtils.moveFile(file.getAbsolutePath(), backupFile + file.getName());
                    } catch (Exception e) {
                        //文件迁移到异常文件夹
                        try {
                            FileUtils.moveFile(file.getAbsolutePath(), errorFile + file.getName());
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        e.printStackTrace();
                    }
                }
                Thread.sleep(3*60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
