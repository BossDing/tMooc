package com.example.tmooc.utils ;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	/**
	 * 
	 * @param path
	 *            服务端发布json的地址
	 * @param encode
	 *            指定的编码格式(utf-8)
	 * @return
	 */
	public static String sendPostMethod(String path, String encode) {
		String result = "";

		HttpClient client = new DefaultHttpClient();

		try {
			// post方式请求
			HttpPost post = new HttpPost(path);
			// 获取服务器端的响应对象
			HttpResponse response = client.execute(post);
			// 判断请求是否成功
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获取服务器的响应的信息
				HttpEntity httpEntity = response.getEntity();

				result = EntityUtils.toString(httpEntity, encode);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			client.getConnectionManager().shutdown();
		}

		return result;
	}

	/**
	 * 发送请求并带参数
	 * @param path
	 * @param params
	 * @param encode
	 * @return
	 */
	public static String sendPostMethod(String path,
			Map<String, Object> params, String encode) {

		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(path);

		String result = "";
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();

		/*
		 * NameValuePair代表一个HEADER，List<NameValuePair>存储全部的头字段
		 * UrlEncodedFormEntity类似于URLEncoder语句进行URL编码 HttpPost类似于HTTP的POST请求
		 * client.execute()类似于发出请求，并返回Response
		 */
		try {
			if (params != null && !params.isEmpty()) {
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					String name = entry.getKey();
					String value = entry.getValue().toString();
					BasicNameValuePair valuePair = new BasicNameValuePair(name,
							value);
					parameters.add(valuePair);

				}
			}
			// 纯文本表单，不包含文件
			// 设置字符集
			UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(
					parameters, encode);
			httpPost.setEntity(encodedFormEntity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(httpResponse.getEntity(), encode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			httpClient.getConnectionManager().shutdown();
		}

		return result;
	}

	
	/**
	 * 获取图片
	 * @param path
	 * @return
	 */
	public static byte[] getImageView(String path){
		byte[] data = null;
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpPost post = new HttpPost(path);
			HttpResponse response = httpClient.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				data = EntityUtils.toByteArray(response.getEntity());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return data;
	}
}


