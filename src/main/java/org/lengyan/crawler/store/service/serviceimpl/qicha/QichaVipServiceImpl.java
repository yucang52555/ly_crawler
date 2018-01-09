package org.lengyan.crawler.store.service.serviceimpl.qicha;

import org.lengyan.crawler.store.dao.qicha.QichaQiyeMapper;
import org.lengyan.crawler.store.dao.qicha.QichaVipMapper;
import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaQiye;
import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaVip;
import org.lengyan.crawler.store.service.service.qicha.IQichaQiyeService;
import org.lengyan.crawler.store.service.service.qicha.IQichaVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 企业主要成员服务
 * Created by kangtiancheng on 2017/11/23
 */
@Service("qichaVipService")
public class QichaVipServiceImpl implements IQichaVipService{

    @Autowired
    QichaVipMapper qichaVipMapper;

    @Override
    public void saveQichaVip(QichaVip qichaVip) {
        qichaVipMapper.insertSelective(qichaVip);
    }

    @Override
    public void updateQichaVip(QichaVip qichaVip) {

    }

    @Override
    public List<QichaVip> selectAllVipList() {
        return null;
    }

    @Override
    public void updateQichaVipByVipName(QichaVip qichaVip) {

    }


}
