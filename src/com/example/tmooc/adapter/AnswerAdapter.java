package com.example.tmooc.adapter;

import java.util.List;

import com.example.tmooc.R;
import com.example.tmooc.entity.Answer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AnswerAdapter extends ArrayAdapter<Answer> {

	private int resourceId;
	private Context context;
	public AnswerAdapter(Context context, int resource, List<Answer> objects) {
		super(context, resource, objects);
		this.resourceId=resource;
		this.context=context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Answer answer=getItem(position);
		View view=LayoutInflater.from(context).inflate(resourceId, null);
		TextView tv_answer = (TextView)view.findViewById(R.id.tv_answer);
		tv_answer.setText(answer.getAnswer_content());
		return view;
	}
}
