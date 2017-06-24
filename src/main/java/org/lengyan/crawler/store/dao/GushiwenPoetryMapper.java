package org.lengyan.crawler.store.dao;

import org.lengyan.crawler.store.model.po.Poetry;

public interface GushiwenPoetryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Poetry record);

    int insertSelective(Poetry record);

    Poetry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Poetry record);

    int updateByPrimaryKey(Poetry record);
}