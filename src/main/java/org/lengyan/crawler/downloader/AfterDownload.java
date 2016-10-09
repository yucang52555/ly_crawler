package org.lengyan.crawler.downloader;

import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.response.HttpResponse;

public interface AfterDownload {
	
	public void process(HttpRequest request, HttpResponse response);

}
