package com.marcel.networkdebug.helper;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.marcel.networkdebug.NetworkDebugConfig;
import com.marcel.networkdebug.interfaces.Refreshable;
import com.marcel.networkdebug.network.Post;
import com.marcel.networkdebug.network.Repo;
import com.marcel.networkdebug.network.TestService;
import com.squareup.okhttp.OkHttpClient;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by marcel on 9.10.2015.
 */
public class RetrofitProxyNoSSL
{
	private static final String API_URL = "http://jsonplaceholder.typicode.com";


	public static void loadData(final Refreshable fragment, final View view)
	{

		// create proxy
		final OkHttpClient client = new OkHttpClient();
		if(NetworkDebugConfig.IS_PROXY_ENABLED)
		{
			NetworkUtility.isProxyReachable(new NetworkUtility.ProxyReachable()
			{
				@Override
				public void isReachable(boolean isReachable)
				{
					if(isReachable)
					{
						InetSocketAddress socketAddress = new InetSocketAddress(NetworkDebugConfig.PROXY_IP,
								NetworkDebugConfig.PROXY_PORT);
						Proxy proxy = new Proxy(Proxy.Type.HTTP, socketAddress);
						client.setProxy(proxy);
						Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory
								(GsonConverterFactory.create()).client(client).build();
						makeRequest(fragment, view, retrofit);
					}
					else
					{
						Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory
								(GsonConverterFactory.create()).client(client).build();
						makeRequest(fragment, view, retrofit);
					}
				}
			});


		}
		else
		{
			Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory
					.create()).client(client).build();
			makeRequest(fragment, view, retrofit);
		}

	}


	private static void makeRequest(final Refreshable fragment, final View view, Retrofit retrofit)
	{
		fragment.loadingData();

		Callback<List<Post>> callback = new Callback<List<Post>>()
		{
			@Override
			public void onResponse(Response<List<Post>> response, Retrofit retrofit)
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

		TestService service = retrofit.create(TestService.class);
		Call<List<Post>> repos = service.listPosts();
		repos.enqueue(callback);
	}
}
