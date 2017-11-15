package org.lengyan.crawler.entry.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * jsoup测试
 * @author kangtiancheng
 * @date 2017年6月22日
 */
public class JsoupParseTest {
	public static void main(String[] args) {
		String html = "<div class=\"cont\" style=\" margin-top:12px;border-bottom:1px dashed #DAD9D1; padding-bottom:7px;\">"
				+ "<a style=\" float:left;\" target=\"_blank\" href=\"/mingju/ju_5802.aspx\">料峭春寒中酒，交加晓梦啼莺。</a>"
				+ "<span style=\" color:#65645F; margin-top:-7px; float:left; margin-left:5px; margin-right:10px;\">____</span>"
				+ "<a style=\" float:left;\" target=\"_blank\" href=\"/view_62345.aspx\">"
				+ "吴文英《风入松·听风听雨过清明》</a></div>";
		Document document = Jsoup.parse(html);
		Elements elements = document.select("a:eq(0)");
		System.out.println(elements.html());
		elements = document.select("a:eq(2)");
		System.out.println(elements.html());
	}
}
