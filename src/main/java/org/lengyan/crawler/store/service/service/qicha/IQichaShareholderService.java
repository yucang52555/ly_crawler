package org.lengyan.crawler.store.service.service.qicha;

import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaQiye;
import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaShareholder;

import java.util.List;

/**
 * 企查查股东服务
 */
public interface IQichaShareholderService {
	public void saveQichaShareholder(QichaShareholder qichaShareholder);

	public void updateQichaShareholder(QichaShareholder qichaShareholder);

	/**
	 * 查询企查查企业列表
	 * @return
	 */
	public List<QichaShareholder> selectAllShareholderList();

	public void updateQichaShareholderByQiyeName(QichaShareholder qichaShareholder);
}
