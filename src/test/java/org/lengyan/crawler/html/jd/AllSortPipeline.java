package org.lengyan.crawler.html.jd;

import java.util.ArrayList;
import java.util.List;

import org.lengyan.crawler.annotation.PipelineName;
import org.lengyan.crawler.pipeline.Pipeline;
import org.lengyan.crawler.request.HttpRequest;
import org.lengyan.crawler.spider.HrefBean;

@PipelineName("allSortPipeline")
public class AllSortPipeline implements Pipeline<AllSort> {
	
	public static List<HttpRequest> sortRequests = new ArrayList<HttpRequest>();

	@Override
	public void process(AllSort allSort) {
		List<Category> mobiles = allSort.getMobile();
		process(allSort, mobiles);
		List<Category> domestics = allSort.getDomestic();
		process(allSort, domestics);
		List<Category> bodys = allSort.getBaby();
		process(allSort, bodys);
	}
	
	private void process(AllSort allSort, List<Category> categorys) {
		if(categorys == null) {
			return;
		}
		for(Category category : categorys) {
			List<HrefBean> hrefs = category.getCategorys();
			for(HrefBean href : hrefs) {
				String url = href.getUrl()+"&delivery=1&page=1&JL=4_10_0&go=0";
				HttpRequest currRequest = allSort.getRequest();
				//SchedulerContext.into(currRequest.subRequest(url));
				//将分类的商品列表地址暂存起来
				sortRequests.add(currRequest.subRequest(url));
			}
		}
	}

}