package org.lengyan.crawler.store.service.serviceimpl.qicha;

import org.lengyan.crawler.store.dao.qicha.QichaQiyeMapper;
import org.lengyan.crawler.store.dao.sina.SinaUserMapper;
import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaQiye;
import org.lengyan.crawler.store.model.po.xmlpo.sina.SinaUser;
import org.lengyan.crawler.store.service.service.qicha.IQichaQiyeService;
import org.lengyan.crawler.store.service.service.sina.ISinaUserService;
import org.lengyan.crawler.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 企查企业服务
 * Created by kangtiancheng on 2017/11/23
 */
@Service("qichaQiyeService")
public class QichaQiyeServiceImpl implements IQichaQiyeService{

    @Autowired
    QichaQiyeMapper qichaQiyeMapper;

    @Override
    public void saveQichaQiye(QichaQiye qichaQiye) {
        List<QichaQiye> qiyeList = qichaQiyeMapper.selectByQiyeUrl(qichaQiye.getQiyeUrl());
        if (CommonUtils.sizeOf(qiyeList) > 0) {
            qichaQiye.setUpdateTime(new Date());
            for (int i = 0; i < CommonUtils.sizeOf(qiyeList); i++) {
                qichaQiye.setId(qiyeList.get(i).getId());
                qichaQiyeMapper.updateByPrimaryKeySelective(qichaQiye);
            }
        } else {
            qichaQiye.setCreateTime(new Date());
            qichaQiyeMapper.insertSelective(qichaQiye);
        }
    }

    @Override
    public void updateQichaQiye(QichaQiye qichaQiye) {

    }

    @Override
    public List<QichaQiye> selectAllQiyeList() {
        return qichaQiyeMapper.selectAllQiyeList();
    }

    @Override
    public void updateQichaQiyeByQiyeName(QichaQiye qichaQiye) {

    }

    @Override
    public List<QichaQiye> selectGenNearQiyeList() {
        return qichaQiyeMapper.selectGenNearQiyeList();
    }

}
