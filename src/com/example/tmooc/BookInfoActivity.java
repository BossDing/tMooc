package com.example.tmooc;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.tmooc.entity.Book;
import com.example.tmooc.utils.HttpUtils;
import com.example.tmooc.utils.MyConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BookInfoActivity extends Activity {

	private Book book;

	public Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				bt_collect.setVisibility(View.GONE);
				break;
			case 0:
				bt_collect.setVisibility(View.VISIBLE);
				break;

			default:
				break;
			}
		}
	};


	private Button bt_collect;	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_info);
		Intent intent=getIntent();
	    book = (Book) intent.getExtras().get("book");
	    TextView tv_title = (TextView) findViewById(R.id.tv_title);
	    TextView tv_content = (TextView) findViewById(R.id.tv_content);
	    Button bt_back = (Button) findViewById(R.id.bt_back);
	    bt_collect = (Button) findViewById(R.id.bt_collect);
	    tv_title.setText(book.getBook_name());
	    tv_content.setText(book.getBook_content());
	    new Thread(new Runnable() {

			@Override
			public void run() {
				SharedPreferences idPreferences = getSharedPreferences("userId", MODE_PRIVATE) ;
				final int user_id = idPreferences.getInt("userId_Message", 0) ;
				Map<String, Object> map1 = new HashMap<String, Object>() ;
				map1.put("user_id", user_id);
				String strJson =HttpUtils.sendPostMethod(MyConstants.URL+"findBookByCollect",map1,"utf-8");
				Type type=new TypeToken<List<Book>>(){}.getType();
				Gson gson=new Gson();
				List<Book> books = gson.fromJson(strJson, type);
				for (Book book2 : books) {
					if(book2.getBook_id()==book.getBook_id()){
						Message message = new Message();
						message.what = 1;
						handler.sendMessage(message);
					}else{
						Message message = new Message();
						message.what = 0;
						handler.sendMessage(message);
					}
				}
				
				
			}
		}).start();
	    bt_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	    bt_collect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {

					@Override
					public void run() {

						Map<String, Object> mapss = new HashMap<String, Object>() ;
						SharedPreferences idPreferences = getSharedPreferences("userId", MODE_PRIVATE) ;
						final int user_id = idPreferences.getInt("userId_Message", 0) ;
						mapss.put("user_id", user_id);
						mapss.put("book_id", book.getBook_id());
						HttpUtils.sendPostMethod(MyConstants.URL+"collectBook",mapss,"utf-8");
						
					}
				}).start();
				
				bt_collect.setVisibility(View.GONE);		
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book_info, menu);
		return true;
	}

}
