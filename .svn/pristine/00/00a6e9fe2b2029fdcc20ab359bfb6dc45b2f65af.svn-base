package com.example.tmooc;

import com.example.ActivityCollector.ActivityCollector;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActivityCollector.AddActivity(this) ;
	
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this) ;
	}
	
}
