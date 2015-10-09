package com.marcel.networkdebug.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


/**
 * Created by marcel on 8.10.2015.
 */
public class BasicAdapter extends BaseAdapter
{

	public List mData;
	public Context mContext;


	public BasicAdapter(Context context, List data)
	{
		mContext = context;
		mData = data;
	}


	@Override
	public int getCount()
	{
		if(mData == null)
		{
			return 0;
		}
		else
		{
			return mData.size();
		}
	}


	@Override
	public Object getItem(int position)
	{
		return mData.get(position);
	}


	@Override
	public long getItemId(int position)
	{
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view;
		TextView textView;

		if(convertView == null)
		{
			view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
		}
		else
		{
			view = convertView;
		}

		textView = (TextView) view;
		textView.setText(mData.get(position).toString());

		return view;
	}

	public void setData(List data)
	{
		mData = data;
		notifyDataSetChanged();
	}

}
