package com.marcel.networkdebug;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.marcel.networkdebug.fragment.RepositoryListFragment;
import com.marcel.networkdebug.helper.RetrofitProxy;
import com.marcel.networkdebug.helper.RetrofitProxyNoSSL;
import com.marcel.networkdebug.helper.RetrofitStetho;


public class MainActivity extends AppCompatActivity
{

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		View mainView = findViewById(android.R.id.content);

		switch(item.getItemId())
		{
			case R.id.retrofit_stetho:
				RetrofitStetho.loadData(getFragment(), mainView);
				break;
			case R.id.retrofit_proxy:
				RetrofitProxy.loadData(getFragment(), mainView);
				break;
			case R.id.retrofit_proxy_no_ssl:
				RetrofitProxyNoSSL.loadData(getFragment(), mainView);
				break;
		}

		return super.onOptionsItemSelected(item);
	}


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		if(savedInstanceState == null)
		{
			FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
			trx.add(R.id.fragment_container, RepositoryListFragment.newInstance(), RepositoryListFragment.TAG);
			trx.commit();
		}
	}


	private RepositoryListFragment getFragment()
	{
		return (RepositoryListFragment) getSupportFragmentManager().findFragmentByTag(RepositoryListFragment.TAG);
	}

}
