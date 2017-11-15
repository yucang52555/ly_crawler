package org.lengyan.crawler.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 正则表达式
 * @since  2017年5月9日
 * @author ktc
 */
public class RegexUtils {

	/**
	 * 特殊字符过滤
	 * @param
	 * @return 
	 * @变更记录 2017年5月9日 下午1:59:17  ktc
	 */
	public static String specialCharacterFilter(String content) throws PatternSyntaxException {
		if (StringUtils.isNotEmpty(content)) {
			//过滤Emoji表情
			content = filterEmoji(content);
			//过滤特殊符号
			content = filterSpecialSymbols(content);
			//替换四字节字符
			content = removeFourChar(content);
			// 只允许字母和数字
			// String regEx = "[^a-zA-Z0-9]";
			// 清除掉所有特殊字符
			String regEx = "[`~!@#$%^&*()+=|{}';'//[//]<>/?~！@#￥%……&*——+|{}【】‘；’、？]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(content);
			return m.replaceAll("").trim();
		}
		return null;
	}
	
	/**
	 * 过滤表情包
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
		if (source != null) {
			Pattern emoji = Pattern.compile("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]|[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", 
					Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
			Matcher emojiMatcher = emoji.matcher(source);
			if (emojiMatcher.find()) {
				source = emojiMatcher.replaceAll("*");
				return source;
			}
			return source;
		}
		return source;
	}
	
	/**
	 * 过滤特殊符号
	 * @param source
	 */
	public static String filterSpecialSymbols(String source) {
		return StringEscapeUtils.unescapeXml(source);
	}
	
	/**
     * 替换四个字节的字符 '\xF0\x9F\x98\x84\xF0\x9F）的解决方案 ��
     */
    public static String removeFourChar(String content) {
        byte[] conbyte = content.getBytes();
        for (int i = 0; i < conbyte.length; i++) {
            if ((conbyte[i] & 0xF8) == 0xF0) {
                for (int j = 0; j < 4; j++) {
                    conbyte[i + j] = 0x30;// 0x30 int=48   字符=0
                }
                i += 3;
            }
        }
        content = new String(conbyte);
        return content.replaceAll("0000", "");
    }
	
	
}
