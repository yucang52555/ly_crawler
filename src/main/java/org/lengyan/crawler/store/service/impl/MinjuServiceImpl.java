package org.lengyan.crawler.store.service.impl;

import java.util.List;

import org.lengyan.crawler.store.dao.GushiwenMinjuMapper;
import org.lengyan.crawler.store.model.po.Minju;
import org.lengyan.crawler.store.service.IMinjuService;
import org.lengyan.crawler.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("minjuService")
public class MinjuServiceImpl implements IMinjuService {
	
	@Autowired
	private GushiwenMinjuMapper minjuMapper;
	
	@Override
	public void saveMinjus(List<Minju> gswMinjus) {
		Integer size = CommonUtils.sizeOf(gswMinjus);
		for (int i = 0; i < size; i++) {
			Minju minju = gswMinjus.get(i);
			Integer result = minjuMapper.insertSelective(minju);
			System.out.println(minju.getSourcePoetry() + ":" + result);
		}
	}
	
}
