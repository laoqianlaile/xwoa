package com.centit.webservice.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.centit.sys.util.DateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

    /**
     * 从一个JSON 对象字符格式中得到一个java对象
     * 
     * @param jsonString
     * @param pojoCalss
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Object getObject4JsonString(String jsonString, Class pojoCalss) {
        Object pojo;
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        pojo = JSONObject.toBean(jsonObject, pojoCalss);
        return pojo;
    }

    /**
     * 从一个JSON 对象字符格式中得到一个java对象
     * 
     * @param jsonString
     * @param pojoCalss
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Object getObject4GsonString(String jsonString, Class pojoCalss) {
        Object pojo;
        Map<String, Object> Map = JsonUtil.getMap4Json(jsonString);
        JSONObject parasJson = (JSONObject) Map.get("paras");
        Gson gson = new Gson();
        pojo = gson.fromJson(parasJson.toString(), pojoCalss);
        return pojo;
    }

    /**
     * 从json HASH表达式中获取一个map，改map支持嵌套功能
     * 
     * @param jsonString
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map getMap4Json(String jsonString) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Iterator keyIter = jsonObject.keys();
        String key;
        Object value;
        Map valueMap = new HashMap();

        while (keyIter.hasNext()) {
            key = (String) keyIter.next();
            value = jsonObject.get(key);
            valueMap.put(key, value);
        }

        return valueMap;
    }

    /**
     * 从json数组中得到相应java数组
     * 
     * @param jsonString
     * @return
     */
    public static Object[] getObjectArray4Json(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        return jsonArray.toArray();

    }

    /**
     * 从json对象集合表达式中得到一个java对象列表
     * 
     * @param jsonString
     * @param pojoClass
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List getList4Json(String jsonString, Class pojoClass) {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        Object pojoValue;

        List list = new ArrayList();
        for (int i = 0; i < jsonArray.size(); i++) {

            jsonObject = jsonArray.getJSONObject(i);
            pojoValue = JSONObject.toBean(jsonObject, pojoClass);
            list.add(pojoValue);

        }
        return list;

    }

    /**
     * 从json数组中解析出java字符串数组
     * 
     * @param jsonString
     * @return
     */
    public static String[] getStringArray4Json(String jsonString) {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        String[] stringArray = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            stringArray[i] = jsonArray.getString(i);

        }

        return stringArray;
    }

    /**
     * 从json数组中解析出javaLong型对象数组
     * 
     * @param jsonString
     * @return
     */
    public static Long[] getLongArray4Json(String jsonString) {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Long[] longArray = new Long[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            longArray[i] = jsonArray.getLong(i);

        }
        return longArray;
    }

    /**
     * 从json数组中解析出java Integer型对象数组
     * 
     * @param jsonString
     * @return
     */
    public static Integer[] getIntegerArray4Json(String jsonString) {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Integer[] integerArray = new Integer[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            integerArray[i] = jsonArray.getInt(i);

        }
        return integerArray;
    }

    /**
     * 从json数组中解析出java Date 型对象数组，使用本方法必须保证
     * 
     * @param jsonString
     * @return
     */
    public static Date[] getDateArray4Json(String jsonString, String DataFormat) {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Date[] dateArray = new Date[jsonArray.size()];
        String dateString;
        Date date;

        for (int i = 0; i < jsonArray.size(); i++) {
            dateString = jsonArray.getString(i);
            date = DateUtil.dateStr2Date(dateString);
            dateArray[i] = date;

        }
        return dateArray;
    }

    /**
     * 从json数组中解析出java Double型对象数组
     * 
     * @param jsonString
     * @return
     */
    public static Double[] getDoubleArray4Json(String jsonString) {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Double[] doubleArray = new Double[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            doubleArray[i] = jsonArray.getDouble(i);

        }
        return doubleArray;
    }

    /**
     * 将java对象转换成json字符串
     * 
     * @param javaObj
     * @return
     */
    public static String getJsonString4JavaPOJO(Object javaObj) {

        JSONObject json;
        json = JSONObject.fromObject(javaObj);
        return json.toString();

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO 自动生成方法存根

    }

    public static String createJsonString(Object value) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String string = gson.toJson(value);
        return string;
    }

    /**
     * 列表返回数据 版本1.0（比如个人日程列表）
     * 
     * @param value
     * @return
     */
    public static String createSimpleListJsonString(Object value) {

        return createJsonString(value, 1.0);
    }

    /**
     * 列表返回数据 版本1.1（比如领导日程列表）
     * 
     * @param value
     * @return
     */
    public static String createOtherleListJsonString(Object value) {

        return createJsonString(value, 1.1);
    }

    /**
     * 查看返回数据 版本2.0（比如个人日程查看）
     * 
     * @param value
     * @return
     */
    public static String createSimpleFormJsonString(Object value) {

        return createJsonString(value, 2.0);
    }

    /**
     * 查看返回数据 版本2.1（比如领导日程查看）
     * 
     * @param value
     * @return
     */
    public static String createOtherFormJsonString(Object value) {
        
        return createJsonString(value, 2.1);

    }

    /***
     * 指定版本序列化json
     * 
     * @param value
     * @param version
     * @return
     */
    public static String createJsonString(Object value, double version) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .enableComplexMapKeySerialization().setVersion(version).create();
        String string = gson.toJson(value);
        return string;
    }

    /**
     * 各业务节点可调用此方法生成业务JSON： 解决过滤空值问题
     * 
     * @param obj
     * @return
     */
    public static Object initObject4WithOutNull(Object obj) {
        JsonConfig jsonConfig = new JsonConfig();

        // 解决过滤空值问题
        PropertyFilter filter = new PropertyFilter() {
            public boolean apply(Object object, String fieldName,
                    Object fieldValue) {
                return null == fieldValue;
            }
        };
        jsonConfig.setJsonPropertyFilter(filter);

        return JSONObject.fromObject(obj, jsonConfig);
    }

    /**
     * 处理每个element的空值问题
     */
    public static String replaceNullString(String str) {
        if (str == null)
            return "";
        else
            return str;
    }

    
    
    /** 
    * 判断是否是json结构 
    */ 
    public static boolean isJson(String value) { 
    try { 
        JSONObject.fromObject(value);
    } catch (JSONException e) { 
    return false; 
    } 
    return true; 
    } 

}
