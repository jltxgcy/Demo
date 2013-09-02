package com.jltxgcy.framework;





import android.app.Activity;

import com.jltxgcy.framework.annotation.Injector;
import com.jltxgcy.framework.layoutloader.ILayoutLoader;
import com.jltxgcy.framework.layoutloader.LayoutLoader;

public class FrameActivity extends Activity
{
	
	private ILayoutLoader mLayoutLoader;
	private Injector mInjector;

	public ILayoutLoader getLayoutLoader()
	{
		if (mLayoutLoader == null)
		{
			mLayoutLoader = LayoutLoader.getInstance(this);
		}
		return mLayoutLoader;
	}

	public void setLayoutLoader(ILayoutLoader layoutLoader)
	{
		this.mLayoutLoader = layoutLoader;
	}

	public Injector getInjector()
	{
		if (mInjector == null)
		{
			mInjector = Injector.getInstance();
		}
		return mInjector;
	}

	public void setInjector(Injector injector)
	{
		this.mInjector = injector;
	}
	

}
