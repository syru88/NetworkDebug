package com.marcel.networkdebug;

import android.app.Application;

import com.facebook.stetho.Stetho;


/**
 * Created by marcel on 8.10.2015.
 */
public class NetworkDebugApplication extends Application
{

	@Override
	public void onCreate()
	{
		super.onCreate();
		if (BuildConfig.DEBUG) {
			Stetho.initializeWithDefaults(this);
		}
	}
}
