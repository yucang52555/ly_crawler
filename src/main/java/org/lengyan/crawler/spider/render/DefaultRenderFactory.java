package org.lengyan.crawler.spider.render;

import org.reflections.Reflections;

import org.lengyan.crawler.spider.render.html.HtmlRender;
import org.lengyan.crawler.spider.render.json.JsonRender;

public class DefaultRenderFactory extends RenderFactory {
	
	public DefaultRenderFactory(Reflections reflections) {
		super(reflections);
	}

	public HtmlRender createHtmlRender() {
		return new HtmlRender();
	}
	
	public JsonRender createJsonRender() {
		return new JsonRender();
	}
	
}
