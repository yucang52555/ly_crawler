package org.lengyan.crawler.store.dao.qicha;

import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaShareholder;

public interface QichaShareholderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QichaShareholder record);

    int insertSelective(QichaShareholder record);

    QichaShareholder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QichaShareholder record);

    int updateByPrimaryKey(QichaShareholder record);
}