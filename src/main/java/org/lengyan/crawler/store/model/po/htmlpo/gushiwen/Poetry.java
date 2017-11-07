package org.lengyan.crawler.store.model.po.htmlpo.gushiwen;

import java.util.Date;
import java.util.List;

import org.lengyan.crawler.annotation.html.Href;
import org.lengyan.crawler.annotation.html.Html;
import org.lengyan.crawler.annotation.html.HtmlField;
import org.lengyan.crawler.annotation.html.Text;
import org.lengyan.crawler.spider.HtmlBean;

/**
 * 古诗文-诗词
 * @author kangtiancheng
 * @date 2017年6月24日
 */
public class Poetry implements HtmlBean{
	
    private static final long serialVersionUID = 2597571202174743257L;

	private Long id;

    @Text
	@HtmlField(cssPath=".cont p:eq(1) a b")
    private String poetryName;

    @Href
	@HtmlField(cssPath=".cont p:eq(1) a")
    private String poetryUrl;

    @Text
	@HtmlField(cssPath=".cont .source a:eq(0)")
    private String dynasty;

    @Text
	@HtmlField(cssPath=".cont .source a:eq(2)")
    private String author;

    private Long authorId;

    @Html
	@HtmlField(cssPath=".cont .contson")
    private String poetryContent;

    @Text
	@HtmlField(cssPath=".tool .good a span")
    private Integer pointNumber;
    
    @Text
    @HtmlField(cssPath=".tag a")
	private List<Tag> tags;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoetryName() {
        return poetryName;
    }

    public void setPoetryName(String poetryName) {
        this.poetryName = poetryName == null ? null : poetryName.trim();
    }

    public String getPoetryUrl() {
        return poetryUrl;
    }

    public void setPoetryUrl(String poetryUrl) {
        this.poetryUrl = poetryUrl == null ? null : poetryUrl.trim();
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty == null ? null : dynasty.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getPoetryContent() {
        return poetryContent;
    }

    public void setPoetryContent(String poetryContent) {
        this.poetryContent = poetryContent == null ? null : poetryContent.trim();
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