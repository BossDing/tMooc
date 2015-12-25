package com.example.tmooc;

import java.lang.reflect.Type;
import java.util.List;
import com.example.tmooc.adapter.ProblemAdapter;
import com.example.tmooc.entity.Problem;
import com.example.tmooc.utils.HttpUtils;
import com.example.tmooc.utils.MyConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ProblemActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.problem);
		String path=MyConstants.URL+"findAllProblem";
		new MyTask().execute(path);
		Button add_problem = (Button) findViewById(R.id.add_problem);
		add_problem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),"暂未实现",Toast.LENGTH_SHORT).show();				
			}
		});
	}

	public class MyTask extends AsyncTask<String,Void,List<Problem>> {
		/**
		 * 执行之前调用此方法
		 */
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		/**
		 * 后台执行操作
		 * @return 
		 */

		@Override
		protected  List<Problem> doInBackground(String... params) {
	
			String strJson = HttpUtils.sendPostMethod(params[0],"utf-8");
			Log.i("aaa", strJson);
			Type type=new TypeToken<List<Problem>>(){}.getType();
			Gson gson=new Gson();
			List<Problem> problem = gson.fromJson(strJson, type);
			return problem;
		}

		/**
		 * 执行完操作后
		 */

		@Override
		protected void onPostExecute( List<Problem> problem) {
			// TODO Auto-generated method stub
			super.onPostExecute(problem);
			ListView lv_problem = (ListView) findViewById(R.id.lv_problem);
			ProblemAdapter adapter=new ProblemAdapter(getApplication(), R.layout.problem_item, problem);
			lv_problem.setAdapter(adapter);

		}
	}
}
