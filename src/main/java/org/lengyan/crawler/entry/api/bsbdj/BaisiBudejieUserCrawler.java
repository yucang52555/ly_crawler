package org.lengyan.crawler.entry.api.bsbdj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.lengyan.crawler.store.service.service.bsbdj.IBsbdjUserService;
import org.lengyan.crawler.store.service.serviceimpl.bsbdj.BsbdjUserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONException;

/**
 * 百思不得姐用户信息抓取
 * 
 * @author kangtiancheng
 * @date 2018年3月7日
 */
public class BaisiBudejieUserCrawler {
	
	
	static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-data.xml");
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
	public static String postRequestFromUrl(String url, String body) throws IOException, JSONException {
		URL realUrl = new URL(url);
		URLConnection conn = realUrl.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		PrintWriter out = new PrintWriter(conn.getOutputStream());
		out.print(body);
		out.flush();
		
		InputStream instream = conn.getInputStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(instream, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			return jsonText;
		} finally {
			instream.close();
		}
	}
	
	public static String getRequestFromUrl(String url) throws IOException, JSONException {
		URL realUrl = new URL(url);
		URLConnection conn = realUrl.openConnection();
		InputStream instream = conn.getInputStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(instream, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			return jsonText;
		} finally {
			instream.close();
		}
	}
	public static void main(String[] args) throws IOException, JSONException {
		context.start();
		IBsbdjUserService bsbdjUserService = (BsbdjUserServiceImpl)context.getBean("bsbdjUserService");
		for (int i = 160648; i < 1000000; i++) {
			try {
				//获取百思不得姐用户信息
				String url = "http://api01.bitspaceman.com:8000/profile/baisibudejie?apikey=rU2NaVBDz2G3BSLdggKyunvqRsp5YU0KSG5Ubxfd2czdTZNMm0uJsJDD4BKg6Fx7&id=" + i;
				String result = getRequestFromUrl(url);
				//保存到数据库
				bsbdjUserService.saveApiResult(result);
				System.out.println(result);
				//获取百思不得姐帖子
				
				Thread.sleep(5500);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					Thread.sleep(5500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
}
