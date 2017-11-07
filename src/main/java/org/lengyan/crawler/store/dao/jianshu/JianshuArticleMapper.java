package org.lengyan.crawler.store.dao.jianshu;

import java.util.List;

import org.lengyan.crawler.store.model.po.xmlpo.jianshu.JianshuArticle;

public interface JianshuArticleMapper {
    int insert(JianshuArticle record);

    int insertSelective(JianshuArticle record);

    /**
     * 根据url查询文章信息
     * @param articleUrl
     * @return
     */
	JianshuArticle selectArticleByUrl(String articleUrl);
	
	int updateByPrimaryKeySelective(JianshuArticle record);

    int updateByPrimaryKey(JianshuArticle record);

    /**
     * 查询所有文章
     * @return
     */
	List<JianshuArticle> selectAllUserList();
}