package org.lengyan.crawler.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * Created by Rock on 2015/1/2 0002.
 */
public class JsonUtils {

    /**
     * @param inputObject inputObject
     * @return String
     * @discription 对象转json
     * @author rock 创建时间 2014年1月22日 下午12:17:10
     */
    public static String objectToJson(Object inputObject) {
        return JSON.toJSONString(inputObject);
    }

    /**
     * @param inputObject inputObject
     * @return String
     * @discription 对象转json
     * @author rock 创建时间 2014年1月22日 下午12:17:10
     */
    public static String objectToJson(Object inputObject, SerializeFilter filter) {
        return JSON.toJSONString(inputObject, filter);
    }
    
    /**
     * 更全面的自定义json转化规则
     * @param inputObject
     * @param filters SerializeFilter子接口数组
     * @param features SerializerFeature数组
     * @author oycw
     * @return
     */
    public static String objectToJson(Object inputObject, SerializeFilter[] filters,SerializerFeature... features) {
        SerializeWriter sw = new SerializeWriter();  
        JSONSerializer serializer = new JSONSerializer(sw);   
        for (SerializeFilter filter : filters) {
			if(filter instanceof ValueFilter)
				serializer.getValueFilters().add((ValueFilter)filter);
			if(filter instanceof PropertyFilter)
				serializer.getPropertyFilters().add((PropertyFilter)filter);
			if(filter instanceof NameFilter)
				serializer.getNameFilters().add((NameFilter)filter);
		}
        for (SerializerFeature serializerFeature : features) {
        	 serializer.config(serializerFeature, true);
		}
        serializer.write(inputObject);    
        return sw.toString() ; 
    }
    
    
    /**
     *
     * @discription 将JSON转换为对象
     * 201704 oycw 对于多层次json，比较好的做法是转换为 JsonObject，参考RecoveryOrderServiceImpl
     * @author rock 创建时间 2014年1月25日 上午9:19:56
     * @param json
     *            JSON
     * @param obj
     *            obj
     * @param <T>
     *            T
     * @return T t
     */
    public static <T> T jsonToObject(String json, Class<T> obj) {
        return JSON.parseObject(json, obj);
    }
    
    public static JSONObject jsonToObject(String json) {
        return JSON.parseObject(json);
    }

    /**
     *
     * @discription 将JSON转换为对象
     * @author rock 创建时间 2014年1月25日 上午9:19:56
     * @param json
     *            JSON
     * @param obj
     *            obj
     * @param <T>
     *            T
     * @return T t
     */
    public static <T> List<T> jsonToArray(String json, Class<T> obj) {
        return JSON.parseArray(json, obj);
    }
    
	/**
	 * JSON字符串特殊字符处理，比如：“\A1;1300”
	 * 
	 * @param s
	 * @return String
	 */
	public static String string2Json(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
				case '\"':
					sb.append("\\\"");
					break;
				case '\\':
					sb.append("\\\\");
					break;
				case '/':
					sb.append("\\/");
					break;
				case '\b':
					sb.append("\\b");
					break;
				case '\f':
					sb.append("\\f");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\r':
					sb.append("\\r");
					break;
				case '\t':
					sb.append("\\t");
					break;
				default:
					sb.append(c);
			}
		}
		return sb.toString();
	}
    
}
