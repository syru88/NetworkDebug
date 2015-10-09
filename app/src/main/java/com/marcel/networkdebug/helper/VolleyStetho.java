package com.marcel.networkdebug.helper;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.marcel.networkdebug.interfaces.Refreshable;
import com.marcel.networkdebug.network.Repo;
import com.squareup.okhttp.OkHttpClient;

import java.util.Arrays;


/**
 * Created by marcel on 9.10.2015.
 */
public class VolleyStetho
{

	private static final String URL = "https://api.github.com/users/square/repos";


	public static void loadData(Context context, final Refreshable fragment, final View view)
	{
		fragment.loadingData();

		OkHttpClient client = new OkHttpClient();
		client.networkInterceptors().add(new StethoInterceptor());
		RequestQueue requestQueue = Volley.newRequestQueue(context, new OkHttpStack(client));

		GsonRequest<Repo[]> repoRequest = new GsonRequest<Repo[]>(Request.Method.GET, URL, Repo[].class, new
				Listener<Repo[]>()
		{
			@Override
			public void onResponse(Repo[] response)
			{
				fragment.onDataRefreshed(Arrays.asList(response));
			}
		}, new ErrorListener()
		{
			@Override
			public void onErrorResponse(VolleyError error)
			{
				error.printStackTrace();
				fragment.onDataRefreshed(null);
				Snackbar.make(view, "Network Error", Snackbar.LENGTH_LONG).show();
			}
		});
		repoRequest.setShouldCache(false);
		requestQueue.add(repoRequest);

	}
}
