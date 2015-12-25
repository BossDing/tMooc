package com.example.tmooc;

import java.util.ArrayList;
import java.util.List;

import com.example.tmooc.adapter.ViewPagerAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Guide extends Activity implements OnPageChangeListener{
	private ViewPager vp;
	private ViewPagerAdapter vpAdapter;
	private List<View> views;
	private ImageView[] dots;
	private int[] ids={R.id.iv1,R.id.iv2,R.id.iv3};
	private Button start_btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.guide);
		
		initView();
		
		initDots();
	}

	//hahahahahahahahahahahhahaahhahaahye

	private void initView() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(this);
		
		views = new ArrayList<View>();
		views.add(inflater.inflate(R.layout.one, null));
		views.add(inflater.inflate(R.layout.two, null));
		views.add(inflater.inflate(R.layout.three, null));
		
		start_btn = (Button) views.get(2).findViewById(R.id.strat_btn);
		start_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("执行到此处");
				Intent i = new Intent(Guide.this,LoginActivity.class);
				startActivity(i);
				finish();
			}
		});
		
		
		vpAdapter = new ViewPagerAdapter(views, this);
		vp = (ViewPager) findViewById(R.id.viewpager);
		
		vp.setAdapter(vpAdapter);
		
		vp.setOnPageChangeListener(this);
		
		
	}
	
	private void initDots() {
		// TODO Auto-generated method stub
		dots = new ImageView[views.size()];
		for (int i = 0; i < views.size(); i++) {
			dots[i] = (ImageView) findViewById(ids[i]);
		}
	}



	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		for (int i = 0; i < ids.length; i++) {
			if (arg0==i) {
				dots[i].setImageResource(R.drawable.u);
			}else{
				dots[i].setImageResource(R.drawable.p);
			}
		}
	}
	
	

}
