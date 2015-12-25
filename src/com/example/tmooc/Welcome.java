package com.example.tmooc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Welcome extends Activity implements AnimationListener{
	
	private ImageView imageView = null;
	private Animation alphaAnimation = null;
	
	private static final int TIME = 2000;
	private static final int GO_HOME = 1000;
	private static final int GO_GUIDE = 1001;
	private boolean isFirstIn = false;
	
	
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case GO_HOME:
				goHome();
				break;

			case GO_GUIDE:
				goGuide();
				break;
			}
		};
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.welcome);
		
//		init();
		welcomeAnimation();
	}
	
	private void welcomeAnimation() {
		// TODO Auto-generated method stub
		imageView = (ImageView) findViewById(R.id.imageView1);
		alphaAnimation = AnimationUtils.loadAnimation(this,R.anim.guu);
		//alphaAnimation.setFillEnabled(true); // 启动Fill保持
		//alphaAnimation.setFillAfter(true); // 设置动画的最后一帧是保持在View上面
		imageView.startAnimation(alphaAnimation);
		alphaAnimation.setAnimationListener(this); // 为动画设置监听
	}
//
//	private void init(){
//		SharedPreferences mPreferences = getSharedPreferences("test", MODE_PRIVATE);
//		isFirstIn = mPreferences.getBoolean("isFirst", true);
		
//		if (!isFirstIn) {
//			mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);
//		}else{
//			mHandler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
//			Editor editor = mPreferences.edit();
//			editor.putBoolean("isFirst", false);
//			editor.commit();
//		}
//	}
	
	private void goHome(){
		Intent i = new Intent(Welcome.this,LoginActivity.class);
		startActivity(i);
		finish();
	}
	
	private void goGuide(){
		Intent i = new Intent(Welcome.this,Guide.class);
		startActivity(i);
		finish();
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		// TODO Auto-generated method stub
		SharedPreferences mPreferences = getSharedPreferences("test", MODE_PRIVATE);
		isFirstIn = mPreferences.getBoolean("isFirst", true);
		if (!isFirstIn) {
			mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);
		}else{
			mHandler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
			Editor editor = mPreferences.edit();
			editor.putBoolean("isFirst", false);
			editor.commit();
		}
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub
		
	}

}
