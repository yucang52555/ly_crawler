package org.lengyan.crawler.store.service;

import org.lengyan.crawler.store.model.po.Chapter;

/**
 * 古诗文章节服务
 * @author kangtiancheng
 * @date 2017年6月23日
 */
public interface IChapterService {
	
	/**
	 * 保存章节信息
	 * @param GSWAuthor
	 * @return
	 */
	public void saveChapter(Chapter chapter);
	
}
