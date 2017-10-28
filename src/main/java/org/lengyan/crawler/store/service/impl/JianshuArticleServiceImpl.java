package org.lengyan.crawler.store.service.impl;

import org.lengyan.crawler.store.dao.jianshu.JianshuArticleMapper;
import org.lengyan.crawler.store.model.xmlpo.jianshu.JianshuArticle;
import org.lengyan.crawler.store.service.IJianshuArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("jianshuArticleService")
public class JianshuArticleServiceImpl implements IJianshuArticleService {
	
	@Autowired
	private JianshuArticleMapper jianshuArticleMapper;

	@Override
	public void saveArticle(JianshuArticle jianshuArticle) {
		jianshuArticleMapper.insertSelective(jianshuArticle);
	}

	@Override
	public JianshuArticle selectArticleByUrl(String articleUrl) {
		return jianshuArticleMapper.selectArticleByUrl(articleUrl);
	}

	@Override
	public void updateArticle(JianshuArticle jianshuArticle) {
		jianshuArticleMapper.updateByPrimaryKeySelective(jianshuArticle);
	}
}
