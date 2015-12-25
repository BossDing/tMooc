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
	 *            ����˷���json�ĵ�ַ
	 * @param encode
	 *            ָ���ı����ʽ(utf-8)
	 * @return
	 */
	public static String sendPostMethod(String path, String encode) {
		String result = "";

		HttpClient client = new DefaultHttpClient();

		try {
			// post��ʽ����
			HttpPost post = new HttpPost(path);
			// ��ȡ�������˵���Ӧ����
			HttpResponse response = client.execute(post);
			// �ж������Ƿ�ɹ�
			if (response.getStatusLine().getStatusCode() == 200) {
				// ��ȡ����������Ӧ����Ϣ
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
	 * �������󲢴�����
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
		 * NameValuePair����һ��HEADER��List<NameValuePair>�洢ȫ����ͷ�ֶ�
		 * UrlEncodedFormEntity������URLEncoder������URL���� HttpPost������HTTP��POST����
		 * client.execute()�����ڷ������󣬲�����Response
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
			// ���ı������������ļ�
			// �����ַ���
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
	 * ��ȡͼƬ
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


