package org.lengyan.crawler.demo.jd;

import java.util.List;

import org.lengyan.crawler.annotation.HtmlField;
import org.lengyan.crawler.annotation.Text;
import org.lengyan.crawler.spider.HrefBean;
import org.lengyan.crawler.spider.HtmlBean;

public class Category implements HtmlBean {

	private static final long serialVersionUID = 3018760488621382659L;

	@Text
	@HtmlField(cssPath="dt a")
	private String parentName;
	
	@HtmlField(cssPath="dd a")
	private List<HrefBean> categorys;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<HrefBean> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<HrefBean> categorys) {
		this.categorys = categorys;
	}
	
}
