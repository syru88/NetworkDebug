package com.marcel.networkdebug.interfaces;

import java.util.List;


/**
 * Created by marcel on 8.10.2015.
 */
public interface Refreshable
{
	void loadingData();
	void onDataRefreshed(List data);
}
