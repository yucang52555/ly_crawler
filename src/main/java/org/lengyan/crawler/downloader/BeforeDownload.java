package org.lengyan.crawler.downloader;

import org.lengyan.crawler.request.HttpRequest;

public interface BeforeDownload {
	
	public void process(HttpRequest request);

}
