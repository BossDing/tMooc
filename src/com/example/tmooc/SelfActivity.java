package com.example.tmooc;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tmooc.entity.User;
import com.example.tmooc.utils.HttpUtils;
import com.example.tmooc.utils.MyConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SelfActivity extends BaseActivity implements OnClickListener{
	
	
	protected static final int USER = 1;
	protected static final int USERNAME = 0;
	private ImageView iv_avatar ;
	private TextView tv_username ;
	private RelativeLayout rl_course_collect ;
	private RelativeLayout rl_download ;
	private RelativeLayout rl_mytask ;
	private RelativeLayout rl_vip ;
	private RelativeLayout rl_usersetting ;
	
	private ProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_self);
		
		//��ʼ�����
		initView() ;
		//��ȡ�û�����
		SharedPreferences idPreferences = getSharedPreferences("userId", MODE_PRIVATE) ;
		final int userId = idPreferences.getInt("userId_Message", 0) ;
		Log.i("aaa", userId+"") ;
			// ����ҳ�棬������������ѷ������ϵ�JSON���ݻ�ȡ��������JSON->Java���϶���
			// ͨ����������������ʾ��ListView��
			String[] maps ={MyConstants.URL+"findById",userId+""} ;
			new MyTask().execute(maps);
	}
	private void initView() {
		// TODO Auto-generated method stub
		iv_avatar = (ImageView)findViewById(R.id.iv_avatar) ;
		tv_username = (TextView)findViewById(R.id.tv_username) ;
		rl_course_collect = (RelativeLayout)findViewById(R.id.rl_course_collect) ;
		rl_download = (RelativeLayout)findViewById(R.id.rl_download) ;
		rl_mytask = (RelativeLayout)findViewById(R.id.rl_mytask) ;
		rl_usersetting = (RelativeLayout)findViewById(R.id.rl_usersetting) ;
		rl_vip = (RelativeLayout)findViewById(R.id.rl_vip) ;
		
		rl_course_collect.setOnClickListener(this) ;
		rl_download.setOnClickListener(this) ;
		rl_mytask.setOnClickListener(this) ;
		rl_usersetting.setOnClickListener(this) ;
		rl_vip.setOnClickListener(this) ;
		
		//��ʼ��������
		dialog = new ProgressDialog(this);
		dialog.setTitle("��ʾ");
		dialog.setMessage("���ڼ���...");
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_course_collect:
			Intent intent1 = new Intent(SelfActivity.this,CourseCollectActivity.class) ;
			startActivity(intent1) ;
			break;
		case R.id.rl_download:
			Toast.makeText(this, "��δ���", Toast.LENGTH_SHORT).show() ;
			break ;
		case R.id.rl_mytask:
			Toast.makeText(this, "��δ���", Toast.LENGTH_SHORT).show() ;
			break;
		case R.id.rl_vip:
			Toast.makeText(this, "��δ���", Toast.LENGTH_SHORT).show() ;
			break;
		case R.id.rl_usersetting:
			Intent intent2 = new Intent(this,SettingActivity.class) ;
			startActivity(intent2) ;
			break;
		default:
			break;
		}
	}
	public class MyTask extends AsyncTask<String,Void,User> {
		/**
		 * ִ��֮ǰ���ô˷���
		 */
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.show();
		}

		/**
		 * ��ִ̨�в���
		 */

		@Override
		protected User doInBackground(String... params) {
			Map<String, Object> map = new HashMap<String, Object>() ;
			map.put("user_id", params[1]) ;
			String strJson = HttpUtils.sendPostMethod(params[0],map,"utf-8");
			Log.i("aaa", strJson);
			Type type=new TypeToken<User>(){}.getType();
			Gson gson=new Gson();
			User user = gson.fromJson(strJson, type);
			return user;
		}

		/**
		 * ִ���������
		 */

		@Override
		protected void onPostExecute(User user) {
			// TODO Auto-generated method stub
			super.onPostExecute(user);
			tv_username.setText(user.getUsername()) ;
			dialog.dismiss();

		}
	}

}