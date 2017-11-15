package org.lengyan.crawler.store.model.bo.apibo;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.lengyan.crawler.store.model.po.apipo.juhe.JuheapiTohis;

/**
 * 历史上的今天
 * @author kangtiancheng
 * @date 2017年11月7日
 */
public class TodayHistoryBo {
	private String reason;
	
	private Integer error_code;
	
	private List<JuheapiTohis> result;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getError_code() {
		return error_code;
	}

	public void setError_code(Integer error_code) {
		this.error_code = error_code;
	}

	public List<JuheapiTohis> getResult() {
		return result;
	}

	public void setResult(List<JuheapiTohis> result) {
		this.result = result;
	}

	public TodayHistoryBo() {
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
