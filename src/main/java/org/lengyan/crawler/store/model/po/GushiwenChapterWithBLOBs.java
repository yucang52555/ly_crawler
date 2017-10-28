package org.lengyan.crawler.store.model.po;

import java.io.UnsupportedEncodingException;

import org.lengyan.crawler.annotation.html.Html;
import org.lengyan.crawler.annotation.html.HtmlField;
import org.lengyan.crawler.utils.ConvertUtils;

public class GushiwenChapterWithBLOBs extends GushiwenChapter {
	
	private static final long serialVersionUID = 6362448662015452989L;

	@Html
	@HtmlField(cssPath=".left .sons .cont .contson")
	private String charpterContentStr;
	
	@Html
	@HtmlField(cssPath=".right .sons .shisoncont")
	private String translateContentStr;
	
    private byte[] charpterContent;

    private byte[] translateContent;

    public byte[] getCharpterContent() {
        return charpterContent;
    }

    public void setCharpterContent(byte[] charpterContent) throws UnsupportedEncodingException {
        this.charpterContent = charpterContent;
        this.charpterContentStr = ConvertUtils.byte2Str(charpterContent);
    }

    public byte[] getTranslateContent() {
        return translateContent;
    }

    public void setTranslateContent(byte[] translateContent) throws UnsupportedEncodingException {
        this.translateContent = translateContent;
        this.translateContentStr = ConvertUtils.byte2Str(translateContent); 
    }

	public String getCharpterContentStr() {
		return charpterContentStr;
	}

	public void setCharpterContentStr(String charpterContentStr) throws UnsupportedEncodingException {
		this.charpterContentStr = charpterContentStr;
		this.charpterContent = ConvertUtils.str2Byte(charpterContentStr);
	}

	public String getTranslateContentStr() {
		return translateContentStr;
	}

	public void setTranslateContentStr(String translateContentStr) throws UnsupportedEncodingException {
		this.translateContentStr = translateContentStr;
		this.translateContent = ConvertUtils.str2Byte(translateContentStr);
	}
    
}