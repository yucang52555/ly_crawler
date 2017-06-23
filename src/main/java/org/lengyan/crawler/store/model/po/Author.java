package org.lengyan.crawler.store.model.po;

import java.util.Date;

import org.lengyan.crawler.annotation.Href;
import org.lengyan.crawler.annotation.HtmlField;
import org.lengyan.crawler.annotation.Image;
import org.lengyan.crawler.annotation.Text;
import org.lengyan.crawler.spider.HtmlBean;

/**
 * 古诗文作者
 * @author kangtiancheng
 * @date 2017年6月19日
 */
public class Author implements HtmlBean{

	private static final long serialVersionUID = -6171990435798033685L;
	
	private Long id;
	
	@Text
	@HtmlField(cssPath=".cont p:first-of-type a b")
	private String authorName;
	
	@Text
	@HtmlField(cssPath=".cont p:last-of-type")
	private String authorDesc;
	
	@Image
	@HtmlField(cssPath=".cont .divimg a img")
	private String authorImg;

	@Text
	@HtmlField(cssPath=".tool .good a span")
	private Integer pointNumber;
	
	private String authorDynasty;
	
	@Href
	@HtmlField(cssPath=".cont p:first-of-type a")
	private String authorUrl;
	
	private Date createTime;
	
	private Date updateTime;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	public String getAuthorDesc() {
		return authorDesc;
	}
	
	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}
	
	public String getAuthorImg() {
		return authorImg;
	}
	
	public void setAuthorImg(String authorImg) {
		this.authorImg = authorImg;
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
	
	public String getAuthorDynasty() {
		return authorDynasty;
	}

	public void setAuthorDynasty(String authorDynasty) {
		this.authorDynasty = authorDynasty;
	}

	public String getAuthorUrl() {
		return authorUrl;
	}

	public void setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
	}
}
