package com.example.tmooc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.example.tmooc.utils.HttpUtils;
import com.example.tmooc.utils.MyConstants;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistActivity extends Activity {

	private EditText username;
	private EditText password;
	private EditText repassword;
	private Button register;
	private TextView prompt;
	private boolean isRemeber2;
	private SharedPreferences sharedPreferences;
	public static final int LOGIN = 1;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case LOGIN:
				String result = (String) msg.obj;
				if ("1".equals(result)) {
					Toast.makeText(getApplicationContext(), "注册成功",
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(RegistActivity.this,
							LoginActivity.class);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(getApplicationContext(), "注册失败",
							Toast.LENGTH_SHORT).show();
				}
				break;

			case 0:
				Toast.makeText(getApplicationContext(), "用户名重复",
						Toast.LENGTH_SHORT).show();
				break;
			default:
				
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		username = (EditText) findViewById(R.id.et_register_name);
		password = (EditText) findViewById(R.id.et_register_password);
		repassword = (EditText) findViewById(R.id.et_register_repassword);
		prompt = (TextView) findViewById(R.id.tv_register_Prompt);
		register = (Button) findViewById(R.id.bt_register);
		
		
		
		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!LoginActivity.isNetworkAvailable(getApplicationContext())) {
					Toast.makeText(getApplicationContext(), "你没联网", Toast.LENGTH_SHORT).show();
				}else{
				
				
				
				
				final String n = username.getText().toString();
				final String p = password.getText().toString();
				if (n.equals("")) {
					Toast.makeText(getApplicationContext(), "用户名不能为空！", Toast.LENGTH_SHORT).show();
				}else{
					
					if (p.equals("")) {
						Toast.makeText(getApplicationContext(), "密码不能为空！", Toast.LENGTH_SHORT).show();
					}else{
				if (password.getText().toString()
						.equals(repassword.getText().toString())) {
					new Thread(new Runnable() {

						@Override
						public void run() {
							try {
								Map<String, Object> map=new HashMap<String, Object>();
								map.put("username", n);
								if(HttpUtils.sendPostMethod(MyConstants.URL + "findUserByUserName", map, "utf-8").equals("0")){
								HttpClient httpClient = new DefaultHttpClient();
								HttpPost httpPost = new HttpPost(
										MyConstants.URL + "regist");
								List<NameValuePair> params = new ArrayList<NameValuePair>();
								params.add(new BasicNameValuePair("username", n));
								params.add(new BasicNameValuePair("password",
										p));
								UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
										params, "UTF-8");
								httpPost.setEntity(entity);
								HttpResponse httpResponse = httpClient
										.execute(httpPost);
								if (httpResponse.getStatusLine().getStatusCode() == 200) {// 响应成功
									HttpEntity httpEntity = httpResponse
											.getEntity();
									String response = EntityUtils.toString(
											httpEntity, "UTF-8");
									Message message = new Message();
									message.what = LOGIN;
									message.obj = response;
									handler.sendMessage(message);

								}else{
									
								}
								}else{	
									Message message = new Message();
									message.what = 0;
									handler.sendMessage(message);
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}).start();

				} else {

					prompt.setText("两次密码不相同");
				}
				}}
			}}
		});

	}


}
