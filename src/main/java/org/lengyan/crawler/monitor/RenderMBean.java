package org.lengyan.crawler.monitor;

import org.lengyan.crawler.annotation.MBean;
import org.weakref.jmx.Managed;

import com.alibaba.fastjson.JSON;

@MBean("render")
public class RenderMBean {
	
	private String statistics;

	@Managed
	public String getStatistics() {
		return statistics;
	}

	@Managed
	public void setStatistics(String statistics) {
		this.statistics = statistics;
	}
	
	@Managed
	public void refresh() {
		setStatistics(JSON.toJSONString(RenderMonitor.getStatistics()));
	}
}
