package org.lengyan.crawler.demo.dynamic;

import org.apache.commons.lang3.StringUtils;
import org.lengyan.crawler.annotation.PipelineName;
import org.lengyan.crawler.pipeline.JsonPipeline;
import org.lengyan.crawler.request.HttpGetRequest;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.scheduler.DeriveSchedulerContext;

import com.alibaba.fastjson.JSONObject;

@PipelineName("productListJsonPipeline")
public class ProductListJsonPipeline extends JsonPipeline {

	@Override
	public void process(JSONObject productList) {
		HttpRequest currRequest = HttpGetRequest.fromJson(productList.getJSONObject("request"));
		//下一页继续抓取
		int currPage = productList.getIntValue("currPage");
		int nextPage = currPage + 1;
		int totalPage = productList.getIntValue("totalPage");
		if(nextPage <= totalPage) {
			String nextUrl = "";
			String currUrl = currRequest.getUrl();
			if(currUrl.indexOf("page=") != -1) {
				nextUrl = StringUtils.replaceOnce(currUrl, "page=" + currPage, "page=" + nextPage);
			} else {
				nextUrl = currUrl + "&" + "page=" + nextPage;
			}
//			SchedulerContext.into(currRequest.subRequest(nextUrl));
			DeriveSchedulerContext.into(currRequest.subRequest(nextUrl));
		}
	}

}
