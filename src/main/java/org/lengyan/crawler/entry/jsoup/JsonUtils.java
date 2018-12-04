package org.lengyan.crawler.entry.jsoup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static void main(String[] args) {
        //list转换为json
        List<Map<String,String>> toList = new ArrayList<Map<String,String>>();
        String jsonStr = JSON.toJSON(toList).toString();
        System.out.println(jsonStr);

        //json转换为list
        List<Map> fromList = new ArrayList<>();
        fromList = JSONObject.parseArray(jsonStr, Map.class);
        System.out.println(fromList);
    }
}
