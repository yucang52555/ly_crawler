package org.lengyan.crawler.entry.xml.sina;

import org.dom4j.Document;
import org.dom4j.Element;
import org.lengyan.crawler.annotation.xml.XmlField;
import org.lengyan.crawler.spider.XmlBean;
import org.lengyan.crawler.store.model.po.xmlpo.sina.SinaUser;
import org.lengyan.crawler.store.model.po.xmlpo.sina.SinaWeibo;
import org.lengyan.crawler.store.service.service.sina.ISinaUserService;
import org.lengyan.crawler.store.service.service.sina.ISinaWeiboService;
import org.lengyan.crawler.store.service.serviceimpl.sina.SinaUserServiceImpl;
import org.lengyan.crawler.store.service.serviceimpl.sina.SinaWeiboServiceImpl;
import org.lengyan.crawler.utils.CommonUtils;
import org.lengyan.crawler.utils.FileUtils;
import org.lengyan.crawler.utils.PropertiesUtil;
import org.lengyan.crawler.utils.RegexUtils;
import org.lengyan.crawler.utils.xml.XMLParseUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 新浪用户主页解析
 * Created by kangtiancheng on 2017/11/14.
 */
public class SinaUserMainPageParser implements XmlBean {

    private static final long serialVersionUID = -3348562176411453430L;

    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");

    static String sourceFile = PropertiesUtil.getProperties("crawler", "xml.sina.source.usermain");
    static String backupFile = PropertiesUtil.getProperties("crawler", "xml.sina.backup.usermain");
    static String errorFile = PropertiesUtil.getProperties("crawler", "xml.sina.error.usermain");
    
    @XmlField(xPath="//keyword/item")
    private List<SinaUser> sinaUsers;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        //保存到数据库
        context.start();
        ISinaUserService sinaUserService = (SinaUserServiceImpl)context.getBean("sinaUserService");

        ISinaWeiboService sinaWeiboService = (SinaWeiboServiceImpl)context.getBean("sinaWeiboService");

        //1：循环获取文件列表
        while (!FileUtils.isEmpty(new File(sourceFile))) {
            try {
                //2：获取文件列表
                List<File> xmlFileList = FileUtils.getFiles(sourceFile);
                //3：循环文件列表
                for (File file : xmlFileList) {
                    try {
                        if (FileUtils.getFileName(file).startsWith("新浪用户主页")) {
                            System.out.println(file.getAbsolutePath());
                            //读取文件
                            Document document = XMLParseUtil.getDocument(file);
                            Element rootElement = document.getRootElement();
                            Element userElement = rootElement.element("sinaUser").element("item");
                            Date now = new Date();
                            //用户信息处理
                            SinaUser sinaUser = new SinaUser();
                            sinaUser.setUserName(userElement.elementText("userName"));
                            sinaUser.setAbstractContent(RegexUtils.specialCharacterFilter(userElement.elementText("abstractContent")));
                            sinaUser.setFollowCount(Integer.parseInt(userElement.elementText("followCount")));
                            sinaUser.setFansCount(Integer.parseInt(userElement.elementText("fansCount")));
                            sinaUser.setWeiboCount(Integer.parseInt(userElement.elementText("weiboCount")));
                            sinaUser.setLevel(userElement.elementText("level"));
                            sinaUser.setBirthday(RegexUtils.specialCharacterFilter(userElement.elementText("birthday")));
                            sinaUserService.updateSinaUserByUserName(sinaUser);
                            //微博信息列表处理
                            List<Element> weiboElements = userElement.element("weibos").elements("item");
                            if (CommonUtils.sizeOf(weiboElements) > 0) {
                                for (Element weiboElement : weiboElements) {
                                    System.out.println(weiboElement.asXML());
                                    SinaWeibo sinaWeibo = new SinaWeibo();
                                    sinaWeibo.setCreateTime(now);
                                    sinaWeibo.setPubTime(weiboElement.elementText("pubTime"));
                                    sinaWeibo.setPubContent(RegexUtils.specialCharacterFilter(weiboElement.elementText("pubContent")));
                                    sinaWeibo.setWeiboUrl("https://weibo.com" + weiboElement.elementText("weiboUrl"));
                                    sinaWeiboService.saveSinaWeibo(sinaWeibo);
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
