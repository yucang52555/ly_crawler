package org.lengyan.crawler.store.service.serviceimpl.juhe;

import java.util.List;

import org.lengyan.crawler.store.dao.juheapi.JuheapiTohisMapper;
import org.lengyan.crawler.store.model.bo.apibo.TodayHistoryBo;
import org.lengyan.crawler.store.model.po.apipo.juhe.JuheapiTohis;
import org.lengyan.crawler.store.service.service.juhe.IJuheTodayHistoryService;
import org.lengyan.crawler.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 聚合api-历史上的今天
 * @author kangtiancheng
 * @date 2017年11月7日
 */
@Service("juheTodayHistoryService")
public class JuheTodayHistoryServiceImpl implements IJuheTodayHistoryService{
	
	@Autowired
	JuheapiTohisMapper todayHistoryMapper;

	@Override
	public void saveApiResult(String todayHistoryResp) {
		TodayHistoryBo todayHistoryBo = JsonUtils.jsonToObject(todayHistoryResp, TodayHistoryBo.class);
		List<JuheapiTohis> tohistoryList = todayHistoryBo.getResult();
		for (int i = 0; i < tohistoryList.size(); i++) {
			todayHistoryMapper.insertSelective(tohistoryList.get(i));
		}
	}
	
}
