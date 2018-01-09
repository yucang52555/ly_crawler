package org.lengyan.crawler.generate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

/**
 * html生成
 * @author kangtiancheng
 * @date 2017年10月16日
 */
public class GenerateHTMLUtils {
	/**
	 * 根据提供的参数替换模版中的内容(可批量生成)，生成文章静态页面到指定目录下,并且返回文章ID对应的文件路径
	 */
	public static Map<String, String> generate(String templatePath,
			String targetPath, List<Map<String, Object>> params) {
		String pageName = null;
		
		BufferedReader in = null;
		PrintWriter out = null;
		Map<String, String> returnUrl = new HashMap<String, String>();
		try {
			// 读取指定模版文件中的数据
			in = new BufferedReader(new InputStreamReader(new FileInputStream(templatePath),"utf-8"));
			//读取文件
			StringBuilder template = new StringBuilder();
			while (StringUtils.isNotBlank(in.readLine())) {
				template.append(in.readLine());
			}
			//替换文件内容
			for (Map<String, Object> map : params) {
				String page = template.toString();
				// ####title#### 为标识性字符, 视模版文件中的实际标识字符为准
				page = page.replace("####charset####", map.get("charset").toString());
				page = page.replace("####title####", map.get("title").toString());
				page = page.replace("####content####", map.get("content").toString());
				// 由于每篇文章在数据库中的id是唯一的，所以即使是文件名的前面有了重复但再加上文章的id即可保证文件名的唯一性
				pageName = targetPath + "/"	+ map.get("title") + ".html";
				File newPage = new File(pageName);
				File target = new File(targetPath);
				if (!target.exists()) {
					target.mkdir();
					if (!newPage.exists()) {
						newPage.createNewFile();
					}
				}
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newPage),"utf-8")));
				out.write(page);
				out.flush();
//				returnUrl.put(map.get("id").toString(), pageName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {out.close();}
				if (in != null) {in.close();}
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return returnUrl;
	}

	/**
	 * 根据参数决定生成文件名的方式<br>
	 * true使用UUID生成文件名<br>
	 * false使用当前时间生成文件名
	 */
	private static String randomPageName(boolean flag) {
		if (flag) {
			return UUID.randomUUID().toString().replace("-", "");
		} else {
			Calendar calendar = Calendar.getInstance();
			return calendar.get(Calendar.YEAR) + ""
					+ (calendar.get(Calendar.MONTH) + 1) + ""
					+ calendar.get(Calendar.DAY_OF_MONTH);
		}
	}

	public void test() {
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("id", i);
		map.put("title", "简书用户信息");
		StringBuilder hrefBuilder = new StringBuilder();
		for (int i = 1; i < 60; i++) {
			hrefBuilder.append("<a href='http://www.jianshu.com/search?q=%E6%95%B0%E6%8D%AE&page=")
						.append(i)
						.append("&type=user'>第")
						.append(i)
						.append("页</a><br/>");
		}
		map.put("content", hrefBuilder.toString());
		params.add(map);
		
		Map<String, String> urls = GenerateHTMLUtils.generate("E:/workspace/YucangCrawler/file/HtmlTemplate.html",
				"E:/workspace/YucangCrawler/file/htmlResult", params);
		for (Entry<String, String> en : urls.entrySet()) {
			System.out.println(en.getKey() + "-->" + en.getValue());
		}
	}
}