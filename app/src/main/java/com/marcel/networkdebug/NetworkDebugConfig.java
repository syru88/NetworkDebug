package com.marcel.networkdebug;

import com.marcel.networkdebug.BuildConfig;


/**
 * Created by marcel on 8.10.2015.
 */
public class NetworkDebugConfig
{
	public static final boolean LOGS = BuildConfig.LOGS;
	public static final boolean IS_PROXY_ENABLED = BuildConfig.IS_PROXY_ENABLED;
	public static final String PROXY_IP = BuildConfig.PROXY_IP;
	public static final int PROXY_PORT = 8888;
	public static final String API_URL = "https://api.github.com";
}
