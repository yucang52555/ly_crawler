package org.lengyan.crawler.store.dao.jianshu;

import java.util.List;

import org.lengyan.crawler.store.model.xmlpo.jianshu.JianshuKeyword;

public interface JianshuKeywordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JianshuKeyword record);

    int insertSelective(JianshuKeyword record);

    JianshuKeyword selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JianshuKeyword record);

    int updateByPrimaryKey(JianshuKeyword record);

	JianshuKeyword selectByKeyword(String keyword);

	List<JianshuKeyword> selectAllJianshuKeyword();
}