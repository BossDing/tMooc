package com.example.tmooc.adapter;





import com.example.tmooc.R;
import com.example.tmooc.entity.BaseViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyGridAdapter extends BaseAdapter {
	private Context mContext;

	public String[] img_text = { "Java", "算法", "C/C++","数据库", "HTML5", "IOS", "Android",
			"Web",  "更多", };
	public int[] imgs = { R.drawable.java,  R.drawable.alg,
			 R.drawable.c,  R.drawable.database,
			 R.drawable.html5,  R.drawable.ios,
			 R.drawable.android,  R.drawable.web,  R.drawable.go };

	public MyGridAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return img_text.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.grid_item, parent, false);
		}
		TextView tv = BaseViewHolder.get(convertView, R.id.tv_item);
		ImageView iv = BaseViewHolder.get(convertView, R.id.iv_item);
		iv.setBackgroundResource(imgs[position]);

		tv.setText(img_text[position]);
		return convertView;
	}

}
