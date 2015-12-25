package com.example.ActivityCollector;

import java.util.ArrayList;
import java.util.List;

import com.example.tmooc.SettingActivity;

import android.app.Activity;
import android.content.Context;

public class ActivityCollector {
	public static List<Activity> list = new ArrayList<Activity>() ;
	
	public static void AddActivity(Activity activity){
		list.add(activity) ;
	}
	public static void removeActivity(Activity activity){
		list.remove(activity) ;
	}
	public static void finishAll(){
		for (Activity activity : list) {
			if (!activity.isFinishing()) {
				activity.finish() ;
			}
		}
	}
}
