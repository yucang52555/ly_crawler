package org.lengyan.crawler.store.service.service.sina;

import org.lengyan.crawler.store.model.po.xmlpo.sina.SinaUser;
import org.lengyan.crawler.store.model.po.xmlpo.sina.SinaWeibo;

import java.util.List;

/**
 * 新浪微博服务
 */
public interface ISinaWeiboService {
	public void saveSinaWeibo(SinaWeibo sinaWeibo);

	public void updateSinaWeibo(SinaWeibo sinaWeibo);

	/**
	 * 查询新浪微博列表
	 * @return
	 */
	public List<SinaWeibo> selectAllWeiboList();

    void updateSinaWeiboByUserName(SinaWeibo sinaWeibo);
}
