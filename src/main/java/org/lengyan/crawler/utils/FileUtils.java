package org.lengyan.crawler.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.lengyan.crawler.utils.html.Encoding;
import org.lengyan.crawler.utils.html.HTMLDecoder;
import org.lengyan.crawler.utils.html.SinoDetect;

/**
 * 文件操作工具类
 * @author kangtc
 * @date 2015年2月3日
 */
public class FileUtils {

	/**
	 * 新建目录
	 * @param folderPath
	 */
	public static void newFolder(String folderPath){
		String filePath = folderPath;
		filePath = filePath.toString();
		java.io.File myFilePath = new java.io.File(filePath);
		if (!myFilePath.exists()) {
			myFilePath.mkdir();
		}
	}
	
	/**
	 * 新建目录群
	 * @param folderPath
	 */
	public static void newFolders(String folderPath) {
		String filePath = folderPath;
		filePath = filePath.toString();
		java.io.File myFilePath = new java.io.File(filePath);
		if (!myFilePath.exists()) {
			myFilePath.mkdirs();
		}
	}
	
	/**
	 * 新建文件
	 * @param filePath
	 * @throws IOException 
	 */
	public static void newFile(String filePath) throws IOException{
		File file = new File(filePath);
		File folder = file.getParentFile();
		if (!folder.exists()) {
			newFolders(folder.getAbsolutePath());
		}
		file.createNewFile();
	}

	/**
	 * 删除文件
	 * @param filePathAndName
	 * @param fileContent
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		String filePath = filePathAndName;
		filePath = filePath.toString();
		java.io.File myDelFile = new java.io.File(filePath);
		myDelFile.delete();
	}

	/**
	 * 删除文件
	 * @param filePathAndName
	 * @param fileContent
	 * @return boolean
	 */
	public static void delFolder(String folderPath) {
		delAllFile(folderPath); // 删除完里面所有内
		String filePath = folderPath;
		filePath = filePath.toString();
		java.io.File myFilePath = new java.io.File(filePath);
		myFilePath.delete(); // 删除空文件夹
	}

	/**
	 * 删除文件夹里面的文件
	 * @param path
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文
				delFolder(path + "/" + tempList[i]);// 再删除空文件
			}
		}
	}

	/**
	 * 复制单个文件
	 * @param oldPath
	 * @param newPath
	 * @return boolean
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public static void copyFile(String oldPath, String newPath) throws Exception {
		int bytesum = 0;
		int byteread = 0;
		File oldfile = new File(oldPath);
		if (oldfile.exists()) { // 文件存在
			newFile(newPath);
			InputStream inStream = new FileInputStream(oldPath); // 读入原文
			FileOutputStream fs = new FileOutputStream(newPath);
			byte[] buffer = new byte[1444];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread; // 字节文件大小
				// System.out.println(bytesum);
				fs.write(buffer, 0, byteread);
			}
			inStream.close();
			fs.close();
		}
	}

	/**
	 * 复制整个文件夹内
	 * @param oldPath
	 * @param newPath
	 * @return boolean
	 * @throws Exception
	 */
	public static void copyFolder(String oldPath, String newPath) throws Exception {
		(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件
		File a = new File(oldPath);
		String[] file = a.list();
		File temp = null;
		for (int i = 0; i < file.length; i++) {
			if (oldPath.endsWith(File.separator)) {
				temp = new File(oldPath + file[i]);
			} else {
				temp = new File(oldPath + File.separator + file[i]);
			}

			if (temp.isFile()) {
				FileInputStream input = new FileInputStream(temp);
				FileOutputStream output = new FileOutputStream(newPath + "/"
						+ (temp.getName()).toString());
				byte[] b = new byte[1024 * 5];
				int len;
				while ((len = input.read(b)) != -1) {
					output.write(b, 0, len);
				}
				output.flush();
				output.close();
				input.close();
			}
			if (temp.isDirectory()) {// 如果是子文件
				copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
			}
		}
	}

	/**
	 * 移动文件到指定目
	 * @param oldPath
	 * @param newPath
	 * @throws Exception
	 */
	public static void moveFile(String oldPath, String newPath) throws Exception {
		copyFile(oldPath, newPath);
		delFile(oldPath);
	}

	/**
	 * 移动文件夹到指定目录
	 * @param oldPath
	 * @param newPath
	 * @throws Exception
	 */
	public static void moveFolder(String oldPath, String newPath) throws Exception {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);

	}

	// ============================================================\\
	// ==========================分割线==============================\\
	// ==============================================================\\
	/**
	 * 数据流直接写入文
	 * @param is
	 * @param filePath
	 * @throws Exception
	 * @throws Exception
	 */
	public static void writeFile(InputStream is, String filePath) throws Exception {
		FileOutputStream fs = new FileOutputStream(filePath);
		byte[] buf = new byte[1024];
		int len = is.read(buf);
		while (len != -1) {
			fs.write(buf, 0, len);
			len = is.read(buf);
		}
		fs.close();
	}

	/**
	 * 文字写入文件 <b>iso-8859-1</b> 为编码格
	 * @param src
	 * @param filePath
	 * @throws Exception
	 */
	public static void writeFile(String src, String filePath) throws Exception {
		FileOutputStream fs = new FileOutputStream(filePath);
		fs.write(src.getBytes("iso-8859-1"));// 如果不指定编码，在中英文平台上运行时可能会出现意想不到的结果
		fs.close();
	}

	/**
	 * 文件写入文件并制定编码方
	 * @param src
	 * @param charset
	 * @param filePath
	 */
	public static void writeFile(String src, String filePath, String charset) throws Exception {
		newFile(filePath);
		FileOutputStream fs = new FileOutputStream(filePath);
		fs.write(src.getBytes(charset));
		fs.close();
	}

	/**
	 * 文字附加在原内容后写入文<b>iso-8859-1</b> 为编码格
	 * @param src
	 * @param filePath
	 */
	public static void writeFileAppend(String src, String filePath) throws Exception {
		FileOutputStream fs = new FileOutputStream(filePath, true);
		fs.write(src.getBytes("iso-8859-1"));// 如果不指定编码，在中英文平台上运行时可能会出现意想不到的结果
		fs.close();
	}

	/**
	 * 文字附加在原内容后写入文charset 为编码格
	 * @param src
	 * @param filePath
	 */
	public static void writeFileAppend(String src, String filePath, String charset) throws Exception {
		FileOutputStream fs = new FileOutputStream(filePath, true);
		fs.write(src.getBytes(charset));// 如果不指定编码，在中英文平台上运行时可能会出现意想不到的结果
		fs.close();
	}

	/**
	 * 读取文件 <b>iso-8859-1</b> 为编码格
	 * @param filePath
	 * @return
	 */
	public static String readFile(String filePath) {
		return readFile(filePath, "utf-8", 1024);
	}
	
	public static String readFile(File file) {
		String filePath = file.getAbsolutePath();
		return readFile(filePath, "utf-8", 1024);
	}

	/**
	 * 以指定编码方式读取文
	 * @param filePath
	 * @param charset
	 * @return
	 */
	public static String readFile(String filePath, String charset) {
		return readFile(filePath, charset, 1024);
	}

	/**
	 * 以指定编码方式以及单位块大小读取文件
	 * @param filePath
	 * @param charset
	 * @param blockSize
	 * @return
	 */
	public static String readFile(String filePath, String charset, int blockSize) {
		FileInputStream fs;
		String content = "";
		try {
			fs = new FileInputStream(filePath);
			byte data[] = new byte[1024];
			int len = fs.read(data);
			while (len != -1) {
				content = content + new String(data, 0, len, charset);
				len = fs.read(data);
			}
			fs.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
		return content;
	}

	// ============================================================\\
	// ==========================分割线==============================\\
	// ==============================================================\\
	/**
	 * 读取流内
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String readStream(InputStream is) throws Exception {
		String cs = null;
		try {
			ByteArrayOutputStream buffer = null;
			BufferedInputStream in = new BufferedInputStream(is);
			buffer = new ByteArrayOutputStream();
			byte[] buff = new byte[1024];
			int len = -1;
			// 从socket连接中获取输出流，主要为请求的响应报头和HTML编码

			while ((len = in.read(buff)) != -1) {
				buffer.write(buff, 0, len);
			}// 由于使用BufferOutputStream会出现一个连接被分割在两行的情况，因此只能利用字节流将所有源代码取得，后换成String

			HTMLDecoder htmd = new HTMLDecoder();
			SinoDetect sd = new SinoDetect();

			if (buffer != null) {

				try {
					int i = sd.detectEncoding(buffer.toByteArray());
					cs = buffer.toString(Encoding.htmlname[i]);
				} catch (RuntimeException e) {
					cs = buffer.toString("GBK");
				}
				cs = cs.replace("&nbsp;", "");
				cs = htmd.ASCIIToGB(cs);
			}
			is.close();
		} catch (IOException e) {
			throw e;
		}
		return cs;
	}

	/**
	 * 读取字节内容
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String readBytes(byte[] buff) throws Exception {
		String cs = null;
		try {
			HTMLDecoder htmd = new HTMLDecoder();
			SinoDetect sd = new SinoDetect();

			if (buff != null) {
				if (sd.detectEncoding(buff) == 1) {
					cs = new String(buff, "GBK");
					cs = cs.replace("&nbsp;", " ");
					cs = htmd.ASCIIToGB(cs);
				} else if (sd.detectEncoding(buff) == 2) {
					cs = new String(buff, "HZ");
					cs = cs.replace("&nbsp;", " ");
					cs = htmd.ASCIIToGB(cs);
				} else if (sd.detectEncoding(buff) == 3) {
					cs = new String(buff, "BIG5");
					cs = cs.replace("&nbsp;", " ");
					cs = htmd.ASCIIToGB(cs);
				} else if (sd.detectEncoding(buff) == 4) {
					cs = new String(buff, "EUC_TW");
					cs = cs.replace("&nbsp;", " ");
					cs = htmd.ASCIIToGB(cs);
				} else if (sd.detectEncoding(buff) == 5) {
					cs = new String(buff, "ISO_2022_CN");
					cs = cs.replace("&nbsp;", " ");
					cs = htmd.ASCIIToGB(cs);
				} else if (sd.detectEncoding(buff) == 6) {
					cs = new String(buff, "UTF-8");
					cs = cs.replace("&nbsp;", " ");
					cs = htmd.ASCIIToGB(cs);
				} else if (sd.detectEncoding(buff) == 7) {
					cs = new String(buff, "Unicode");
					cs = cs.replace("&nbsp;", " ");
					cs = htmd.ASCIIToGB(cs);
				} else
					cs = new String(buff);
				// cs=new String(buff,"GBK");
			}
		} catch (IOException e) {
			throw e;
		}
		return cs;
	}

	/**
	 * 读取流内容，指定编码方式
	 * @param is
	 * @param charset
	 * @return
	 */
	public static String readStream(InputStream is, String charset) {
		StringBuilder sb = new StringBuilder();
		// String content="";
		try {
			byte data[] = new byte[1024];
			for (int n; (n = is.read(data)) != -1;) {
				sb.append(new String(data, 0, n, charset));
			}

			is.close();
		} catch (IOException e) {

		}
		return sb.toString();
		// return content;
	}

	// ============================================================\\
	// ==========================分割线==============================\\
	// ==============================================================\\
	/**
	 * 获取文件大小
	 * @param fileName
	 * @return 单位为：kb
	 * @throws Exception
	 */
	public static double getFileSize(String fileName) {
		double size = 0;
		DecimalFormat df = new DecimalFormat("###.0");
		File file = new File(fileName);
		double length = file.length();
		if (file.exists()) {
			size = Double.valueOf(df.format(length / 1024));
		}
		return size;
	}
	
	/**
	 * 判断文件夹包括子文件夹下没有文件
	 * @param file
	 * @return
	 */
	public static boolean isEmpty(File file) {
		boolean flag = false;
		if (!file.isDirectory()) {
			flag = false;
			return flag;
		}
		File[] files = file.listFiles();
		if (files.length == 0) {
			flag = true;
		}
		for (File subFile : files) {
			flag = isEmpty(subFile);
			if (!flag) {
				return flag;
			}
		}
		return flag;
	}
	
	/**
	 * 读取一个文件夹下所有文件及子文件夹下的所有文件
	 * @param filePath
	 */
	public void ReadAllFile(String filePath) {  
		List<File> list = new ArrayList<File>();
		File f = null;
        f = new File(filePath);
        File[] files = f.listFiles(); // 得到f文件夹下面的所有文件。  
        for (File file : files) {
            if(file.isDirectory()) {
                //如何当前路劲是文件夹，则循环读取这个文件夹下的所有文件  
                ReadAllFile(file.getAbsolutePath());  
            } else {
                list.add(file);  
            }  
        }  
    }

	/**
	 * 获得文件夹下的所有文件
	 * @param filePath
	 * @return
	 */
	public static List<File> getFiles(String filePath){
		List<File> fileList = new ArrayList<File>();
		File sourceFile = new File(filePath);
		if (!sourceFile.isDirectory()) {
			System.err.println("文件路径错误");
			return null;
		}
		for (File file : sourceFile.listFiles()) {
			if (file.isDirectory()) {
				fileList.addAll(getFiles(file.getAbsolutePath()));
			}else {
				fileList.add(file);
			}
		}
		return fileList;
	}
	/**
	 * 获得文件夹下所有文件
	 * @param sourceFile
	 * @return
	 */
	public static List<File> getFiles(File sourceFile) {
		List<File> fileList = new ArrayList<File>();
		fileList = getFiles(sourceFile.getAbsolutePath());
		return fileList;
	}
	
	/**
	 * 获取文件夹下文件数目
	 * @param sourceFile
	 * @return
	 */
	public static Integer getFileCount(File sourceFile){
		if (!sourceFile.isDirectory()) {
			return 0;
		}
		Integer size = 0;
		File[] files = sourceFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				size = size + getFileCount(files[i]);
			}else {
				size ++;
			}
		}
		return size;
	}
	
	/**
	 * 获取文件路径下的文件个数
	 * @param filePath
	 * @return
	 */
	public static Integer getFileCount(String filePath){
		Integer size = 0;
		File sourceFile = new File(filePath);
		if (!sourceFile.isDirectory()) {
			return 0;
		}
		size = getFileCount(sourceFile);
		return size;
	}
	
	/**
	 * 获得文件的后缀名
	 * @param file
	 * @return
	 */
	public static String getFileSuffix(File file){
		if (file.isDirectory()) {
			return null;
		}
		String fileName = file.getName();
		return fileName.substring(fileName.lastIndexOf(".")+1); 
	}

	/**
	 * 获取文件名称
	 * @param file
	 * @return
	 */
	public static String getFileName(File file){
		String filename = file.getName();
		if ((filename != null) && (filename.length() > 0)) { 
            int dot = filename.lastIndexOf('.'); 
            if ((dot >-1) && (dot < (filename.length()))) { 
                return filename.substring(0, dot); 
            } 
        } 
        return filename; 
	}
}
