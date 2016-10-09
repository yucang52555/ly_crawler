package org.lengyan.crawler.demo.sogouwx;

import org.lengyan.crawler.annotation.GeccoClass;
import org.lengyan.crawler.downloader.BeforeDownload;
import org.lengyan.crawler.request.HttpRequest;

@GeccoClass(Article.class)
public class BeforeArticleDownloader implements BeforeDownload {

	@Override
	public void process(HttpRequest request) {
		request.clearCookie();
		request.clearHeader();
	}

}
