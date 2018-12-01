package org.lengyan.crawler.entry.xml.qicha;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.lengyan.crawler.spider.XmlBean;
import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaQiye;
import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaVip;
import org.lengyan.crawler.store.service.service.qicha.IQichaQiyeService;
import org.lengyan.crawler.store.service.service.qicha.IQichaVipService;
import org.lengyan.crawler.store.service.serviceimpl.qicha.QichaQiyeServiceImpl;
import org.lengyan.crawler.store.service.serviceimpl.qicha.QichaVipServiceImpl;
import org.lengyan.crawler.utils.CommonUtils;
import org.lengyan.crawler.utils.FileUtils;
import org.lengyan.crawler.utils.PropertiesUtil;
import org.lengyan.crawler.utils.xml.XMLParseUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 企查查企业列表解析
 */
public class QichaQiyeMainPageParser implements XmlBean {

    private static final long serialVersionUID = -3348562176411453430L;

    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");

    static String sourceFile = PropertiesUtil.getProperties("crawler", "xml.qicha.source.qiyemain");
    static String backupFile = PropertiesUtil.getProperties("crawler", "xml.qicha.backup.qiyemain");
    static String errorFile = PropertiesUtil.getProperties("crawler", "xml.qicha.error.qiyemain");

    public static void main(String[] args) {
        //保存到数据库
        context.start();
        IQichaQiyeService qichaQiyeService = (QichaQiyeServiceImpl)context.getBean("qichaQiyeService");
        IQichaVipService qichaVipService = (QichaVipServiceImpl)context.getBean("qichaVipService");

        //1：循环获取文件列表
        while (!FileUtils.isEmpty(new File(sourceFile))) {
            try {
                //2：获取文件列表
                List<File> xmlFileList = FileUtils.getFiles(sourceFile);
                //3：循环文件列表
                for (File file : xmlFileList) {
                    try {
                        if (FileUtils.getFileName(file).startsWith("企查查企业主页")) {
                            System.out.println(file.getAbsolutePath());
                            //读取文件
                            Document document = XMLParseUtil.getDocument(file);
                            Element rootElement = document.getRootElement();
                            //解析原始url
                            String sourceUrl = (String) rootElement.element("fullpath").getData();
//                            Object urlObject = rootElement.element("fullpath").getData();
//                            if (urlObject instanceof CDATA) {
//                                CDATA urlData = (CDATA) urlObject;
//                                sourceUrl = urlData.getText();
//                                System.out.println(sourceUrl);
//                            }

                            Element qiyeElement = rootElement.element("qiye").element("item");
                            System.out.println(qiyeElement.asXML());
                            //保存企业详细信息
                            QichaQiye qichaQiye = new QichaQiye();
                            qichaQiye.setQiyeName(qiyeElement.elementText("qiyeName"));
                            qichaQiye.setQiyeUrl(sourceUrl);
                            qichaQiye.setQiyeStatus(qiyeElement.elementText("qiyeStatus"));
                            qichaQiye.setQiyeBirthday(qiyeElement.elementText("qiyeBirthday"));
                            qichaQiye.setQiyeMobile(qiyeElement.elementText("qiyeMobile"));
                            qichaQiye.setQiyeEmail(qiyeElement.elementText("qiyeEmail"));
                            qichaQiye.setQiyeMainurl(qiyeElement.elementText("qiyeMainurl"));
                            qichaQiye.setQiyeAddress(qiyeElement.elementText("qiyeAddress"));
                            qichaQiye.setQiyeUpdate(qiyeElement.elementText("qiyeUpdate"));

                            String readCount = qiyeElement.elementText("readCount").substring(4).trim();
                            qichaQiye.setReadCount(Integer.parseInt(readCount));
                            String followCount = qiyeElement.elementText("followCount").substring(4).trim();
                            qichaQiye.setFollowCount(Integer.parseInt(followCount));

                            qichaQiye.setSocialCreditCode(qiyeElement.elementText("socialCreditCode"));
                            qichaQiye.setTaxpayerNumber(qiyeElement.elementText("taxpayerNumber"));
                            qichaQiye.setRegisterNumber(qiyeElement.elementText("registerNumber"));
                            qichaQiye.setOrganizCode(qiyeElement.elementText("organizCode"));
                            qichaQiye.setLegalPerson(qiyeElement.elementText("legalPerson"));
                            qichaQiye.setRegisterCapital(qiyeElement.elementText("registerCapital"));
                            qichaQiye.setQiyeType(qiyeElement.elementText("qiyeType"));
                            qichaQiye.setQiyeScale(qiyeElement.elementText("qiyeScale"));
                            qichaQiye.setBusinessTerm(qiyeElement.elementText("businessTerm"));
                            qichaQiye.setRegisterAuthority(qiyeElement.elementText("registerAuthority"));
                            qichaQiye.setApproveDate(qiyeElement.elementText("approveDate"));
                            qichaQiye.setEnglishName(qiyeElement.elementText("englishName"));
                            qichaQiye.setQiyeRegion(qiyeElement.elementText("qiyeRegion"));
                            qichaQiye.setQiyeIndustry(qiyeElement.elementText("qiyeIndustry"));
                            qichaQiye.setBusinessScope(qiyeElement.elementText("businessScope"));
                            qichaQiyeService.saveQichaQiye(qichaQiye);

                            //保存企业主要成员信息
                            if (rootElement.element("qiye").element("vips") != null) {
                                List<Element> vipElements = rootElement.element("qiye").element("vips").elements("item");
                                for (int i = 0; i < CommonUtils.sizeOf(vipElements); i++) {
                                    QichaVip qichaVip = new QichaVip();
                                    qichaVip.setVipName(vipElements.get(i).elementText("vipName"));
                                    qichaVip.setVipPosition(vipElements.get(i).elementText("vipPosition"));
                                    qichaVipService.saveQichaVip(qichaVip);
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
