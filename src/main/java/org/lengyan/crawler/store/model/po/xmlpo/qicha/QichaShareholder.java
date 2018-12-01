package org.lengyan.crawler.store.model.po.xmlpo.qicha;

import java.util.Date;

public class QichaShareholder {
    private Long id;

    private String shName;

    private String qiyeName;

    private String shRatio;

    private String shContrib;

    private String shContribDate;

    private String shType;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShName() {
        return shName;
    }

    public void setShName(String shName) {
        this.shName = shName == null ? null : shName.trim();
    }

    public String getQiyeName() {
        return qiyeName;
    }

    public void setQiyeName(String qiyeName) {
        this.qiyeName = qiyeName == null ? null : qiyeName.trim();
    }

    public String getShRatio() {
        return shRatio;
    }

    public void setShRatio(String shRatio) {
        this.shRatio = shRatio == null ? null : shRatio.trim();
    }

    public String getShContrib() {
        return shContrib;
    }

    public void setShContrib(String shContrib) {
        this.shContrib = shContrib == null ? null : shContrib.trim();
    }

    public String getShContribDate() {
        return shContribDate;
    }

    public void setShContribDate(String shContribDate) {
        this.shContribDate = shContribDate == null ? null : shContribDate.trim();
    }

    public String getShType() {
        return shType;
    }

    public void setShType(String shType) {
        this.shType = shType == null ? null : shType.trim();
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