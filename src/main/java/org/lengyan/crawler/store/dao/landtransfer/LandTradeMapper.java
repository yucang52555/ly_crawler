package org.lengyan.crawler.store.dao.landtransfer;

import org.lengyan.crawler.store.model.po.htmlpo.landtransfer.LandTrade;

/**
 * 实时品类分布订单数据 DAO层
 * @since  2018年10月19日
 * @author ktc
 */
public interface LandTradeMapper {

	int deleteByPrimaryKey(Long id);

    int insert(LandTrade record);

    int insertSelective(LandTrade record);

    LandTrade selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LandTrade record);

    int updateByPrimaryKey(LandTrade record);

    LandTrade selectByDetailUrl(String detailUrl);
}
