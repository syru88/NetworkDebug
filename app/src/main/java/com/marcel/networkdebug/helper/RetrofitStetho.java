package com.marcel.networkdebug.helper;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.marcel.networkdebug.NetworkDebugConfig;
import com.marcel.networkdebug.interfaces.Refreshable;
import com.marcel.networkdebug.network.GitHubService;
import com.marcel.networkdebug.network.Repo;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by marcel on 8.10.2015.
 */
public class RetrofitStetho
{
	public static void loadData(final Refreshable fragment, final View view)
	{
		fragment.loadingData();

		Callback<List<Repo>> callback = new Callback<List<Repo>>()
		{
			@Override
			public void onResponse(Response<List<Repo>> response, Retrofit retrofit)
			{
				fragment.onDataRefreshed(response.body());
			}


			@Override
			public void onFailure(Throwable t)
			{
				t.printStackTrace();
				fragment.onDataRefreshed(null);
				Snackbar.make(view, "Network Error", Snackbar.LENGTH_LONG).show();
			}
		};

		OkHttpClient client = new OkHttpClient();
		client.networkInterceptors().add(new StethoInterceptor());

		Retrofit retrofit = new Retrofit.Builder().baseUrl(NetworkDebugConfig.API_URL).addConverterFactory
				(GsonConverterFactory.create()).client(client).build();
		GitHubService service = retrofit.create(GitHubService.class);
		Call<List<Repo>> repos = service.listRepos("square");
		repos.enqueue(callback);

	}
}
