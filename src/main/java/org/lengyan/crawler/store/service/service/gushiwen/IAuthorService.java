package org.lengyan.crawler.store.service.service.gushiwen;

import java.util.List;

import org.lengyan.crawler.store.model.po.htmlpo.gushiwen.Author;

/**
 * 古诗文作者服务
 * @author kangtiancheng
 * @date 2017年6月19日
 */
public interface IAuthorService {
	
	/**
	 * 保存作者信息
	 * @param GSWAuthor
	 * @return
	 */
	public void saveAuthors(List<Author> GSWAuthor);
	
}
