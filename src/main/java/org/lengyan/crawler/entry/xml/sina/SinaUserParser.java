package org.lengyan.crawler.entry.xml.sina;

import org.dom4j.Document;
import org.dom4j.Element;
import org.lengyan.crawler.annotation.xml.XmlField;
import org.lengyan.crawler.spider.XmlBean;
import org.lengyan.crawler.store.model.po.htmlpo.sina.UserTag;
import org.lengyan.crawler.store.model.po.xmlpo.jianshu.JianshuKeyword;
import org.lengyan.crawler.store.model.po.xmlpo.sina.SinaUser;
import org.lengyan.crawler.store.service.service.sina.ISinaUserService;
import org.lengyan.crawler.store.service.service.sina.IUserTagService;
import org.lengyan.crawler.store.service.serviceimpl.sina.SinaUserServiceImpl;
import org.lengyan.crawler.store.service.serviceimpl.sina.UserTagServiceImpl;
import org.lengyan.crawler.utils.FileUtils;
import org.lengyan.crawler.utils.PropertiesUtil;
import org.lengyan.crawler.utils.RegexUtils;
import org.lengyan.crawler.utils.xml.XMLParseUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.List;

/**
 * 新浪用户解析
 * Created by kangtiancheng on 2017/11/14.
 */
public class SinaUserParser implements XmlBean {

    private static final long serialVersionUID = -3348562176411353430L;

    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");

    static String sourceFile = PropertiesUtil.getProperties("crawler", "xml.sina.source.user");
    static String backupFile = PropertiesUtil.getProperties("crawler", "xml.sina.backup.user");
    static String errorFile = PropertiesUtil.getProperties("crawler", "xml.sina.error.user");

    @XmlField(xPath="//keyword/item")
    private List<SinaUser> sinaUsers;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        //保存到数据库
        context.start();
        ISinaUserService sinaUserService = (SinaUserServiceImpl)context.getBean("sinaUserService");

        //1：循环获取文件列表
        while (!FileUtils.isEmpty(new File(sourceFile))) {
            try {
                //2：获取文件列表
                List<File> xmlFileList = FileUtils.getFiles(sourceFile);
                //3：循环文件列表
                for (File file : xmlFileList) {
                    try {
                        if (FileUtils.getFileName(file).startsWith("新浪用户列表")) {
                            System.out.println(file.getAbsolutePath());
                            //读取文件
                            Document document = XMLParseUtil.getDocument(file);
                            Element rootElement = document.getRootElement();
                            List<Element> typeElements = rootElement.element("sinaUser").elements("item");
                            for (Element element : typeElements) {
                                System.out.println(element.asXML());
                                SinaUser sinaUser = new SinaUser();
                                sinaUser.setUserName(element.elementText("userName"));
                                sinaUser.setHeadUrl("http:" + element.elementText("headUrl"));
                                sinaUser.setUserUrl("http:" + element.elementText("userUrl"));
                                sinaUser.setApprove(element.elementText("approve"));
                                sinaUser.setMember(element.elementText("member"));
                                sinaUser.setGender(element.elementText("gender"));
                                sinaUser.setRecommendFlag(element.elementText("recommend"));
                                sinaUser.setFollowCount(Integer.parseInt(element.elementText("followCount")));
                                sinaUser.setFansCount(Integer.parseInt(element.elementText("fansCount")));
                                sinaUser.setWeiboCount(Integer.parseInt(element.elementText("weiboCount")));
                                sinaUser.setAddress(element.elementText("address"));
                                sinaUser.setAbstractContent(RegexUtils.specialCharacterFilter(element.elementText("abstractContent")));
                                sinaUser.setTags(element.elementText("tags"));
                                sinaUserService.saveSinaUser(sinaUser);
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
