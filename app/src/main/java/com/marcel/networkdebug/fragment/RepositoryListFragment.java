package com.marcel.networkdebug.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;

import com.marcel.networkdebug.adapter.BasicAdapter;
import com.marcel.networkdebug.interfaces.Refreshable;

import java.util.List;


public class RepositoryListFragment extends ListFragment implements Refreshable
{

	public static final String TAG = RepositoryListFragment.class.getSimpleName();
	private BasicAdapter mAdapter;

	public static RepositoryListFragment newInstance()
	{
		RepositoryListFragment fragment = new RepositoryListFragment();
		return fragment;
	}


	public RepositoryListFragment()
	{
	}


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		mAdapter = new BasicAdapter(getContext(), null);
	}


	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		setEmptyText("No Data");
		setListAdapter(mAdapter);
	}


	@Override
	public void loadingData()
	{
		setListShown(false);
	}


	@Override
	public void onDataRefreshed(List data)
	{
		mAdapter.setData(data);
		setListShown(true);
	}

}
