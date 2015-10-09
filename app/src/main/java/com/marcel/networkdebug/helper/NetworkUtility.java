package com.marcel.networkdebug.helper;

import android.os.AsyncTask;

import com.marcel.networkdebug.NetworkDebugConfig;
import com.marcel.networkdebug.utility.Logcat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


/**
 * Created by marcel on 9.10.2015.
 */
public class NetworkUtility
{
	public static final int TIMEOUT_IN_MILLIS = 2000;


	public interface ProxyReachable
	{
		void isReachable(boolean isReachable);
	}


	public static void isProxyReachable(final ProxyReachable listener)
	{
		Logcat.e("Proxy IP: " + NetworkDebugConfig.PROXY_IP);

		AsyncTask asyncTask = new AsyncTask()
		{
			@Override
			protected Boolean doInBackground(Object[] params)
			{
				SocketAddress socketAddress = new InetSocketAddress(NetworkDebugConfig.PROXY_IP, NetworkDebugConfig
						.PROXY_PORT);
				Socket socket = new Socket();
				try
				{
					socket.connect(socketAddress, TIMEOUT_IN_MILLIS);
					return true;
				}
				catch(IOException e)
				{
					e.printStackTrace();
					return false;
				}
			}


			@Override
			protected void onPostExecute(Object result)
			{
				if((Boolean) result)
				{
					listener.isReachable(true);
				}
				else
				{
					Logcat.e("Unreachable");
					listener.isReachable(false);
				}
			}
		};
		asyncTask.execute();
	}
}
