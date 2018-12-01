package org.lengyan.crawler.store.service.serviceimpl.qicha;

import org.lengyan.crawler.store.dao.qicha.QichaQiyeMapper;
import org.lengyan.crawler.store.dao.qicha.QichaShareholderMapper;
import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaQiye;
import org.lengyan.crawler.store.model.po.xmlpo.qicha.QichaShareholder;
import org.lengyan.crawler.store.service.service.qicha.IQichaQiyeService;
import org.lengyan.crawler.store.service.service.qicha.IQichaShareholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 企查企业股东服务
 * Created by kangtiancheng on 2017/11/23
 */
@Service("qichaShareholderService")
public class QichaShareholderServiceImpl implements IQichaShareholderService {

    @Autowired
    QichaShareholderMapper qichaShareholderMapper;

    @Override
    public void saveQichaShareholder(QichaShareholder qichaShareholder) {

    }

    @Override
    public void updateQichaShareholder(QichaShareholder qichaShareholder) {

    }

    @Override
    public List<QichaShareholder> selectAllShareholderList() {
        return null;
    }

    @Override
    public void updateQichaShareholderByQiyeName(QichaShareholder qichaShareholder) {

    }


}
