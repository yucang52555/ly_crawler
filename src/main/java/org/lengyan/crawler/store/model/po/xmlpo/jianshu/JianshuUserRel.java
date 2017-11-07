package org.lengyan.crawler.store.model.po.xmlpo.jianshu;

import java.util.Date;

public class JianshuUserRel {
    private Long id;

    private Long followUserid;

    private String followUserName;

    private String followUserUrl;

    private Long fansUserid;

    private String fansUserName;

    private String fansUserUrl;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollowUserid() {
        return followUserid;
    }

    public void setFollowUserid(Long followUserid) {
        this.followUserid = followUserid;
    }

    public String getFollowUserName() {
        return followUserName;
    }

    public void setFollowUserName(String followUserName) {
        this.followUserName = followUserName == null ? null : followUserName.trim();
    }

    public String getFollowUserUrl() {
        return followUserUrl;
    }

    public void setFollowUserUrl(String followUserUrl) {
        this.followUserUrl = followUserUrl == null ? null : followUserUrl.trim();
    }

    public Long getFansUserid() {
        return fansUserid;
    }

    public void setFansUserid(Long fansUserid) {
        this.fansUserid = fansUserid;
    }

    public String getFansUserName() {
        return fansUserName;
    }

    public void setFansUserName(String fansUserName) {
        this.fansUserName = fansUserName == null ? null : fansUserName.trim();
    }

    public String getFansUserUrl() {
        return fansUserUrl;
    }

    public void setFansUserUrl(String fansUserUrl) {
        this.fansUserUrl = fansUserUrl == null ? null : fansUserUrl.trim();
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