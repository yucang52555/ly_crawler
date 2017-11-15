package org.lengyan.crawler.store.service.serviceimpl.sina;

import java.util.List;

import org.lengyan.crawler.store.dao.sina.UserTagMapper;
import org.lengyan.crawler.store.model.po.htmlpo.sina.UserTag;
import org.lengyan.crawler.store.service.service.sina.IUserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userTagService")
public class UserTagServiceImpl implements IUserTagService{
	
	@Autowired
	UserTagMapper userTagMapper;

	@Override
	public void saveUserTag(UserTag userTag) {
		userTagMapper.insertSelective(userTag);
	}

	@Override
	public void updateUserTag(UserTag userTag) {
		userTagMapper.updateByPrimaryKeySelective(userTag);
	}

	@Override
	public List<UserTag> selectAllUserTagList() {
		return userTagMapper.selectAllUserTagList();
	}


}
