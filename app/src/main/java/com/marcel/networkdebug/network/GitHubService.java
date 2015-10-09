package com.marcel.networkdebug.network;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


/**
 * Created by marcel on 8.10.2015.
 */
public interface GitHubService {
	@GET("/users/{user}/repos")
	Call<List<Repo>> listRepos(@Path("user") String user);
}