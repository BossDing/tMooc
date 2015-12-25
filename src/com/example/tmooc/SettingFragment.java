package com.example.tmooc;

import com.example.ActivityCollector.ActivityCollector;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import android.widget.Toast;

public class SettingFragment extends PreferenceFragment {
	Preference preference_user  ;
	Preference preference_update ;
	Preference preference_software_explain ;
	Preference preference_software_author ;
	Preference preference_exit ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.setting_preference) ;
		//初始化数据
		initView() ;
		preference_user.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),AlterUserActivity.class) ;
				startActivity(intent) ;
				return false;
			}
		}) ;
		preference_update.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "未完成", Toast.LENGTH_SHORT).show() ;
				return false;
			}
		}) ;
		preference_software_explain.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "未完成", Toast.LENGTH_SHORT).show() ;
				return false;
			}
		}) ;
		preference_software_author.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "未完成", Toast.LENGTH_SHORT).show() ;
				return false;
			}
		}) ;
		preference_exit.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),LoginActivity.class) ;
				startActivity(intent) ;
				ActivityCollector.finishAll() ; 
				return false;
			}
		}) ;
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		preference_user = findPreference("user_setting") ;
		preference_update = findPreference("update_software") ;
		preference_software_explain = findPreference("software_explain") ;
	    preference_software_author = findPreference("software_author") ;
		preference_exit = findPreference("user_exit") ;
	}
	
}
