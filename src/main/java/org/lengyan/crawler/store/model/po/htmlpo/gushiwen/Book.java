package org.lengyan.crawler.store.model.po.htmlpo.gushiwen;

import java.util.Date;

import org.lengyan.crawler.annotation.html.Href;
import org.lengyan.crawler.annotation.html.HtmlField;
import org.lengyan.crawler.annotation.html.Image;
import org.lengyan.crawler.annotation.html.Text;
import org.lengyan.crawler.spider.HtmlBean;

/**
 * 古诗文-名句
 * @author kangtiancheng
 * @date 2017年6月20日
 */
public class Book implements HtmlBean{

	private static final long serialVersionUID = -8364620467125905345L;

	private Long id;
	
	@Text
	@HtmlField(cssPath=".cont p:eq(1) a b")
    private String bookName;

	@Href
	@HtmlField(cssPath=".cont p:eq(1) a")
    private String bookUrl;

	@Image
	@HtmlField(cssPath=".cont .divimg a img")
    private String bookImg;

	@Text
	@HtmlField(cssPath=".cont p:eq(2)")
    private String bookDesc;

	@Text
	@HtmlField(cssPath=".tool .good a span")
    private Integer pointNumber;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl == null ? null : bookUrl.trim();
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg == null ? null : bookImg.trim();
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc == null ? null : bookDesc.trim();
    }

    public Integer getPointNumber() {
        return pointNumber;
    }

    public void setPointNumber(Integer pointNumber) {
        this.pointNumber = pointNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
