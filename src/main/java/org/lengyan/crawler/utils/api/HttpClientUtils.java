package org.lengyan.crawler.utils.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 基于 httpclient 4.3.1版本的 http工具类
 *
 * @author mcSui
 *
 */
public class HttpClientUtils {

	//private static CloseableHttpClient httpClient;
	public static final String DefaultCharset = "UTF-8";
    private static final Log LOGGER = LogFactory.getLog(HttpClientUtils.class);

    public static <T extends Object> String doGet(String url, Map<String, T> params) {
        return doGet(url, params, DefaultCharset);
    }

	/*static {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(600000).setSocketTimeout(600000).build();
		httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
	}*/

	public static <T extends Object> String doPost(String url, Map<String, T> params) {
		return doPost(url, params, DefaultCharset);
	}

	private static CloseableHttpClient getHttpClient(){
		RequestConfig config = RequestConfig.custom().setConnectTimeout(600000).setSocketTimeout(600000).build();
		return  HttpClientBuilder.create().setDefaultRequestConfig(config).build();
	}

	/**
	 * HTTP Get 获取内容
	 *
	 * @param url
	 *            请求的url地址 ?之前的地址
	 * @param params
	 *            请求的参数
	 * @param charset
	 *            编码格式
	 * @return 页面内容
	 */
	public static <T extends Object> String doGet(String url, Map<String, T> params, String charset) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		LOGGER.info("http:get:request:url: " + url + ", params:" + params);
		CloseableHttpResponse response = null;
		String result = null;

		try {
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
				for (Map.Entry<String, T> entry : params.entrySet()) {
					Object value = entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value.toString()));
					}
				}
				url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
			}
			HttpGet httpGet = new HttpGet(url);
			response = getHttpClient().execute(httpGet);

			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpGet.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
				}
			}
			response = null;
		}
		LOGGER.info("http:get:result: " + result);
		return result;
	}

	/**
	 * HTTP Post 获取内容 请求响应，非200将抛出异常。
	 *
	 * @param url
	 *            请求的url地址 ?之前的地址
	 * @param params
	 *            请求的参数
	 * @param charset
	 *            编码格式
	 * @return 页面内容
	 */
	public static <T extends Object> String doPost(String url, Map<String, T> params, String charset) {

		HttpResult result = doPostReturnStateCode(url, params, charset);
		if (result.stateCode != 200) {
			throw new RuntimeException("HttpClient,error status code :" + result.stateCode);
		}
		return result.content;

	}

	public static <T extends Object> HttpResult doGetReturnStateCode(String url, Map<String, T> params) {
		return doGetReturnStateCode(url, params, DefaultCharset);
	}

	public static <T extends Object> HttpResult doPostReturnStateCode(String url, Map<String, T> params) {
		return doPostReturnStateCode(url, params, DefaultCharset);
	}

	/**
	 * HTTP Get 获取内容，返回状态和请求内容 <br>
	 * 返回状态码，不是200也成功返回结果
	 *
	 * @param url
	 *            请求的url地址
	 * @param params
	 *            请求参数
	 * @param charset
	 *            编码格式
	 * @return 页面内容
	 */
	public static <T extends Object> HttpResult doGetReturnStateCode(String url, Map<String, T> params,
																	 String charset) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		CloseableHttpClient httpClient=getHttpClient();
		HttpResult httpResult = new HttpResult();
		int statusCode = -1;
		LOGGER.info("http:get:request:url: " + url + ", params:" + params);
		CloseableHttpResponse response = null;
		String result = null;

		try {
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
				for (Map.Entry<String, T> entry : params.entrySet()) {
					Object value = entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value.toString()));
					}
				}
				url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
			}
			HttpGet httpGet = new HttpGet(url);
			response = httpClient.execute(httpGet);

			statusCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				result = EntityUtils.toString(entity, charset);
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
				}
			}
			if(httpClient!=null){
				try {
					httpClient.close();
				} catch (IOException e) {
				}
			}
			httpClient=null;
			response = null;
		}
		LOGGER.info("http:get:result: " + result);
		httpResult.content = result;
		httpResult.stateCode = statusCode;
		return httpResult;
	}

	/**
	 * HTTP Post 获取内容,返回状态和请求内容。<br>
	 * 返回状态码，不是200也成功返回结果
	 *
	 * @param url
	 *            请求的url地址 ?之前的地址
	 * @param params
	 *            请求的参数
	 * @param charset
	 *            编码格式
	 * @return 页面内容
	 */
	public static <T extends Object> HttpResult doPostReturnStateCode(String url, Map<String, T> params,
																	  String charset) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		CloseableHttpClient httpClient=getHttpClient();
		HttpResult result = new HttpResult();

		LOGGER.info("http:post:request:url: " + url + ", params:" + params.toString());
		CloseableHttpResponse response = null;

		try {
			List<NameValuePair> pairs = null;
			if (params != null && !params.isEmpty()) {
				pairs = new ArrayList<NameValuePair>(params.size());
				for (Map.Entry<String, T> entry : params.entrySet()) {
					Object value = entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value.toString()));
					}
				}
			}
			HttpPost httpPost = new HttpPost(url);
			if (pairs != null && pairs.size() > 0) {
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));
			}
			response = httpClient.execute(httpPost);
			result.stateCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result.content = EntityUtils.toString(entity, charset);
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
				}
			}
			if(httpClient!=null){
				try {
					httpClient.close();
				} catch (IOException e) {
				}
			}
			httpClient=null;
			response = null;
		}
		LOGGER.info("http:post:result: "+ result);
		return result;
    }

    public static class HttpResult {
        public int stateCode;
        public String content;
    }
}
