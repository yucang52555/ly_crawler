package org.lengyan.crawler.store.service.service.sina;

import org.lengyan.crawler.store.model.po.htmlpo.sina.UserTag;
import org.lengyan.crawler.store.model.po.xmlpo.sina.SinaUser;

import java.util.List;

/**
 * 新浪用户服务
 */
public interface ISinaUserService {
	public void saveSinaUser(SinaUser sinaUser);

	public void updateSinaUser(SinaUser sinaUser);

	/**
	 * 查询新浪用户列表
	 * @return
	 */
	public List<SinaUser> selectAllUserList();
}
