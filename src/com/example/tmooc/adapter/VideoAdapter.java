package com.example.tmooc.adapter;

import java.util.List;


import com.example.tmooc.R;
import com.example.tmooc.entity.Video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VideoAdapter extends ArrayAdapter<Video> {
	
	private int resourceId;
	private Context context;
	private ViewHolder viewHolder;
	

	public VideoAdapter(Context context, int textViewResourceId,
			List<Video> objects) {
		super(context, textViewResourceId, objects);
		resourceId= textViewResourceId;
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Video video = getItem(position);
		View view;
		if (convertView == null) {
			view = LayoutInflater.from(context).inflate(resourceId, null);
			viewHolder=new ViewHolder();
			viewHolder.videoPhoto = (ImageView) view.findViewById(R.id.iv_stuView_photo);
			viewHolder.videoName = (TextView) view.findViewById(R.id.tv_stuView_title);
			viewHolder.videoTime = (TextView) view.findViewById(R.id.tv_stuView_time);
			viewHolder.videoAuthor = (TextView) view.findViewById(R.id.tv_stuView_name);
			viewHolder.timelong = (TextView) view.findViewById(R.id.timelong);
			viewHolder.zhuz = (TextView) view.findViewById(R.id.zhuz);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder=(ViewHolder) view.getTag();
		}
		viewHolder.videoPhoto.setImageResource(video.getImageID());
		viewHolder.videoName.setText(video.getVideoName());
		viewHolder.videoTime.setText(video.getVideoTime());
		viewHolder.videoAuthor.setText(video.getVideoAuthor());
		return view;
		
	}

	class ViewHolder {
		ImageView videoPhoto;
		TextView videoName;
		TextView videoTime;
		TextView videoAuthor;
		TextView timelong;
		TextView zhuz;
	}
}
