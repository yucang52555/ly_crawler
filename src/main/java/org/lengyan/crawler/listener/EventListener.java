package org.lengyan.crawler.listener;

import org.lengyan.crawler.GeccoEngine;

/**
 * 爬虫引擎生命周期监听器
 * 
 * @author LiuJunGuang
 */
public interface EventListener {

	/**
	 * 开始启动时，回调
	 * 
	 * @param ge
	 */
	public void onStart(GeccoEngine ge);

	/**
	 * 暂停时，回调
	 * 
	 * @param ge
	 */
	public void onPause(GeccoEngine ge);

	/**
	 * 恢复抓取时，回调
	 * 
	 * @param ge
	 */
	public void onRestart(GeccoEngine ge);

	/**
	 * 引擎停止时，回调
	 * 
	 * @param ge
	 */
	public void onStop(GeccoEngine ge);
}
