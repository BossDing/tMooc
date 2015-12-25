package com.example.tmooc.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tmooc.R;
import com.example.tmooc.entity.Book;

public class BookAdapter extends ArrayAdapter<Book> {
	
	private int resourceId;
	private Context context;
	public BookAdapter(Context context, int textViewResourceId,
			List<Book> objects) {
		super(context, textViewResourceId, objects);
		this.resourceId=textViewResourceId;
		this.context=context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Book book=getItem(position);
		View view=LayoutInflater.from(context).inflate(resourceId, null);
		TextView book_title = (TextView) view.findViewById(R.id.tv_book_title);
		TextView book_author = (TextView) view.findViewById(R.id.tv_book_author);
		book_title.setText(book.getBook_name());
		book_author.setText(book.getBook_author());
		return view;
	}

}
