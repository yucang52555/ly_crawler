package org.lengyan.crawler.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 属性文件操作工具类
 * @author kangtiancheng
 * @date 2017年10月9日
 */
public class PropertiesUtil {
	
	/**
	 * 获取指定属性
	 * @param propertiesName
	 * @return
	 */
	public static String getProperties(String fileName, String propertyKey) {
		Properties properties = getProperties(fileName);
		return properties.getProperty(propertyKey);
	}
	

	/**
	 * 获取配置文件属性
	 * @param propertiesName
	 * @return
	 */
	public static Properties getProperties(String fileName) {
		String propertiesPath = getConfigPath(fileName);
		return createProperties(propertiesPath);
	}

	/**
	 * 获取配置文件路径
	 * @param filePath
	 * @return
	 */
	private static String getConfigPath(String fileName) {
		String resultPath = null;
		String classPath = PropertiesUtil.class.getClassLoader()
				.getResource(PropertiesUtil.class.getName().replace('.', '/') + ".class").getFile();
		String classRoot = "";
		classRoot = classPath.substring(0, classPath.indexOf("/org/lengyan/crawler/utils"));
		if (!classRoot.equals("") && classRoot.startsWith("/")
				&& classRoot.indexOf(":") == 2) {
			classRoot = classRoot.substring(1);
		}
		if (!classRoot.equals("")) {
			resultPath = classRoot + "/" + fileName + ".properties";
		}
		return resultPath;
	}
	
	/**
	 * create properties object by propertiesPath
	 * @author tiancheng
	 * @param fileFullName
	 * @return
	 */
	private static Properties createProperties(String fileFullName) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileFullName)), "UTF-8"));
			Properties properties = new Properties();
			properties.load(br);
			return properties;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}
}
