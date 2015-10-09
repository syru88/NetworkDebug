package com.marcel.networkdebug.helper;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.marcel.networkdebug.interfaces.Refreshable;
import com.marcel.networkdebug.network.Repo;

import java.util.Arrays;


/**
 * Created by marcel on 9.10.2015.
 */
public class VolleyProxy
{
	public static final int TIMEOUT_IN_MILLIS = 30 * 1000;
	private static final String URL = "https://api.github.com/users/square/repos";


	public static void loadData(Context context, final Refreshable fragment, final View view)
	{
		fragment.loadingData();

		RequestQueue requestQueue = Volley.newRequestQueue(context, new ProxyUrlStack());

		GsonRequest<Repo[]> repoRequest = new GsonRequest<Repo[]>(Request.Method.GET, URL, Repo[].class, new Response
				.Listener<Repo[]>()
		{
			@Override
			public void onResponse(Repo[] response)
			{
				fragment.onDataRefreshed(Arrays.asList(response));
			}
		}, new Response.ErrorListener()
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
		repoRequest.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_IN_MILLIS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		requestQueue.add(repoRequest);

	}
}
