package com.example.tmooc.adapter;

import java.util.List;

import com.example.tmooc.ProblemInfoActivity;
import com.example.tmooc.R;
import com.example.tmooc.entity.Problem;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProblemAdapter extends ArrayAdapter<Problem> {
	private int resourceId;
	private Context context;

	public ProblemAdapter(Context context, int resource, List<Problem> objects) {
		super(context, resource, objects);
		this.resourceId=resource;
		this.context=context;
	}


	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final Problem problem=getItem(position);
		View view=LayoutInflater.from(context).inflate(resourceId, null);
		TextView problem_content = (TextView) view.findViewById(R.id.problem_content);
		ImageButton bt_answer = (ImageButton) view.findViewById(R.id.bt_answer);
		problem_content.setText(problem.getProblem_content());
		bt_answer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context,ProblemInfoActivity.class);
				intent.putExtra("problem", problem);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
				context.startActivity(intent);
			}
		});
		return view;
	}
}
