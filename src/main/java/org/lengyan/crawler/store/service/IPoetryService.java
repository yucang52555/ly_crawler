package org.lengyan.crawler.store.service;

import java.util.List;

import org.lengyan.crawler.store.model.po.Poetry;

/**
 * 古诗文诗词服务
 * @author kangtiancheng
 * @date 2017年6月24日
 */
public interface IPoetryService {
	
	/**
	 * 保存作者信息
	 * @param GSWAuthor
	 * @return
	 */
	public void savePoetrys(List<Poetry> GSWPoetry);
	
}
