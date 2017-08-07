package org.lengyan.crawler.utils;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

/**
 * sql操作工具类
 * @author kangtiancheng
 * @date 2017年7月31日
 */
public class ConvertUtils {
	
	/**
	 * 字符串转blob
	 * @param blobStr
	 * @return
	 * @throws SerialException
	 * @throws UnsupportedEncodingException
	 * @throws SQLException
	 */
	public static Blob str2Blob(String blobStr) throws SerialException, UnsupportedEncodingException, SQLException {
		Blob blob = new SerialBlob(blobStr.getBytes("UTF-8"));
		return blob;
	}
	
	/**
	 * blob转字符串
	 * @param blob
	 * @return
	 * @throws SerialException
	 * @throws UnsupportedEncodingException
	 * @throws SQLException
	 */
	public static String blob2Str(Blob blob) throws SerialException, UnsupportedEncodingException, SQLException {
		String blobString = new String(blob.getBytes(1, (int) blob.length()),"UTF-8");//blob 转 String
		return blobString;
	}
	
	/**
	 * 字节转字符串
	 * @param bytes
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String byte2Str(byte[] bytes) throws UnsupportedEncodingException {
		String res = new String(bytes,"UTF-8");
		return res;
	}
	
	/**
	 * 字符串转字节
	 * @param byteStr
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] str2Byte(String byteStr) throws UnsupportedEncodingException {
		byte[] srtbyte = byteStr.getBytes("UTF-8");
		return srtbyte;
	}
	
	public static void main(String[] args) throws SerialException, UnsupportedEncodingException, SQLException {
		String s1 = "北望诸𣬈";
		Blob b = new SerialBlob(s1.getBytes("UTF-8"));
		String blobString = new String(b.getBytes(1, (int) b.length()),"UTF-8");//blob 转 String
		System.out.println(blobString);
		
		Clob c = new SerialClob(s1.toCharArray());//String 转 clob
		String clobString = c.getSubString(1, (int) c.length());//clob 转 String
		System.out.println(clobString);
		
	}
}
