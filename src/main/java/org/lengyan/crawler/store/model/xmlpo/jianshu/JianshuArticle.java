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
public class JianshuArticle implements XmlBean {

	private static final long serialVersionUID = 4824489539648656524L;

	private Long id;

	@XmlField(xPath="/headimgurl")
    private String headimgUrl;//头像
	
	@XmlField(xPath="/username")
    private String userName;//用户名

	@XmlField(xPath="/userurl")
    private String userUrl;//用户url
	
	@XmlField(xPath="/title")
	private String title;//标题
	
	@XmlField(xPath="/articleurl")
	private String articleUrl;//文章标题
	
	@XmlField(xPath="/abstract")
	private String abstractContent;//内容摘要
	
	@XmlField(xPath="/readcount")
	private Integer readCount;//阅读量
	
	@XmlField(xPath="/commentcount")
	private Integer commentCount;//评论数
	
	@XmlField(xPath="/approvecount")
	private Integer approveCount;//点赞数

    private Date createTime;//创建时间

    private Date updateTime;//更新时间
    
    private Date articleCreateTime;

    private String content;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getHeadimgUrl() {
		return headimgUrl;
	}

	public void setHeadimgUrl(String headimgUrl) {
		this.headimgUrl = headimgUrl;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserUrl() {
		return userUrl;
	}

	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getArticleUrl() {
		return articleUrl;
	}
	
	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}

	public String getAbstractContent() {
		return abstractContent;
	}

	public void setAbstractContent(String abstractContent) {
		this.abstractContent = abstractContent;
	}
	
	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	
	public Integer getApproveCount() {
		return approveCount;
	}

	public void setApproveCount(Integer approveCount) {
		this.approveCount = approveCount;
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

	public Date getArticleCreateTime() {
		return articleCreateTime;
	}

	public void setArticleCreateTime(Date articleCreateTime) {
		this.articleCreateTime = articleCreateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}