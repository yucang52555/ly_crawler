package org.lengyan.crawler.store.service.service.qicha;

import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaQiye;
import org.lengyan.crawler.store.model.po.xmlpo.sina.SinaUser;

import java.util.List;

/**
 * 企查查企业服务
 */
public interface IQichaQiyeService {
	public void saveQichaQiye(QichaQiye qichaQiye);

	public void updateQichaQiye(QichaQiye qichaQiye);

	/**
	 * 查询企查查企业列表
	 * @return
	 */
	public List<QichaQiye> selectAllQiyeList();

	public void updateQichaQiyeByQiyeName(QichaQiye qichaQiye);
}
