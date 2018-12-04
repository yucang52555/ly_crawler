package org.lengyan.crawler.store.service.service.landtransfer;

import org.lengyan.crawler.store.model.po.htmlpo.landtransfer.LandTrade;

import java.util.List;

/**
 * 土地交易服务
 * @author kangtiancheng
 * @date 2018年10月19日
 */
public interface ILandTradeService {
	
	/**
	 * 保存土地交易记录列表
	 * @param landTrades
	 * @return
	 */
	void saveLandTrades(List<LandTrade> landTrades);

	/**
	 * 保存单个土地交易记录
	 * @param landTrade
	 */
	void saveLandTrade(LandTrade landTrade);

	/**
	 * 更新土地交易记录
	 * @param landTrade
	 */
	void updateLandTrade(LandTrade landTrade);

	/**
	 * 根据详情url查询土地交易记录
	 * @param detailUrl
	 * @return
	 */
    LandTrade selectLandTradeByDetailUrl(String detailUrl);
}
