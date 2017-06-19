package org.lengyan.crawler.utils;

import java.util.List;

/**
 * 集合工具类
 * @author oycw
 *
 */
public class CommonUtils {
	
	/**
	 * 获取集合的大小
	 * @param
	 * @return 
	 * @变更记录 2016年10月17日 上午10:58:43  ktc
	 */
	public static int sizeOf(List<?> elements) {
		return elements == null ? 0: elements.size();
	}
	
	/**
	 * 获取数组的大小
	 * @param
	 * @return 
	 * @变更记录 2016年10月17日 上午10:58:43  ktc
	 */
	public static int lengthOf(Object[] elements) {
		return elements == null ? 0: elements.length;
	}
	
}
