package org.lengyan.crawler.store.dao.qq;

import org.lengyan.crawler.store.model.po.htmlpo.qq.QQAccount;

public interface QQAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QQAccount record);

    int insertSelective(QQAccount record);

    QQAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QQAccount record);

    int updateByPrimaryKey(QQAccount record);
}