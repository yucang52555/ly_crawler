package org.lengyan.crawler.store.dao.jianshu;

import org.lengyan.crawler.store.model.xmlpo.jianshu.JianshuUser;

public interface JianshuUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JianshuUser record);

    int insertSelective(JianshuUser record);

    JianshuUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JianshuUser record);

    int updateByPrimaryKey(JianshuUser record);
}