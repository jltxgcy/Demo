package com.jltxgcy.framework;
import android.os.Bundle;

public abstract class BaseActivity extends FrameActivity
{

	private String moduleName = "";
	private String layoutName = "";//main

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		initActivity();
		onAfterOnCreate(savedInstanceState);
	}


	private void initActivity()
	{
		getModuleName();
		getLayoutName();
		initInjector();
		loadDefautLayout();
	}

	protected void onAfterOnCreate(Bundle savedInstanceState)
	{
		
	}

	private void initInjector()
	{
		getInjector().injectResource(this);
		getInjector().inject(this);
	}

	private void loadDefautLayout()
	{
		try
		{
			int layoutResID = getLayoutLoader().getLayoutID(
					layoutName);
			setContentView(layoutResID);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setContentView(int layoutResID)
	{
		super.setContentView(layoutResID);
		getInjector().injectView(this);
		onAfterSetContentView();
	}


	protected void onAfterSetContentView()
	{

	}

	public String getModuleName()
	{
		String moduleName = this.moduleName;
		if (moduleName == null || moduleName.equalsIgnoreCase(""))
		{
			moduleName = getClass().getName().substring(0,
					getClass().getName().length() - 8);
			String arrays[] = moduleName.split("\\.");
			this.moduleName = moduleName = arrays[arrays.length - 1]
					.toLowerCase();
		}
		return moduleName;
	}

	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}

	public String getLayoutName()
	{
		String layoutName = this.layoutName;
		if (layoutName == null || layoutName.equalsIgnoreCase(""))
		{
			this.layoutName = this.moduleName;
		}
		return layoutName;
	}

	protected void setLayoutName(String layoutName)
	{
		this.layoutName = layoutName;
	}

}