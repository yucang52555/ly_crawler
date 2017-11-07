package org.lengyan.crawler.store.model.po.htmlpo.gushiwen;

import java.util.Date;

import org.lengyan.crawler.annotation.html.Href;
import org.lengyan.crawler.annotation.html.HtmlField;
import org.lengyan.crawler.annotation.html.Text;
import org.lengyan.crawler.spider.HtmlBean;

/**
 * 古诗文-名句
 * @author kangtiancheng
 * @date 2017年6月20日
 */
public class Minju implements HtmlBean{

	private static final long serialVersionUID = -8364620467125905345L;

	private Long id;
	
	@Text
	@HtmlField(cssPath="a:eq(0)")
	private String minjuContent;
	
	@Href
	@HtmlField(cssPath="a:eq(0)")
	private String minjuUrl;
	
	@Text
	@HtmlField(cssPath="a:eq(2)")
	private String sourcePoetry;
	
	@Href
	@HtmlField(cssPath="a:eq(2)")
	private String sourceUrl;
	
	private Date createTime;
	
	private Date updateTime;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMinjuContent() {
		return minjuContent;
	}
	
	public void setMinjuContent(String minjuContent) {
		this.minjuContent = minjuContent;
	}
	
	public String getMinjuUrl() {
		return minjuUrl;
	}
	
	public void setMinjuUrl(String minjuUrl) {
		this.minjuUrl = minjuUrl;
	}
	
	public String getSourcePoetry() {
		return sourcePoetry;
	}
	
	public void setSourcePoetry(String sourcePoetry) {
		this.sourcePoetry = sourcePoetry;
	}
	
	public String getSourceUrl() {
		return sourceUrl;
	}
	
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
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
