package org.lengyan.crawler.store.service.service.jianshu;

import java.util.List;

import org.lengyan.crawler.store.model.po.xmlpo.jianshu.JianshuArticle;

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

	/**
	 * 查询文章列表
	 * @return
	 */
	public List<JianshuArticle> selectAllUserList();
	
}
