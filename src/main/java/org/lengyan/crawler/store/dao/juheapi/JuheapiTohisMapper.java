package org.lengyan.crawler.store.dao.juheapi;

import org.lengyan.crawler.store.model.po.apipo.juhe.JuheapiTohis;

/**
 * 聚合api历史上的今天
 * @author kangtiancheng
 * @date 2017年11月6日
 */
public interface JuheapiTohisMapper {
    int deleteByPrimaryKey(Long uid);

    int insert(JuheapiTohis record);

    int insertSelective(JuheapiTohis record);

    JuheapiTohis selectByPrimaryKey(Long uid);

    int updateByPrimaryKeySelective(JuheapiTohis record);

    int updateByPrimaryKey(JuheapiTohis record);
}