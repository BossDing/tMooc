package com.example.tmooc;

import java.util.ArrayList;
import java.util.List;

import com.example.tmooc.adapter.VideoAdapter;
import com.example.tmooc.entity.Video;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class InfoViewActivity extends Activity {
	private TextView tv;
	private ListView lv_video;
	
	
	private List<Video> videos = new ArrayList<Video>();

	private String Title[]= new String[20];
	private String ownerName[]= new String[20];
	private String PlayURL[]= new String[20];
	private String Time[]= new String[20];
	private String imageID[]= new String[20];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_view);
		
		Intent i = getIntent();
		Bundle b = i.getExtras();
		Title= b.getStringArray("Title");
		ownerName= b.getStringArray("ownerName");
		PlayURL= b.getStringArray("PlayURL");
		Time= b.getStringArray("Time");
		imageID= b.getStringArray("imageID");
		
		
		initVideoView();
		
		VideoAdapter adapter = new VideoAdapter(getApplicationContext(), R.layout.stu_item, videos);
		lv_video = (ListView) findViewById(R.id.videoListview);
		
		lv_video.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Intent i = new Intent(InfoViewActivity.this,SeeVideo.class);
				i.putExtra("URL", PlayURL[position]);
				startActivity(i);
			}
		});
		
		
		
		lv_video.setAdapter(adapter);
	}


	private void initVideoView() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 20; i++) {
			Video video1 = new Video(R.drawable.android, Title[i],Time[i]+"s", ownerName[i]);
			videos.add(video1);
		}
		
	}

	

}
