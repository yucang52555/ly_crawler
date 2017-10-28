package org.lengyan.crawler.store.service;

import org.lengyan.crawler.store.model.xmlpo.jianshu.JianshuArticle;

/**
 * 简书文章服务
 * @author kangtiancheng
 * @date 2017年10月28日
 */
public interface IJianshuArticleService {
	
	/**
	 * 保存简书文章
	 * @param jianshuArticle
	 */
	public void saveArticle(JianshuArticle jianshuArticle);

	/**
	 * 根据url查询文件
	 * @param articleUrl
	 * @return
	 */
	public JianshuArticle selectArticleByUrl(String articleUrl);

	/**
	 * 更新文章信息
	 * @param jianshuArticle
	 */
	public void updateArticle(JianshuArticle jianshuArticle);
	
}
