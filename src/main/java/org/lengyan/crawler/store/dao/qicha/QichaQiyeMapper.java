package org.lengyan.crawler.store.dao.qicha;

import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaQiye;

import java.util.List;

public interface QichaQiyeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QichaQiye record);

    int insertSelective(QichaQiye record);

    QichaQiye selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QichaQiye record);

    int updateByPrimaryKey(QichaQiye record);

    List<QichaQiye> selectByQiyeUrl(String qiyeUrl);

    /**
     * 查询所有企业列表
     * @return
     */
    List<QichaQiye> selectAllQiyeList();
}