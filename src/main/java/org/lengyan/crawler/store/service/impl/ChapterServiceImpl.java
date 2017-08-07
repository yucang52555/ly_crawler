package org.lengyan.crawler.store.service.impl;

import org.lengyan.crawler.store.dao.GushiwenChapterMapper;
import org.lengyan.crawler.store.model.po.GushiwenChapterWithBLOBs;
import org.lengyan.crawler.store.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("chapterService")
public class ChapterServiceImpl implements IChapterService {
	
	@Autowired
	private GushiwenChapterMapper chapterMapper;

	@Override
	public void saveChapter(GushiwenChapterWithBLOBs chapter) {
		chapterMapper.insertSelective(chapter);
	}

	@Override
	public void updateChapter(GushiwenChapterWithBLOBs chapter) {
		chapterMapper.updateChapterContent(chapter);
	}
}
