package org.lengyan.crawler.store.service.serviceimpl.sina;

import org.lengyan.crawler.store.dao.sina.SinaUserMapper;
import org.lengyan.crawler.store.model.po.xmlpo.sina.SinaUser;
import org.lengyan.crawler.store.service.service.sina.ISinaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 新浪用户服务
 * Created by kangtiancheng on 2017/11/14.
 */
@Service("sinaUserService")
public class SinaUserServiceImpl implements ISinaUserService{

    @Autowired
    SinaUserMapper sinaUserMapper;

    @Override
    public void saveSinaUser(SinaUser sinaUser) {
    	SinaUser sinaUserTemp = sinaUserMapper.selectByUserUrl(sinaUser.getUserUrl());
    	if (sinaUserTemp == null) {
    		sinaUserMapper.insertSelective(sinaUser);
		} else {
			sinaUser.setId(sinaUserTemp.getId());
			sinaUserMapper.updateByPrimaryKeySelective(sinaUser);
		}
    }

    @Override
    public void updateSinaUser(SinaUser sinaUser) {
        sinaUserMapper.updateByPrimaryKeySelective(sinaUser);
    }

    @Override
    public List<SinaUser> selectAllUserList() {
        return null;
    }
}
