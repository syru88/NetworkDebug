package com.marcel.networkdebug.helper;

import com.android.volley.toolbox.HurlStack;
import com.marcel.networkdebug.NetworkDebugConfig;
import com.marcel.networkdebug.utility.Logcat;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;


/**
 * Created by marcel on 9.10.2015.
 */
public class ProxyUrlStack extends HurlStack
{
	public static final int TIMEOUT_IN_MILLIS = 2000;


	@Override
	protected HttpURLConnection createConnection(final URL url) throws IOException
	{

		HttpURLConnection connection;
		if(NetworkDebugConfig.IS_PROXY_ENABLED && isReachable())
		{
			InetSocketAddress socketAddress = new InetSocketAddress(NetworkDebugConfig.PROXY_IP, NetworkDebugConfig
					.PROXY_PORT);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, socketAddress);
			connection = (HttpURLConnection) url.openConnection(proxy);
		}
		else
		{
			Logcat.e("without proxy");
			connection = (HttpURLConnection) url.openConnection();
		}

		return connection;
	}


	private boolean isReachable()
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
}
