package org.lengyan.crawler.store.dao;

import org.lengyan.crawler.store.model.po.QQAccount;

public interface QQAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QQAccount record);

    int insertSelective(QQAccount record);

    QQAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QQAccount record);

    int updateByPrimaryKey(QQAccount record);
}