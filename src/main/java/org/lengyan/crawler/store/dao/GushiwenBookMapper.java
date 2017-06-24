package org.lengyan.crawler.store.dao;

import org.lengyan.crawler.store.model.po.Book;

public interface GushiwenBookMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
}