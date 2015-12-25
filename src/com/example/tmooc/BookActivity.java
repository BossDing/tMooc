package com.example.tmooc;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.List;
import com.example.tmooc.adapter.BookAdapter;
import com.example.tmooc.entity.Book;
import com.example.tmooc.utils.HttpUtils;
import com.example.tmooc.utils.MyConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class BookActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book);
		String path=MyConstants.URL+"findAllBook";
		new MyTask().execute(path);
	}

	public class MyTask extends AsyncTask<String,Void,List<Book>> {
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
		protected  List<Book> doInBackground(String... params) {
	
			String strJson = HttpUtils.sendPostMethod(params[0],"utf-8");
			Log.i("aaa", strJson);
			Type type=new TypeToken<List<Book>>(){}.getType();
			Gson gson=new Gson();
			List<Book> book = gson.fromJson(strJson, type);
			return book;
		}

		/**
		 * 执行完操作后
		 */

		@Override
		protected void onPostExecute( final List<Book> book) {
			// TODO Auto-generated method stub
			super.onPostExecute(book);
			ListView lv_book = (ListView) findViewById(R.id.lv_book);
			BookAdapter adapter=new BookAdapter(getApplication(), R.layout.book_item, book);
			lv_book.setAdapter(adapter);
			lv_book.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent intent=new Intent(BookActivity.this,BookInfoActivity.class);
					intent.putExtra("book", book.get(position));
					startActivity(intent);
					
				}
			});

		}
	}
}
