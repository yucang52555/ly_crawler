package org.lengyan.crawler.store.dao.gushiwen;

import org.lengyan.crawler.store.model.po.htmlpo.gushiwen.Minju;

public interface GushiwenMinjuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Minju record);

    int insertSelective(Minju record);

    Minju selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Minju record);

    int updateByPrimaryKey(Minju record);
}