package org.lengyan.crawler.store.service;

import java.util.List;

import org.lengyan.crawler.store.model.xmlpo.jianshu.JianshuKeyword;

/**
 * 古诗文标签服务
 * @author kangtiancheng
 * @date 2017年6月20日
 */
public interface IJianshuKeywordService {
	
	/**
	 * 保存简书关键词
	 * @param jianshuKeyword
	 */
	public void saveKeyword(JianshuKeyword jianshuKeyword);
	
	/**
	 * 查询简书关键词
	 * @param GSWAuthor
	 * @return
	 */
	public JianshuKeyword selectJianshuKeyword(String keyword);

	/**
	 * 更新关键词
	 * @param jianshuKeyword
	 */
	public void updateKeyword(JianshuKeyword jianshuKeyword);

	/**
	 * 获取所有关键词列表
	 * @return
	 */
	public List<JianshuKeyword> selectAllJianshuKeyword();
	
}
