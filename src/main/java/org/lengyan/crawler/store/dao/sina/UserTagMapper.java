package org.lengyan.crawler.store.dao.sina;

import org.lengyan.crawler.store.model.po.htmlpo.sina.UserTag;

public interface UserTagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserTag record);

    int insertSelective(UserTag record);

    UserTag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserTag record);

    int updateByPrimaryKey(UserTag record);
}