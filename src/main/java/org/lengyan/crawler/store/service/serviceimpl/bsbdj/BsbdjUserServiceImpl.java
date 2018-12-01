package org.lengyan.crawler.store.service.serviceimpl.bsbdj;

import org.lengyan.crawler.store.dao.bsbdj.BsbdjUserMapper;
import org.lengyan.crawler.store.model.po.apipo.bsbdj.BsbdjUser;
import org.lengyan.crawler.store.model.po.apipo.bsbdj.BsbdjUserResult;
import org.lengyan.crawler.store.service.service.bsbdj.IBsbdjUserService;
import org.lengyan.crawler.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 百思不得姐-用户信息服务
 * @author kangtiancheng
 * @date 2018年3月9日
 */
@Service("bsbdjUserService")
public class BsbdjUserServiceImpl implements IBsbdjUserService{
	
	@Autowired
	BsbdjUserMapper bsbdjUserMapper;

	@Override
	public void saveApiResult(String todayHistoryResp) {
		BsbdjUserResult bsbdjUserResult = JsonUtils.jsonToObject(todayHistoryResp, BsbdjUserResult.class);
		BsbdjUser[] bsbdjUsers = bsbdjUserResult.getData();
		for (BsbdjUser bsbdjUser : bsbdjUsers) {
			BsbdjUser tempBsbdjUser = bsbdjUserMapper.selectByPrimaryKey(bsbdjUser.getId());
			if (tempBsbdjUser == null) {
				bsbdjUserMapper.insertSelective(bsbdjUser);
			} else {
				bsbdjUserMapper.updateByPrimaryKeySelective(bsbdjUser);
			}
		}
	}
	
}
