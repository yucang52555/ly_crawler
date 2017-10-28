package org.lengyan.crawler.store.model.xmlpo.jianshu;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.lengyan.crawler.annotation.xml.XmlField;
import org.lengyan.crawler.spider.XmlBean;

/**
 * 简书热搜关键词
 * @author kangtiancheng
 * @date 2017年10月9日
 */
public class JianshuKeyword implements XmlBean {
    private static final long serialVersionUID = -6229766204835327420L;

	private Long id;

	@XmlField(xPath="/keyname")
    private String keyword;//关键词
	
	@XmlField(xPath="/keyurl")
    private String keywordUrl;//关键词URL

    private Integer collectTimes;//采集次数

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getKeywordUrl() {
        return keywordUrl;
    }

    public void setKeywordUrl(String keywordUrl) {
        this.keywordUrl = keywordUrl == null ? null : keywordUrl.trim();
    }

    public Integer getCollectTimes() {
        return collectTimes;
    }

    public void setCollectTimes(Integer collectTimes) {
        this.collectTimes = collectTimes;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}