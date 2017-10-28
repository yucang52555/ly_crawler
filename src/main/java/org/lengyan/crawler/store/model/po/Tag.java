package org.lengyan.crawler.store.model.po;

import java.util.Date;

import org.lengyan.crawler.annotation.html.Href;
import org.lengyan.crawler.annotation.html.HtmlField;
import org.lengyan.crawler.annotation.html.Text;
import org.lengyan.crawler.spider.HtmlBean;

/**
 * 古诗文标签
 * @author kangtiancheng
 * @date 2017年6月20日
 */
public class Tag implements HtmlBean{

	private static final long serialVersionUID = -8364620467125905345L;

	private Long id;
	
	@Text
	@HtmlField(cssPath="a")
	private String tagName;
	
	@Href
	@HtmlField(cssPath="a")
	private String tagUrl;
	
	private Date createTime;
	
	private Date updateTime;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTagName() {
		return tagName;
	}
	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public String getTagUrl() {
		return tagUrl;
	}

	public void setTagUrl(String tagUrl) {
		this.tagUrl = tagUrl;
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
