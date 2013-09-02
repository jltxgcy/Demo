package com.jltxgcy.framework.layoutloader;

import android.content.pm.PackageManager.NameNotFoundException;

public interface ILayoutLoader
{
	public int getLayoutID(String resIDName) throws ClassNotFoundException,
			IllegalArgumentException, IllegalAccessException,
			NameNotFoundException;

}