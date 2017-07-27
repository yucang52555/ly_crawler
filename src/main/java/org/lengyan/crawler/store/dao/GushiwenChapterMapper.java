package org.lengyan.crawler.store.dao;

import org.lengyan.crawler.store.model.po.Chapter;

public interface GushiwenChapterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Chapter record);

    int insertSelective(Chapter record);

    Chapter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Chapter record);

    int updateByPrimaryKey(Chapter record);
}