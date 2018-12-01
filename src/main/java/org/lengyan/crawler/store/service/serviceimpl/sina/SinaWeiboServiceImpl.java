package org.lengyan.crawler.store.service.serviceimpl.sina;

import org.lengyan.crawler.store.dao.sina.SinaUserMapper;
import org.lengyan.crawler.store.dao.sina.SinaWeiboMapper;
import org.lengyan.crawler.store.model.po.xmlpo.sina.SinaUser;
import org.lengyan.crawler.store.model.po.xmlpo.sina.SinaWeibo;
import org.lengyan.crawler.store.service.service.sina.ISinaUserService;
import org.lengyan.crawler.store.service.service.sina.ISinaWeiboService;
import org.lengyan.crawler.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 新浪微博服务
 * Created by kangtiancheng on 2017/11/17.
 */
@Service("sinaWeiboService")
public class SinaWeiboServiceImpl implements ISinaWeiboService {

    @Autowired
    SinaWeiboMapper sinaWeiboMapper;

    @Override
    public void saveSinaWeibo(SinaWeibo sinaWeibo) {
        sinaWeiboMapper.insertSelective(sinaWeibo);
    }

    @Override
    public void updateSinaWeibo(SinaWeibo sinaWeibo) {
        sinaWeiboMapper.updateByPrimaryKeySelective(sinaWeibo);
    }

    @Override
    public List<SinaWeibo> selectAllWeiboList() {
        return null;
    }

    @Override
    public void updateSinaWeiboByUserName(SinaWeibo sinaWeibo) {

    }
}
