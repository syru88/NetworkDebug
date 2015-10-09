package com.marcel.networkdebug.utility;

import android.util.Log;

import com.marcel.networkdebug.NetworkDebugConfig;


public class Logcat
{
	public static final String TAG = "NetworkDebug";

	private static final boolean SHOW_CODE_LOCATION = true;
	private static final boolean SHOW_CODE_LOCATION_THREAD = false;
	private static final boolean SHOW_CODE_LOCATION_LINE = false;


	public static void d(String msg, Object... args)
	{
		if(NetworkDebugConfig.LOGS) Log.d(TAG, getCodeLocation().toString() + formatMessage(msg, args));
	}


	public static void e(String msg, Object... args)
	{
		if(NetworkDebugConfig.LOGS) Log.e(TAG, getCodeLocation().toString() + formatMessage(msg, args));
	}


	public static void e(Throwable tr, String msg, Object... args)
	{
		if(NetworkDebugConfig.LOGS) Log.e(TAG, getCodeLocation().toString() + formatMessage(msg, args), tr);
	}


	public static void i(String msg, Object... args)
	{
		if(NetworkDebugConfig.LOGS) Log.i(TAG, getCodeLocation().toString() + formatMessage(msg, args));
	}


	public static void v(String msg, Object... args)
	{
		if(NetworkDebugConfig.LOGS) Log.v(TAG, getCodeLocation().toString() + formatMessage(msg, args));
	}


	public static void w(String msg, Object... args)
	{
		if(NetworkDebugConfig.LOGS) Log.w(TAG, getCodeLocation().toString() + formatMessage(msg, args));
	}


	public static void wtf(String msg, Object... args)
	{
		if(NetworkDebugConfig.LOGS) Log.wtf(TAG, getCodeLocation().toString() + formatMessage(msg, args));
	}


	private static String formatMessage(String msg, Object... args)
	{
		return args.length == 0 ? msg : String.format(msg, args);
	}


	private static CodeLocation getCodeLocation()
	{
		return getCodeLocation(3);
	}


	private static CodeLocation getCodeLocation(int depth)
	{
		StackTraceElement[] stackTrace = new Throwable().getStackTrace();
		StackTraceElement[] filteredStackTrace = new StackTraceElement[stackTrace.length - depth];
		System.arraycopy(stackTrace, depth, filteredStackTrace, 0, filteredStackTrace.length);
		return new CodeLocation(filteredStackTrace);
	}


	private static class CodeLocation
	{
		public final String mThread;
		public final String mClassName;
		public final String mMethod;
		public final int mLineNumber;
		public StackTraceElement[] mStackTrace;


		CodeLocation(StackTraceElement[] stackTrace)
		{
			mStackTrace = stackTrace;
			StackTraceElement root = stackTrace[0];
			mThread = Thread.currentThread().getName();
			String className = root.getClassName();
			mClassName = className.substring(className.lastIndexOf('.') + 1);
			mMethod = root.getMethodName();
			mLineNumber = root.getLineNumber();
		}


		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			if(SHOW_CODE_LOCATION)
			{
				builder.append('[');
				if(SHOW_CODE_LOCATION_THREAD)
				{
					builder.append(mThread);
					builder.append('.');
				}
				builder.append(mClassName);
				builder.append('.');
				builder.append(mMethod);
				if(SHOW_CODE_LOCATION_LINE)
				{
					builder.append(':');
					builder.append(mLineNumber);
				}
				builder.append("] ");
			}
			return builder.toString();
		}
	}
}