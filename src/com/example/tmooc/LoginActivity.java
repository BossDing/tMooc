package com.example.tmooc;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.example.tmooc.entity.User;
import com.example.tmooc.utils.MyConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText etName;
	private EditText etPassword;
	private CheckBox cbRembmerPwd;
	private Button btn1 ;
	private Button btn2 ;
	private SharedPreferences sharedPreferences ;
	private SharedPreferences.Editor editor;
	private Boolean isRemember = false ;
	protected static final int RESULT_CODE = 1;
	
	public Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case RESULT_CODE:
				String message = (String) msg.obj ;
				if ("0".equals(message)) {
					Toast.makeText(LoginActivity.this, "�û������������", Toast.LENGTH_SHORT).show() ;
				}else {
					if(cbRembmerPwd.isChecked()){
						editor.putString("UserName", etName.getText().toString()) ;
						editor.putString("UserPassword", etPassword.getText().toString()) ;
						editor.putBoolean("IsRememberInfo", true) ;
					}else {
						editor.clear() ;
					}
					editor.commit() ;
					
					Type type=new TypeToken<User>(){}.getType();
					Gson gson = new Gson() ;
					User user = gson.fromJson(message,type) ;
					SharedPreferences idPreferences = getSharedPreferences("userId", MODE_PRIVATE) ;
					SharedPreferences.Editor id_editor = idPreferences.edit() ;
					id_editor.putInt("userId_Message", user.getUser_id()) ;
					id_editor.commit() ;
 					Intent intent = new Intent(LoginActivity.this,MainActivity.class) ;
					startActivity(intent) ;
					finish() ;
				}
				break;

			default:
				break;
			}
		}
	} ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initViews();
		
		
		
		
		
		
		isRemember = sharedPreferences.getBoolean("IsRememberInfo", false) ;
		if (isRemember) {
			etName.setText(sharedPreferences.getString("UserName", "")) ;
			etPassword.setText(sharedPreferences.getString("UserPassword", "")) ;
			cbRembmerPwd.setChecked(true) ;
		}
		
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this,
						RegistActivity.class);
				startActivity(intent);

			}
		});
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if (!isNetworkAvailable(getApplicationContext())) {
					Toast.makeText(getApplicationContext(), "��û����", Toast.LENGTH_SHORT).show();
				}else{
				
				final String name = etName.getText().toString();
				final String password = etPassword.getText().toString();

				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							HttpClient httpClient = new DefaultHttpClient();
							HttpPost httpPost = new HttpPost(MyConstants.URL
									+ "login");
							List<NameValuePair> params = new ArrayList<NameValuePair>();
							params.add(new BasicNameValuePair("username", name));
							params.add(new BasicNameValuePair("password",
									password));
							UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
									params, "UTF-8");
							httpPost.setEntity(entity);
							HttpResponse httpResponse = httpClient
									.execute(httpPost);
							if (httpResponse.getStatusLine().getStatusCode() == 200) {// ��Ӧ�ɹ�
								HttpEntity httpEntity = httpResponse
										.getEntity();
								String response = EntityUtils.toString(
										httpEntity, "UTF-8");
								Message message = new Message();
								message.what = RESULT_CODE;
								message.obj = response;
								handler.sendMessage(message);

							} else {
								Toast.makeText(LoginActivity.this, "���ӷ�����ʧ��", Toast.LENGTH_SHORT).show() ;
							}

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}).start();
			}}

		});

	}

	private void initViews() {

		etName = (EditText) findViewById(R.id.et_login_name);
		etPassword = (EditText) findViewById(R.id.et_login_password);
		cbRembmerPwd = (CheckBox) findViewById(R.id.cb_login_remeberPwd);
		btn1 = (Button) findViewById(R.id.tb_login);
		btn2 = (Button) findViewById(R.id.btn_register);
		sharedPreferences = getSharedPreferences("config",Context.MODE_PRIVATE);
		editor = sharedPreferences.edit() ;
		
	}

	
	public static boolean isNetworkAvailable(Context context){
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null) {
			return false;
		}else{
			NetworkInfo[] info = cm.getAllNetworkInfo();
			for (int i = 0; i < info.length; i++) {
				if (info[i].getState()==NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}
	
	
}
