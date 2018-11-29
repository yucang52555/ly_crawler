package org.lengyan.crawler.store.service.serviceimpl.landtransfer;

import org.lengyan.crawler.store.dao.landtransfer.LandTradeMapper;
import org.lengyan.crawler.store.model.po.htmlpo.landtransfer.LandTrade;
import org.lengyan.crawler.store.service.service.landtransfer.ILandTradeService;
import org.lengyan.crawler.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("landTradeService")
public class LandTradeServiceImpl implements ILandTradeService {

	@Autowired
	LandTradeMapper landTradeMapper;

	@Override
	public void saveLandTrades(List<LandTrade> landTrades) {
		Integer size = CommonUtils.sizeOf(landTrades);
		for (int i = 0; i < size; i++) {
			LandTrade landTrade = landTrades.get(i);
			Integer result = landTradeMapper.insertSelective(landTrade);
			System.out.println("新增:" + result);
		}
	}

	@Override
	public LandTrade selectLandTradeByDetailUrl(String detailUrl) {
		LandTrade landTrade = landTradeMapper.selectByDetailUrl(detailUrl);
		return  landTrade;
	}

	@Override
	public void saveLandTrade(LandTrade landTrade) {
		Integer result = landTradeMapper.insertSelective(landTrade);
		System.out.println("新增:" + result);
	}

	@Override
	public void updateLandTrade(LandTrade landTrade) {
		Integer result = landTradeMapper.updateByPrimaryKeySelective(landTrade);
		System.out.println("更新:" + result);
	}
}
