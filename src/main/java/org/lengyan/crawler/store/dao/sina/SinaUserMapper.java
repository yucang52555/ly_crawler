package org.lengyan.crawler.store.dao.sina;

import org.lengyan.crawler.store.model.po.xmlpo.sina.SinaUser;

import java.util.List;

/**
 * 新浪用户
 */
public interface SinaUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SinaUser record);

    int insertSelective(SinaUser record);

    SinaUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SinaUser record);

    int updateByPrimaryKey(SinaUser record);

	SinaUser selectByUserUrl(String userUrl);

    /**
     * 获取所有用户信息
     * @return
     */
    List<SinaUser> selectAllUserList();
}