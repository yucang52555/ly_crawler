package org.lengyan.crawler.utils.xml;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * XML解析工具类
 * @author tiancheng
 *  Nov 15, 2016
 */
public class XMLParseUtil {
	private static Document document = null;
	
	/**
	 * get XML document according to configuration file
	 * @author tiancheng
	 * @param xmlFile
	 * @return document
	 * @throws DocumentException
	 */
	public static Document getDocument(File xmlFile) throws DocumentException {
		SAXReader saxReader = new SAXReader();
		document = saxReader.read(xmlFile);
		return document;
	}

	/**
	 * get XML document according to configuration file
	 * @author tiancheng
	 * @param xmlFile
	 * @return document
	 * @throws DocumentException
	 */
	public static Document getDocument(String xmlFile) throws DocumentException {
		SAXReader saxReader = new SAXReader();
		document = saxReader.read(new File(xmlFile));
		return document;
	}

	/**
	 * support all characters
	 * @author tiancheng
	 * @param data
	 * @return
	 */
	public static String checkAllCharacters(String data) {
		StringBuffer appender = new StringBuffer("");
		if (StringUtils.isNotBlank(data)) {
			appender = new StringBuffer(data.length());
			for (int i = 0; i < data.length(); i++) {
				char ch = data.charAt(i);
				if ((ch == 0x9) || (ch == 0xA) || (ch == 0xD)
						|| ((ch >= 0x20) && (ch <= 0xD7FF))
						|| ((ch >= 0xE000) && (ch <= 0xFFFD))
						|| ((ch >= 0x10000) && (ch <= 0x10FFFF)))
					appender.append(ch);
			}
		}
		String result = appender.toString();
		return result.replaceAll("]]>", "");
	}
}
