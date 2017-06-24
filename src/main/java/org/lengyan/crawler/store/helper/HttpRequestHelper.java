package org.lengyan.crawler.store.helper;

import java.util.List;

import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.store.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 辅助类
 * @author kangtiancheng
 * @date 2017年6月24日
 */
public class HttpRequestHelper {
	
	@Autowired
	ITagService tagService;
	
	public List<HttpRequest> buildTagHttpRequests() {
		return null;
		
	}
}
