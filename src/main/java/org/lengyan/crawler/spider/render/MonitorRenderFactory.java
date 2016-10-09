package org.lengyan.crawler.spider.render;

import org.reflections.Reflections;

import net.sf.cglib.proxy.Enhancer;

import org.lengyan.crawler.monitor.RenderMointorIntercetor;
import org.lengyan.crawler.spider.render.html.HtmlRender;
import org.lengyan.crawler.spider.render.json.JsonRender;

public class MonitorRenderFactory extends RenderFactory {

	public MonitorRenderFactory(Reflections reflections) {
		super(reflections);
	}

	@Override
	public HtmlRender createHtmlRender() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(HtmlRender.class);
		enhancer.setCallback(new RenderMointorIntercetor());
		return (HtmlRender)enhancer.create();
	}

	@Override
	public JsonRender createJsonRender() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(JsonRender.class);
		enhancer.setCallback(new RenderMointorIntercetor());
		return (JsonRender)enhancer.create();
	}
	
	
	
}
