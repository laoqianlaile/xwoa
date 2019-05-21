package com.centit.sys.util;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

/**
 * 
 * 使用阿里提供的Json API 格式化Json数据
 * 
 * @author sx
 * @create 2014-9-16
 * @version
 * fastjson文档地址：https://github.com/alibaba/fastjson/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98
 * fastJson,jackJson,Gson性能比较    http://chenyanxi.blog.51cto.com/4599355/1543445
 */
public class JsonResultUtils {

	private static Logger LOG = Logger.getLogger(JsonResultUtils.class);

	/**
	 * 格式化Json数据输出
	 * 
	 * @param response
	 */
	public void toJson(HttpServletResponse response) {
		toJson(response, new SimplePropertyPreFilter());
	}

	/**
	 * 格式化Json数据输出
	 * 
	 * @param response
	 * @param simplePropertyPreFilter
	 *            {@link SimplePropertyPreFilter} 格式化时过滤指定的属性
	 */
	public void toJson(HttpServletResponse response, SimplePropertyPreFilter simplePropertyPreFilter) {
		try {
			String text = JSONObject.toJSONString(params, simplePropertyPreFilter);

			text = "{\"code\":0, \"text\" : " + text + "}";
			response.getWriter().print(text);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);

			try {
				response.getWriter().print("{\"code\":500}");
			} catch (IOException e1) {
				LOG.error(e1.getMessage(), e1);
			}
		}
	}

	/**
	 * 输出错误信息
	 * 
	 * @param response
	 * @param errorMessage
	 *            错误消息
	 */
	public static void toErrorJson(HttpServletResponse response, ErrorMessageJson errorMessageJson) {
		try {
			response.getWriter().print(JSONObject.toJSONString(errorMessageJson));
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private Map<String, Object> params = new LinkedHashMap<String, Object>();

	public JsonResultUtils put(String key, Object value) {
		params.put(key, value);

		return this;
	}

	public JsonResultUtils(Map<String, Object> params) {
		super();
		this.params = params;
	}

	public JsonResultUtils() {
		super();
	}

	/**
	 * 
	 * 输出格式化的Json错误信息
	 * 
	 * @author sx
	 * @create 2014-9-29
	 * @version
	 */
	public static class ErrorMessageJson {
		private int code;

		private String fieldName;

		private String errorMessage;

		public ErrorMessageJson(int code, String fieldName, String errorMessage) {
			super();
			this.code = code;
			this.fieldName = fieldName;
			this.errorMessage = errorMessage;
		}

		public ErrorMessageJson(String fieldName, String errorMessage) {
			this(500, fieldName, errorMessage);
		}

		public ErrorMessageJson(String errorMessage) {
			this(500, "", errorMessage);
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

	}

}
