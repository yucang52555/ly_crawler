package org.lengyan.crawler.store.service;

import java.util.List;

import org.lengyan.crawler.store.model.po.QQAccount;

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
	
}
