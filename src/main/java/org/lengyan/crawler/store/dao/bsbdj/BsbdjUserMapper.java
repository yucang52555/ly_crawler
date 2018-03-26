package org.lengyan.crawler.store.dao.bsbdj;

import org.lengyan.crawler.store.model.po.apipo.bsbdj.BsbdjUser;

public interface BsbdjUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BsbdjUser record);

    int insertSelective(BsbdjUser record);

    BsbdjUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BsbdjUser record);

    int updateByPrimaryKey(BsbdjUser record);
}