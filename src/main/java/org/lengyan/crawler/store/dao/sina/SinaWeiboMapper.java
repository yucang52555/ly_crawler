package org.lengyan.crawler.store.dao.sina;

import org.lengyan.crawler.store.model.po.xmlpo.sina.SinaWeibo;

public interface SinaWeiboMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SinaWeibo record);

    int insertSelective(SinaWeibo record);

    SinaWeibo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SinaWeibo record);

    int updateByPrimaryKey(SinaWeibo record);
}