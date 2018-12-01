package org.lengyan.crawler.store.model.po.apipo.bsbdj;

public class BsbdjUserResult {
	String pageToken;
	String retcode;
	String hasNext;
	String appCode;
	String dataType;
	BsbdjUser[] data;
	
	public String getPageToken() {
		return pageToken;
	}
	
	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}
	
	public String getRetcode() {
		return retcode;
	}
	
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	
	public String getHasNext() {
		return hasNext;
	}
	
	public void setHasNext(String hasNext) {
		this.hasNext = hasNext;
	}
	
	public String getAppCode() {
		return appCode;
	}
	
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	
	public String getDataType() {
		return dataType;
	}
	
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	public BsbdjUser[] getData() {
		return data;
	}
	
	public void setData(BsbdjUser[] data) {
		this.data = data;
	}
}