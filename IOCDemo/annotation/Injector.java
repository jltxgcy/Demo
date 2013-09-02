package com.youku.framework.annotation;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.res.Resources;

public class Injector
{
	private static Injector instance;

	private Injector()
	{

	}

	public static Injector getInstance()
	{
		if (instance == null)
		{
			instance = new Injector();
		}
		return instance;
	}

	public void inJectAll(Activity activity)
	{
		// TODO Auto-generated method stub
		Field[] fields = activity.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0)
		{
			for (Field field : fields)
			{
				if (field.isAnnotationPresent(InjectView.class))
				{
					injectView(activity, field);
				} else if (field.isAnnotationPresent(InjectResource.class))
				{
					injectResource(activity, field);
				} else if (field.isAnnotationPresent(InjectObject.class))
				{
					inject(activity, field);
				}
			}
		}
	}

	private void inject(Activity activity, Field field)
	{
		// TODO Auto-generated method stub
		try
		{
			field.setAccessible(true);
			field.set(activity, field.getType().newInstance());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void injectView(Activity activity, Field field)
	{
		// TODO Auto-generated method stub
		if (field.isAnnotationPresent(InjectView.class))
		{
			InjectView viewInject = field.getAnnotation(InjectView.class);
			int viewId = viewInject.id();
			try
			{
				field.setAccessible(true);
				field.set(activity, activity.findViewById(viewId));
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private void injectResource(Activity activity, Field field)
	{
		// TODO Auto-generated method stub
		if (field.isAnnotationPresent(InjectResource.class))
		{
			InjectResource resourceJect = field
					.getAnnotation(InjectResource.class);
			int resourceID = resourceJect.id();
			try
			{
				field.setAccessible(true);
				Resources resources = activity.getResources();
				String type = resources.getResourceTypeName(resourceID);
				if (type.equalsIgnoreCase("string"))
				{
					field.set(activity,
							activity.getResources().getString(resourceID));
				} else if (type.equalsIgnoreCase("drawable"))
				{
					field.set(activity,
							activity.getResources().getDrawable(resourceID));
				} else if (type.equalsIgnoreCase("layout"))
				{
					field.set(activity,
							activity.getResources().getLayout(resourceID));
				} else if (type.equalsIgnoreCase("array"))
				{
					if (field.getType().equals(int[].class))
					{
						field.set(activity, activity.getResources()
								.getIntArray(resourceID));
					} else if (field.getType().equals(String[].class))
					{
						field.set(activity, activity.getResources()
								.getStringArray(resourceID));
					} else
					{
						field.set(activity, activity.getResources()
								.getStringArray(resourceID));
					}

				} else if (type.equalsIgnoreCase("color"))
				{
					if (field.getType().equals(Integer.TYPE))
					{
						field.set(activity,
								activity.getResources().getColor(resourceID));
					} else
					{
						field.set(activity, activity.getResources()
								.getColorStateList(resourceID));
					}

				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public void inject(Activity activity)
	{
		// TODO Auto-generated method stub
		Field[] fields = activity.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0)
		{
			for (Field field : fields)
			{
				if (field.isAnnotationPresent(InjectObject.class))
				{
					inject(activity, field);
				}
			}
		}
	}

	public void injectView(Activity activity)
	{
		// TODO Auto-generated method stub
		Field[] fields = activity.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0)
		{
			for (Field field : fields)
			{
				if (field.isAnnotationPresent(InjectView.class))
				{
					injectView(activity, field);
				}
			}
		}
	}

	public void injectResource(Activity activity)
	{
		// TODO Auto-generated method stub
		Field[] fields = activity.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0)
		{
			for (Field field : fields)
			{
				if (field.isAnnotationPresent(InjectResource.class))
				{
					injectResource(activity, field);
				}
			}
		}
	}

}