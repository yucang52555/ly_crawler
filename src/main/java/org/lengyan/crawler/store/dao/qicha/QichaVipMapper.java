package org.lengyan.crawler.store.dao.qicha;

import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaVip;

public interface QichaVipMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QichaVip record);

    int insertSelective(QichaVip record);

    QichaVip selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QichaVip record);

    int updateByPrimaryKey(QichaVip record);
}