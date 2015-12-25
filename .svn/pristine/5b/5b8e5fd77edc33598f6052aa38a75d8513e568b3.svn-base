package com.example.tmooc;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.TabHost.TabSpec;

public class StudyIntent extends ActivityGroup{
	private Intent problem;
	private Intent  book;
	private TabHost tabHost;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.study);
		problem = new Intent(this,ProblemActivity.class);
		book = new Intent(this,BookActivity.class);
		
		
		
		tabHost = (TabHost) findViewById(R.id.mytabhost);
		tabHost.setup(this.getLocalActivityManager());
		TabWidget tabWidget = tabHost.getTabWidget();
		
		tabHost.addTab(tabHost.newTabSpec("问题").setIndicator("问题").setContent(problem));
		tabHost.addTab(tabHost.newTabSpec("文章").setIndicator("文章").setContent(book));
		
	}

	

}
