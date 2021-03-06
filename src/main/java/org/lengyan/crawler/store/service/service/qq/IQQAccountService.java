package org.lengyan.crawler.store.service.service.qq;

import java.util.List;

import org.lengyan.crawler.store.model.po.htmlpo.qq.QQAccount;

/**
 * 古诗文标签服务
 * @author kangtiancheng
 * @date 2017年6月20日
 */
public interface IQQAccountService {
	
	/**
	 * 
	 * @param qqAccounts
	 */
	public void saveQQAccounts(List<QQAccount> qqAccounts);

	public void updateQQAccount(QQAccount qqAccount);
	
}
