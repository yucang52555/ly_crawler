package org.lengyan.crawler.store.model.po.xmlpo.sina;

public class SinaUser {
    private Long id;

    private String userName;

    private String headUrl;

    private String userUrl;

    private String approve;

    private String member;

    private String gender;

    private String recommendFlag;

    private Integer followCount;

    private Integer fansCount;

    private Integer weiboCount;

    private String address;

    private String abstractContent;

    private String tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl == null ? null : headUrl.trim();
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl == null ? null : userUrl.trim();
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve == null ? null : approve.trim();
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member == null ? null : member.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(String recommendFlag) {
        this.recommendFlag = recommendFlag == null ? null : recommendFlag.trim();
    }

    public Integer getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public Integer getWeiboCount() {
        return weiboCount;
    }

    public void setWeiboCount(Integer weiboCount) {
        this.weiboCount = weiboCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent == null ? null : abstractContent.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }
}