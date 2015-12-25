package com.example.tmooc;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.tmooc.adapter.MyGridAdapter;
import com.example.tmooc.entity.MyGridView;
import com.example.tmooc.entity.Video;


public class HomeActivity extends Activity {
	private String[] INFO={"java�����̳�","������㷨ѧϰ�̳�","C/C++�����̳�",
			"���ݿ����Ž̳�","HTML5�̳�","IOS�����̳�",
			"AndroidӦ�ÿ����̳̿���","Web�����̳�","����������̳�"};
	private MyGridView gridview;
	private MyGridAdapter adapter;
	
	public String Title[]= new String[20] ;
	public String ownerName[]= new String[20] ;
	public String PlayURL[]= new String[20] ;
	public String Time[]= new String[20] ;
	public String imageID[]=new String[20];
	
	private final  String URL_TUDOU = "http://api.tudou.com/v6/video/search?app_key=c69c212d9193b996&format=json&kw=";

	
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				
				String result = (String) msg.obj;
				Log.i("TAG", result);
				
				
				try {
					JSONObject root = new JSONObject(result);
					JSONArray array = root.getJSONArray("results");
					for (int i = 0; i < 20; i++) {
						JSONObject info = array.getJSONObject(i);
						Title[i] = info.getString("title");
						ownerName[i] = info.getString("ownerName");
						PlayURL[i] = info.getString("playUrl");
						Time[i] = info.getString("playTimes");
						imageID[i]=info.getString("bigPicUrl");
						
						
					}
					for (int j = 0; j < Title.length; j++) {
						System.out.println("jjjjjjjjjj"+Title[j]);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				SkipIntent(Title,ownerName,PlayURL,Time,imageID);
				
				
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}

		private void SkipIntent(String[] title, String[] ownerName,
				String[] playURL, String[] time, String[] imageID) {
			Intent i = new Intent(HomeActivity.this,InfoViewActivity.class);
	    	
	    	Bundle b = new Bundle();
	    	b.putStringArray("Title", title);
	    	b.putStringArray("ownerName", ownerName);
	    	b.putStringArray("PlayURL", playURL);
	    	b.putStringArray("Time", time);
	    	b.putStringArray("imageID", imageID);
	    	
	    	i.putExtras(b);
	    	

	    	startActivity(i);
			
		}
		
	}; 
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        initView();
    }


    private void initView() {
    	gridview=(MyGridView) findViewById(R.id.gridview);
    	adapter = new MyGridAdapter(this);
    	
    	gridview.setAdapter(adapter);
    	
    	gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				switch (arg2) {
				case 0:
					Log.i("TAG1", "11111111111111111111");
					getHttpData(arg2);
					break;
				case 1:
					getHttpData(arg2);
					
					break;
				case 2:
					getHttpData(arg2);
					
					break;
				case 3:
					getHttpData(arg2);
					
					break;
				case 4:
					getHttpData(arg2);
					
					break;
				case 5:
					getHttpData(arg2);
					
					break;
				case 6:
					getHttpData(arg2);
					
					break;
				case 7:
					getHttpData(arg2);
					
					break;
				case 8:
					getHttpData(arg2);
					
					break;
				case 9:
					getHttpData(arg2);
					
					break;
                    
				default:
					break;
				}
			}
		});
		
		
	}
    
    protected void getHttpData(final int info) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				HttpURLConnection connection = null;
				
				try {
					Log.i("TAG2", "22222222222222222222");
					Log.i("TAG3", INFO[info]);
					
					URL url = new URL(URL_TUDOU+INFO[info]);
					connection =  (HttpURLConnection) url.openConnection();
					
					Log.i("TAG4", URL_TUDOU+INFO[info]);
					
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					InputStream is = connection.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					StringBuilder sb = new StringBuilder();
					String line;
					while ((line=br.readLine())!=null) {
						sb.append(line);
						
					}
					
					Log.i("TAG5", "oooooooo");
					
					Message message = new Message();
					message.what = 0;
					message.obj=sb.toString();
					
					handler.sendMessage(message);
					
					
					
				} catch (Exception e) {
					System.out.println("�����ˡ���������������������");
					e.printStackTrace();
				}finally{
					if (connection!=null) {
						connection.disconnect();
					}
				}
				
			}
		}).start();
	}


	
    
    
    
    


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
