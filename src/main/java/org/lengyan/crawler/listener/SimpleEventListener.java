package org.lengyan.crawler.listener;

import org.lengyan.crawler.GeccoEngine;

/**
 * 简单的引擎时间兼容实现类，可以继承该类覆盖需要的方法
 * 
 * @author LiuJunGuang
 */
public abstract class SimpleEventListener implements EventListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lengyan.crawler.listener.EventListener#onStart(org.lengyan.crawler.GeccoEngine)
	 */
	@Override
	public void onStart(GeccoEngine ge) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lengyan.crawler.listener.EventListener#onPause(org.lengyan.crawler.GeccoEngine)
	 */
	@Override
	public void onPause(GeccoEngine ge) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lengyan.crawler.listener.EventListener#onRestart(org.lengyan.crawler.GeccoEngine)
	 */
	@Override
	public void onRestart(GeccoEngine ge) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lengyan.crawler.listener.EventListener#onStop(org.lengyan.crawler.GeccoEngine)
	 */
	@Override
	public void onStop(GeccoEngine ge) {
	}

}
