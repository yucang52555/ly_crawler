package org.lengyan.crawler.store.dao;

import org.lengyan.crawler.store.model.po.GushiwenChapter;
import org.lengyan.crawler.store.model.po.GushiwenChapterWithBLOBs;

public interface GushiwenChapterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GushiwenChapterWithBLOBs record);

    int insertSelective(GushiwenChapterWithBLOBs record);

    GushiwenChapterWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GushiwenChapterWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GushiwenChapterWithBLOBs record);

    int updateByPrimaryKey(GushiwenChapter record);
    
    int updateChapterContent(GushiwenChapterWithBLOBs chapter);
}