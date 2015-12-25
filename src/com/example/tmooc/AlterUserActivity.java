package com.example.tmooc;

import java.lang.reflect.Type;
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

import com.example.ActivityCollector.ActivityCollector;
import com.example.tmooc.entity.User;
import com.example.tmooc.utils.HttpUtils;
import com.example.tmooc.utils.MyConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AlterUserActivity extends BaseActivity {
	private TextView tv_userId ;
	private TextView et_userName ;
	private EditText et_newPassword ;
	private EditText et_rePassword ;
	private TextView tv_isVIP ;
	private Button btn_submit ;
	private static final int RESULT_CODE = 1 ; 
	private String[] maps = null ;
	
	private ProgressDialog dialog ;
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case RESULT_CODE:
				String result = (String) msg.obj ;
				if ("1".equals(result)) {
					Toast.makeText(getApplicationContext(), "修改成功",
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(AlterUserActivity.this,
							SettingActivity.class);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(getApplicationContext(), "修改失败",
							Toast.LENGTH_SHORT).show();
				}
				break;

			default:
				break;
			}
		}
	} ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_alter) ;
		
		//初始化组件
		initView() ;
		SharedPreferences userPreferences = getSharedPreferences("userId", MODE_PRIVATE) ;
		final int userId = userPreferences.getInt("userId_Message", 0) ;
		Log.i("aaa", userId+"") ;
		//加载页面，请求服务器，把服务器上的json获取到客户端
		maps = new String[]{MyConstants.URL+"findById",userId+""} ;
		new MyTask().execute(maps) ;
		
		btn_submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String password = et_newPassword.getText().toString().trim() ;
				final String repassword = et_rePassword.getText().toString().trim() ;
				if (!password.equals(repassword)) {
					Toast.makeText(AlterUserActivity.this, "你输入的两次密码不相同，请重新输入", Toast.LENGTH_SHORT).show() ;
				}else {
					if ("".equals(password)||null == password) {
						Toast.makeText(AlterUserActivity.this, "你输入的新密码为空，请重新输入", Toast.LENGTH_SHORT).show() ;
					}else{
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								try {
									HttpClient httpClient = new DefaultHttpClient();
									HttpPost httpPost = new HttpPost(MyConstants.URL
											+ "update");
									List<NameValuePair> params = new ArrayList<NameValuePair>();
									params.add(new BasicNameValuePair("user_id", tv_userId.getText().toString().trim()));
									params.add(new BasicNameValuePair("username", et_userName.getText().toString().trim()));
									params.add(new BasicNameValuePair("password", password));
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
										Log.i("aaa", "response:"+response) ;
										Message message = new Message();
										message.what = RESULT_CODE;
										message.obj = response;
										handler.sendMessage(message);
		
									} else {
										Toast.makeText(AlterUserActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show() ;
									}
		
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		
							}
						}).start() ;
					}
				}
			}
		}) ;
	}

	private void initView() {
		// TODO Auto-generated method stub
		tv_userId = (TextView)findViewById(R.id.tv_userId) ;
		et_userName = (TextView)findViewById(R.id.et_userName) ;
		et_newPassword	= (EditText)findViewById(R.id.et_newPassword) ;
		tv_isVIP = (TextView)findViewById(R.id.tv_isVIP) ;
		btn_submit = (Button)findViewById(R.id.btn_submit) ;
		et_rePassword	= (EditText)findViewById(R.id.et_rePassword) ;
	
		//初始化进度条
		dialog = new ProgressDialog(this) ;
		dialog.setTitle("提示") ;
		dialog.setMessage("正在加载。。。") ;
	}
	
	public class MyTask extends AsyncTask<String, Void, User>{

		@Override
		protected User doInBackground(String... params) {
			// TODO Auto-generated method stub
			Map<String, Object> map = new HashMap<String, Object>() ;
			map.put("user_id", params[1]) ;
			String strJson = HttpUtils.sendPostMethod(params[0], map, "utf-8") ;
			Log.i("aaa", "strJson:"+strJson) ;
			Type type = new TypeToken<User>(){}.getType() ;
			Gson gson = new Gson();
			User user = gson.fromJson(strJson, type) ;
			Log.i("aaa", "user:"+user+"") ;
			return user;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.show() ;
		}

		@Override
		protected void onPostExecute(User user) {
			// TODO Auto-generated method stub
			super.onPostExecute(user);
			Log.i("aaa", user+"") ;
			tv_userId.setText(user.getUser_id()+"") ;
			et_userName.setText(user.getUsername()) ;
			tv_isVIP.setText(user.isVip()+"") ;
			dialog.dismiss() ;
		}		
	}
	
}
