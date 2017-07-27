package org.lengyan.crawler.store.model.bo;

import java.util.List;

import org.lengyan.crawler.annotation.HtmlField;
import org.lengyan.crawler.annotation.Text;
import org.lengyan.crawler.store.model.po.Chapter;

/**
 * 大章
 * @author kangtiancheng
 * @date 2017年7月25日
 */
public class BigChapter {

	@Text
	@HtmlField(cssPath=".bookMl strong")
    private String charpterName;

	@HtmlField(cssPath="span")
    private List<Chapter> chapters;

	public String getCharpterName() {
		return charpterName;
	}

	public void setCharpterName(String charpterName) {
		this.charpterName = charpterName;
	}

	public List<Chapter> getChapters() {
		return chapters;
	}
	
	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

}