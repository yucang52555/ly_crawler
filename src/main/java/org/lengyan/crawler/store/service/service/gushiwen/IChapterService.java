package org.lengyan.crawler.store.service.service.gushiwen;

import org.lengyan.crawler.store.model.po.htmlpo.gushiwen.GushiwenChapterWithBLOBs;

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
	public void saveChapter(GushiwenChapterWithBLOBs chapter);

	/**
	 * 更新章节信息
	 * @param chapter
	 * @param charpterId
	 */
	public void updateChapter(GushiwenChapterWithBLOBs chapter);
	
}
