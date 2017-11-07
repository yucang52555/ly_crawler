package org.lengyan.crawler.store.dao.gushiwen;

import org.lengyan.crawler.store.model.po.htmlpo.gushiwen.Poetry;

public interface GushiwenPoetryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Poetry record);

    int insertSelective(Poetry record);

    Poetry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Poetry record);

    int updateByPrimaryKey(Poetry record);
}