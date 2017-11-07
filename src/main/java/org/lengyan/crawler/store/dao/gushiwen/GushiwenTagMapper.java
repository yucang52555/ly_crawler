package org.lengyan.crawler.store.dao.gushiwen;

import java.util.List;

import org.lengyan.crawler.store.model.po.htmlpo.gushiwen.Tag;

public interface GushiwenTagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

	List<Tag> selectAllTags();
}