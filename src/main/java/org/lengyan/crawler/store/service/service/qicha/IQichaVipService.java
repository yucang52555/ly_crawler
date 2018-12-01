package org.lengyan.crawler.store.service.service.qicha;

import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaQiye;
import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaVip;

import java.util.List;

/**
 * 企查查主要成员服务
 */
public interface IQichaVipService {
	public void saveQichaVip(QichaVip qichaVip);

	public void updateQichaVip(QichaVip qichaVip);

	/**
	 * 查询企查查主要成员列表
	 * @return
	 */
	public List<QichaVip> selectAllVipList();

	public void updateQichaVipByVipName(QichaVip qichaVip);
}
