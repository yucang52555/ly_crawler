package org.lengyan.crawler.store.model.po.apipo.bsbdj;

public class BsbdjUser {
    private Long id;

    private Integer idGrade;

    private Integer friendCount;

    private String url;

    private String idType;

    private String idVerified;

    private String screenName;

    private String avatarUrl;

    private String userName;

    private String isVip;

    private String likeCount;

    private String biography;

    private String gender;

    private Integer fansCount;

    private Integer postCount;

    private String location;

    private String idExpValue;

    private Integer viewCount;

    private String idVerifiedInfo;

    private Integer followCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdGrade() {
        return idGrade;
    }

    public void setIdGrade(Integer idGrade) {
        this.idGrade = idGrade;
    }

    public Integer getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(Integer friendCount) {
        this.friendCount = friendCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType == null ? null : idType.trim();
    }

    public String getIdVerified() {
        return idVerified;
    }

    public void setIdVerified(String idVerified) {
        this.idVerified = idVerified == null ? null : idVerified.trim();
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName == null ? null : screenName.trim();
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip == null ? null : isVip.trim();
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount == null ? null : likeCount.trim();
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography == null ? null : biography.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getIdExpValue() {
        return idExpValue;
    }

    public void setIdExpValue(String idExpValue) {
        this.idExpValue = idExpValue == null ? null : idExpValue.trim();
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getIdVerifiedInfo() {
        return idVerifiedInfo;
    }

    public void setIdVerifiedInfo(String idVerifiedInfo) {
        this.idVerifiedInfo = idVerifiedInfo == null ? null : idVerifiedInfo.trim();
    }

    public Integer getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
    }
}