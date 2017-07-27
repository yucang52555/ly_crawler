package org.lengyan.crawler.store.service.impl;

import org.lengyan.crawler.store.dao.GushiwenChapterMapper;
import org.lengyan.crawler.store.model.po.Chapter;
import org.lengyan.crawler.store.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("chapterService")
public class ChapterServiceImpl implements IChapterService {
	
	@Autowired
	private GushiwenChapterMapper chapterMapper;

	@Override
	public void saveChapter(Chapter chapter) {
		chapterMapper.insertSelective(chapter);
	}
}
