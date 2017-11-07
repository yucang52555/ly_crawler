package org.lengyan.crawler.store.service.serviceimpl.qq;

import java.util.List;

import org.lengyan.crawler.store.dao.qq.QQAccountMapper;
import org.lengyan.crawler.store.model.po.htmlpo.qq.QQAccount;
import org.lengyan.crawler.store.service.service.qq.IQQAccountService;
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

	@Override
	public void updateQQAccount(QQAccount qqAccount) {
		Integer result = qqAccountMapper.updateByPrimaryKeySelective(qqAccount);
		System.out.println(qqAccount.getQqAccount() + ":" + result);
	}
	
}
