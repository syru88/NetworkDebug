package com.marcel.networkdebug.utility;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by marcel on 8.10.2015.
 */
public class Preferences
{
	public static final String FILE_NAME = "test";

	private static final String KEY_TEST = "TEST";

	public static void setTestString(Context context, String value)
	{
		SharedPreferences.Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
		editor.putString(KEY_TEST, value);
		editor.commit();
	}

	public static String getTestString(Context context)
	{
		SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		return preferences.getString(KEY_TEST, "empty");
	}
}
