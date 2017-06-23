package org.lengyan.crawler.store.service;

import java.util.List;

import org.lengyan.crawler.store.model.po.Minju;

/**
 * 古诗文标签服务
 * @author kangtiancheng
 * @date 2017年6月20日
 */
public interface IMinjuService {
	
	/**
	 * 保存作者信息
	 * @param GSWAuthor
	 * @return
	 */
	public void saveMinjus(List<Minju> GSWMinjus);
	
}
