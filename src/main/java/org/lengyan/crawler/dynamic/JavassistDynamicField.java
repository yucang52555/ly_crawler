package org.lengyan.crawler.dynamic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.lengyan.crawler.annotation.JSONPath;
import org.lengyan.crawler.annotation.Request;
import org.lengyan.crawler.annotation.RequestParameter;
import org.lengyan.crawler.annotation.html.Ajax;
import org.lengyan.crawler.annotation.html.Attr;
import org.lengyan.crawler.annotation.html.FieldRenderName;
import org.lengyan.crawler.annotation.html.Href;
import org.lengyan.crawler.annotation.html.Html;
import org.lengyan.crawler.annotation.html.HtmlField;
import org.lengyan.crawler.annotation.html.Image;
import org.lengyan.crawler.annotation.html.Text;

import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.StringMemberValue;

/**
 * 动态生成属性的注解
 * 
 * @author huchengyi
 *
 */
public class JavassistDynamicField implements DynamicField {
	
	private static Log log = LogFactory.getLog(JavassistDynamicField.class);
	
	private DynamicBean dynamicBean;
	
	private CtField cfield;
	
	private ConstPool cpool;
	
	private AnnotationsAttribute attr;
	
	public JavassistDynamicField(DynamicBean dynamicBean, CtClass clazz, ConstPool cpool, String fieldName) {
		try {
			this.dynamicBean = dynamicBean;
			this.cpool = cpool;
			this.cfield  = clazz.getField(fieldName);
			attr = new AnnotationsAttribute(cpool, AnnotationsAttribute.visibleTag);
		} catch (NotFoundException e) {
			log.error(fieldName + " not found");
		}
	}
	
	public DynamicBean build() {
        FieldInfo finfo = cfield.getFieldInfo();
        finfo.addAttribute(attr);
        return dynamicBean;
	}

	@Deprecated
	@Override
	public DynamicField htmlField(String cssPath) {
		return csspath(cssPath);
	}
	
	@Override
	public DynamicField csspath(String cssPath) {
		Annotation annot = new Annotation(HtmlField.class.getName(), cpool);
        annot.addMemberValue("cssPath", new StringMemberValue(cssPath, cpool));
		attr.addAnnotation(annot);
		return this;
	}

	@Override
	public DynamicField text(boolean own) {
        Annotation annot = new Annotation(Text.class.getName(), cpool);
        annot.addMemberValue("own", new BooleanMemberValue(own, cpool));
        attr.addAnnotation(annot);
		return this;
	}
	
	@Override
	public DynamicField text() {
		return text(true);
	}

	@Override
	public DynamicField html(boolean outer) {
		Annotation annot = new Annotation(Html.class.getName(), cpool);
        annot.addMemberValue("outer", new BooleanMemberValue(outer, cpool));
        attr.addAnnotation(annot);
		return this;
	}

	@Override
	public DynamicField href(boolean click, String... value) {
		Annotation annot = new Annotation(Href.class.getName(), cpool);
        annot.addMemberValue("click", new BooleanMemberValue(click, cpool));
        
        ArrayMemberValue arrayMemberValue = new ArrayMemberValue(cpool);
        MemberValue[] memberValues = new StringMemberValue[value.length];
        for(int i = 0; i < value.length; i++) {
        	memberValues[i] = new StringMemberValue(value[i], cpool);
        }
        arrayMemberValue.setValue(memberValues);
        annot.addMemberValue("value", arrayMemberValue);
        
        attr.addAnnotation(annot);
		return this;
	}

	@Override
	public DynamicField href(String... value) {
		return href(false, value);
	}
	
	@Override
	public DynamicField image(String download, String... value) {
		Annotation annot = new Annotation(Image.class.getName(), cpool);
        annot.addMemberValue("download", new StringMemberValue(download, cpool));
        
        ArrayMemberValue arrayMemberValue = new ArrayMemberValue(cpool);
        MemberValue[] memberValues = new StringMemberValue[value.length];
        for(int i = 0; i < value.length; i++) {
        	memberValues[i] = new StringMemberValue(value[i], cpool);
        }
        arrayMemberValue.setValue(memberValues);
        annot.addMemberValue("value", arrayMemberValue);
        
        attr.addAnnotation(annot);
		return this;
	}

	@Override
	public DynamicField image() {
		return image("");
	}

	@Override
	public DynamicField attr(String value) {
		Annotation annot = new Annotation(Attr.class.getName(), cpool);
        annot.addMemberValue("value", new StringMemberValue(value, cpool));
        attr.addAnnotation(annot);
		return this;
	}

	@Override
	public DynamicField ajax(String url) {
		Annotation annot = new Annotation(Ajax.class.getName(), cpool);
        annot.addMemberValue("url", new StringMemberValue(url, cpool));
        attr.addAnnotation(annot);
		return this;
	}

	@Override
	public DynamicField request() {
		Annotation annot = new Annotation(Request.class.getName(), cpool);
        attr.addAnnotation(annot);
		return this;
	}

	@Override
	public DynamicField requestParameter(String param) {
		Annotation annot = new Annotation(RequestParameter.class.getName(), cpool);
        annot.addMemberValue("value", new StringMemberValue(param, cpool));
        attr.addAnnotation(annot);
		return this;
	}

	@Override
	public DynamicField requestParameter() {
		return requestParameter("");
	}

	@Override
	public DynamicField jsvar(String var, String jsonpath) {
		Annotation annot = new Annotation(RequestParameter.class.getName(), cpool);
        annot.addMemberValue("var", new StringMemberValue(var, cpool));
        annot.addMemberValue("jsonpath", new StringMemberValue(jsonpath, cpool));
        attr.addAnnotation(annot);
		return this;
	}

	@Override
	public DynamicField jsvar(String var) {
		return jsvar(var, "");
	}

	@Override
	public DynamicField jsonpath(String value) {
		Annotation annot = new Annotation(JSONPath.class.getName(), cpool);
        annot.addMemberValue("value", new StringMemberValue(value, cpool));
        attr.addAnnotation(annot);
		return this;
	}
	
	@Override
	public DynamicField renderName(String value) {
		Annotation renderName = new Annotation(FieldRenderName.class.getName(), cpool);
		renderName.addMemberValue("value", new StringMemberValue(value, cpool));
        attr.addAnnotation(renderName);
		return this;
	}
	
	@Override
	public DynamicField customAnnotation(Annotation annotation) {
        attr.addAnnotation(annotation);
		return this;
	}
	
	@Override
	public ConstPool getConstPool(){
		return this.cpool;
	}
}
