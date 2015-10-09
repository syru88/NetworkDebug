package com.marcel.networkdebug.network;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;


/**
 * Created by marcel on 9.10.2015.
 */
public interface TestService
{
	@GET("/posts")
	Call<List<Post>> listPosts();
}
