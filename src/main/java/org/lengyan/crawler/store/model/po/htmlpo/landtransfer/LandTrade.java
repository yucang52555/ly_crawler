package org.lengyan.crawler.store.model.po.htmlpo.landtransfer;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.lengyan.crawler.annotation.html.Href;
import org.lengyan.crawler.annotation.html.HtmlField;
import org.lengyan.crawler.annotation.html.Text;
import org.lengyan.crawler.spider.HtmlBean;

import java.util.Date;

/**
 * 土地交易结果
 * @author kangtiancheng
 * @date 2018年10月19日
 */
public class LandTrade implements HtmlBean{

	private static final long serialVersionUID = -6171990435798033685L;
	
	private Long id;

	@Text
	@HtmlField(cssPath="td:eq(0)")
	private String landCno;//采集序号
	
	@Text
	@HtmlField(cssPath="td:eq(2)")
	private String landPosition;//土地坐落位置
	
	@Text
	@HtmlField(cssPath="td:eq(1)")
	private String landArea;//行政区
	
	@Text
	@HtmlField(cssPath="td:eq(3)")
	private String landAcreage;//总面积

	@Text
	@HtmlField(cssPath="td:eq(4)")
	private String landPurpose;//土地用途
	
	@Text
	@HtmlField(cssPath="td:eq(5)")
	private String landSupplymode;//土地供应方式

	@Text
	@HtmlField(cssPath="td:eq(6)")
	private String landTradedate;//签约日期

	private String landCurl;//数据采集url

	@Href
	@HtmlField(cssPath="td:eq(2) a")
	private String landTradeurl;//交易详情url
	
	private Date createTime;//创建时间
	
	private Date updateTime;//更新时间
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getLandCno() {
		return landCno;
	}

	public void setLandCno(String landCno) {
		this.landCno = landCno;
	}

	public String getLandPosition() {
		return landPosition;
	}

	public void setLandPosition(String landPosition) {
		this.landPosition = landPosition;
	}

	public String getLandArea() {
		return landArea;
	}

	public void setLandArea(String landArea) {
		this.landArea = landArea;
	}

	public String getLandAcreage() {
		return landAcreage;
	}

	public void setLandAcreage(String landAcreage) {
		this.landAcreage = landAcreage;
	}

	public String getLandPurpose() {
		return landPurpose;
	}

	public void setLandPurpose(String landPurpose) {
		this.landPurpose = landPurpose;
	}

	public String getLandSupplymode() {
		return landSupplymode;
	}

	public void setLandSupplymode(String landSupplymode) {
		this.landSupplymode = landSupplymode;
	}

	public String getLandTradedate() {
		return landTradedate;
	}

	public void setLandTradedate(String landTradedate) {
		this.landTradedate = landTradedate;
	}

	public String getLandCurl() {
		return landCurl;
	}

	public void setLandCurl(String landCurl) {
		this.landCurl = landCurl;
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

	public String getLandTradeurl() {
		return landTradeurl;
	}

	public void setLandTradeurl(String landTradeurl) {
		this.landTradeurl = landTradeurl;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
