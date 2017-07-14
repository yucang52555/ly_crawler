package org.lengyan.crawler.store.service.impl;

import java.util.List;

import org.lengyan.crawler.store.dao.QQAccountMapper;
import org.lengyan.crawler.store.model.po.QQAccount;
import org.lengyan.crawler.store.service.IQQAccountService;
import org.lengyan.crawler.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("qqAccountService")
public class QQAcountServiceImpl implements IQQAccountService {
	
	@Autowired
	private QQAccountMapper qqAccountMapper;
	
	@Override
	public void saveQQAccounts(List<QQAccount> qqAccounts) {
		Integer size = CommonUtils.sizeOf(qqAccounts);
		for (int i = 0; i < size; i++) {
			QQAccount qqAccount = qqAccounts.get(i);
			Integer result = qqAccountMapper.insertSelective(qqAccount);
			System.out.println(qqAccount.getQqAccount() + ":" + result);
		}
	}
	
}
