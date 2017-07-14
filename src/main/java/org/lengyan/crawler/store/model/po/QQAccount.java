package org.lengyan.crawler.store.model.po;

import java.util.Date;

import org.lengyan.crawler.annotation.Href;
import org.lengyan.crawler.annotation.HtmlField;
import org.lengyan.crawler.annotation.Text;
import org.lengyan.crawler.spider.HtmlBean;

/**
 * QQ号码
 * @author kangtiancheng
 * @date 2017年7月12日
 */
public class QQAccount implements HtmlBean{
	
	private static final long serialVersionUID = -5417375829596094104L;
    
    private Long id;

    @Text
	@HtmlField(cssPath="a")
    private String qqAccount;

    private String nickName;

    private String gender;

    private Integer age;

    private String country;

    private String province;

    private String address;

    private String mobile;

    private String telephone;

    private String email;

    private String starSign;

    private Integer todayVisitor;

    private Integer historyVisitor;

    @Href
	@HtmlField(cssPath="a")
    private String originUrl;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQqAccount() {
        return qqAccount;
    }

    public void setQqAccount(String qqAccount) {
        this.qqAccount = qqAccount == null ? null : qqAccount.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getStarSign() {
        return starSign;
    }

    public void setStarSign(String starSign) {
        this.starSign = starSign == null ? null : starSign.trim();
    }

    public Integer getTodayVisitor() {
        return todayVisitor;
    }

    public void setTodayVisitor(Integer todayVisitor) {
        this.todayVisitor = todayVisitor;
    }

    public Integer getHistoryVisitor() {
        return historyVisitor;
    }

    public void setHistoryVisitor(Integer historyVisitor) {
        this.historyVisitor = historyVisitor;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl == null ? null : originUrl.trim();
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