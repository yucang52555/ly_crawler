package org.lengyan.crawler.store.model.po.xmlpo.qicha;

import java.util.Date;

public class QichaVip {
    private Long id;

    private String vipName;

    private String vipPosition;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName == null ? null : vipName.trim();
    }

    public String getVipPosition() {
        return vipPosition;
    }

    public void setVipPosition(String vipPosition) {
        this.vipPosition = vipPosition == null ? null : vipPosition.trim();
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