package com.centit.webservice.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class PostMsg {
	private static HttpClient httpClient = HttpClientBuilder.create().build();
	
	public static String get(String url,  String appid) {
        // HttpClient httpClient = HttpClientBuilder.create().build(); // Use
        // this

        String response = "";

        try {
        //    StringEntity paramesEntity = new StringEntity(jsonString, "utf-8");
         //   paramesEntity.setContentType("application/json;charset=utf-8");
        //    HttpPost post = new HttpPost(url);
            HttpGet get = new HttpGet(url);
            get.setHeader("appid", appid);//太极验证参数
            get.setHeader("Content-Type","application/json;charset=utf-8");
            HttpResponse getResponse = httpClient.execute(get);
            HttpEntity getEntity = getResponse.getEntity();
            if (null != getEntity) {
                InputStream inStream = getEntity.getContent();
                response = convertStreamToString(inStream);
                get.abort();
                System.out.println(response);
                // result.setServerInfo(serverResult);
            }

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;
    }
	
	public static String post(String url, String jsonString, String appid) {
		// HttpClient httpClient = HttpClientBuilder.create().build(); // Use
		// this

		String response = "";

		try {
			StringEntity paramesEntity = new StringEntity(jsonString, "utf-8");
			paramesEntity.setContentType("application/json;charset=utf-8");
			HttpPost post = new HttpPost(url);
			post.setEntity(paramesEntity);
			post.setHeader("appid", appid);
			HttpResponse postResponse = httpClient.execute(post);
			HttpEntity postEntity = postResponse.getEntity();
			if (null != postEntity) {
				InputStream inStream = postEntity.getContent();
				response = convertStreamToString(inStream);
				post.abort();
				System.out.println(response);
				// result.setServerInfo(serverResult);
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	public static String postBody(String url, Map<String, String> map, String appid) {
		// HttpClient httpClient = HttpClientBuilder.create().build(); // Use
		// this

		String response = "";

		try {
			 System.out.println("url: "+url);
			HttpPost post = new HttpPost(url);
			
			List<NameValuePair> list = new ArrayList<NameValuePair>(); 
			Iterator<Entry<String, String>> iterator = map.entrySet().iterator();  
            while(iterator.hasNext()){  
                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
            }  
            if(list.size() > 0){  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,Consts.UTF_8);  
                post.setEntity(entity);  
            }  
			
		//	post.setEntity(paramesEntity);
			post.setHeader("appid", appid);
			post.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			HttpResponse postResponse = httpClient.execute(post);
			HttpEntity postEntity = postResponse.getEntity();
			if (null != postEntity) {
				InputStream inStream = postEntity.getContent();
				response = convertStreamToString(inStream);
				post.abort();
				System.out.println(response);
				// result.setServerInfo(serverResult);
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	/**
	 * 转换stream为string字符串
	 * 
	 * @param is
	 *            输入流
	 * @return 流形成的字符串
	 * @throws UnsupportedEncodingException 
	 */
	private static String convertStreamToString(InputStream is) throws UnsupportedEncodingException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));//转换获取json中文编码格式
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
