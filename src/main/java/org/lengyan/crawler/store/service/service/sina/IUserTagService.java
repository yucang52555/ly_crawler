package org.lengyan.crawler.store.service.service.sina;

import java.util.List;

import org.lengyan.crawler.store.model.po.htmlpo.sina.UserTag;

/**
 * 新浪用户标签服务
 * @author kangtiancheng
 * @date 2017年11月11日
 */
public interface IUserTagService {
	public void saveUserTag(UserTag userTag);

	public void updateUserTag(UserTag userTag);

	/**
	 * 查询新浪标签列表
	 * @return
	 */
	public List<UserTag> selectAllUserTagList();
}
