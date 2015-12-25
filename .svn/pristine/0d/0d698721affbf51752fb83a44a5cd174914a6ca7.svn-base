package com.example.tmooc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.example.tmooc.BookActivity.MyTask;
import com.example.tmooc.adapter.BookAdapter;
import com.example.tmooc.adapter.CollectBookAdapter;
import com.example.tmooc.entity.Book;
import com.example.tmooc.utils.HttpUtils;
import com.example.tmooc.utils.MyConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

public class CourseCollectActivity extends BaseActivity {
	private ListView list ;
	private PopupMenu popupMenu ;
	private Button button ;
	private Menu menu ;
	private ProgressDialog dialog ;
	CollectBookAdapter adapter ;
	boolean FLAG = false ;
	List<Book> books;
	Book book ;
	int user_id ;
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(CourseCollectActivity.this, "取消关注成功", Toast.LENGTH_SHORT).show() ;
				break;
			case 2:
				books.add(book) ;
				adapter.notifyDataSetChanged() ;
				Toast.makeText(CourseCollectActivity.this, "取消关注成功", Toast.LENGTH_SHORT).show() ; 
			default:
				break;
			}
			
		}
	} ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_course_collect) ;
		initPopupMenu() ;
		dialog = new ProgressDialog(this);
		dialog.setTitle("提示") ;
		dialog.setMessage("正在加载。。。") ;
		SharedPreferences sharedPreferences = getSharedPreferences("userId", MODE_PRIVATE) ;
		user_id = sharedPreferences.getInt("userId_Message", 0) ;
		String[] maps ={MyConstants.URL+"findBookByCollect",user_id+""};
		new MyTask().execute(maps);
		
		
		button = (Button)findViewById(R.id.button1) ;
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popupMenu.show() ;
			}
		}) ;
		list = (ListView)findViewById(R.id.lv_course) ;		
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				
				if (FLAG) {	
					book = books.remove(position) ;
					adapter.notifyDataSetChanged() ;
					
					new Thread(new Runnable() {
						public void run() {
							Map<String, Object> map = new HashMap<String, Object>() ;
							map.put("user_id", user_id) ;
							map.put("book_id",book.getBook_id()) ;
							Message msg = new Message() ;
							String strJson2 =HttpUtils.sendPostMethod(MyConstants.URL+"deleteCollect",map,"utf-8");
							if ("1".equals(strJson2)) {
								msg.what = 1 ;
							}else if ("0".equals(strJson2)){
								msg.what = 2 ;
								/*books.add(book) ;
								adapter.notifyDataSetChanged() ;*/
							}
							handler.sendMessage(msg) ;
						}
					}).start() ;
				}else {
					Intent intent=new Intent(CourseCollectActivity.this,BookInfoActivity.class);
					intent.putExtra("book", books.get(position));
					startActivity(intent);
				}
			}
		});
		
		
		//popupMenu的点击事件
		popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				switch (item.getItemId()) {
				case R.id.delete:
					Toast.makeText(CourseCollectActivity.this, "点击文章进行取消操作,点击取消用于取消此操作", Toast.LENGTH_SHORT).show() ;
					FLAG = true ;
					break;
				case R.id.cancel :
					Toast.makeText(CourseCollectActivity.this, "已取消", Toast.LENGTH_SHORT).show() ;
					FLAG = false ;
				default:
					break;
				}
				
				return false;
			}
		}) ;
	}
	private void initPopupMenu() {
		// TODO Auto-generated method stub
		popupMenu = new PopupMenu(this,findViewById(R.id.button1)) ;
		menu = popupMenu.getMenu() ;
		MenuInflater inflater = getMenuInflater() ;
		inflater.inflate(R.menu.popupmenu, menu) ;
	}

	public class MyTask extends AsyncTask<String,Void,List<Book>> {
		/**
		 * 执行之前调用此方法
		 */
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.show() ;
		}

		/**
		 * 后台执行操作
		 * @return 
		 */

		@Override
		protected  List<Book> doInBackground(String... params) {
			Map<String, Object> map = new HashMap<String, Object>() ;
			map.put("user_id", params[1]) ;
			String strJson = HttpUtils.sendPostMethod(params[0],map,"utf-8");
			Log.i("aaa", strJson);
			Type type=new TypeToken<List<Book>>(){}.getType();
			Gson gson=new Gson();
			books = gson.fromJson(strJson, type);
			return books;
		}

		/**
		 * 执行完操作后
		 */

		@Override
		protected void onPostExecute( final List<Book> books) {
			// TODO Auto-generated method stub
			super.onPostExecute(books);
			adapter=new CollectBookAdapter(getApplication(), R.layout.collect_book_item, books);
			list.setAdapter(adapter);
			dialog.dismiss() ;
			//listView的点击事件
		}
	}
}
