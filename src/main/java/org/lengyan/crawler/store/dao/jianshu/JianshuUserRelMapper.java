package org.lengyan.crawler.store.dao.jianshu;

import org.lengyan.crawler.store.model.po.xmlpo.jianshu.JianshuUserRel;

public interface JianshuUserRelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JianshuUserRel record);

    int insertSelective(JianshuUserRel record);

    JianshuUserRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JianshuUserRel record);

    int updateByPrimaryKey(JianshuUserRel record);
}