package org.lengyan.crawler.request;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpPostRequest extends AbstractHttpRequest {

	private static final long serialVersionUID = -4451221207994730839L;

	private Map<String, Object> fields;
	
	public HttpPostRequest() {
		super();
		fields = new HashMap<String, Object>();
	}

	public HttpPostRequest(String url) {
		super(url);
		fields = new HashMap<String, Object>();
	}
	
	public Map<String, Object> getFields() {
		return fields;
	}

	public void setFields(Map<String, Object> fields) {
		this.fields = fields;
	}

	public void addField(String name, String field) {
		fields.put(name, field);
	}
	
	public static HttpPostRequest fromJson(JSONObject request) {
		return (HttpPostRequest)JSON.toJavaObject(request, HttpPostRequest.class);
	}
}
