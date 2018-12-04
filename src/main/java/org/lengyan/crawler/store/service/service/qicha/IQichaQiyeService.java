package org.lengyan.crawler.store.service.service.qicha;

import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaQiye;

import java.util.List;

/**
 * 企查查企业服务
 */
public interface IQichaQiyeService {
	void saveQichaQiye(QichaQiye qichaQiye);

	void updateQichaQiye(QichaQiye qichaQiye);

	/**
	 * 查询企查查企业列表
	 * @return
	 */
	List<QichaQiye> selectAllQiyeList();

	void updateQichaQiyeByQiyeName(QichaQiye qichaQiye);

	/**
	 * 查询需要生成附近企业列表的数据
	 * @return
	 */
	List<QichaQiye> selectGenNearQiyeList();
}
