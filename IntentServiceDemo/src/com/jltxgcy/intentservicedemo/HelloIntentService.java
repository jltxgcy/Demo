package com.jltxgcy.intentservicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class HelloIntentService extends IntentService {
	public static final String TAG="jltxgcy";

	public HelloIntentService() {
		super("HelloIntentService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		long endTime = System.currentTimeMillis() + 5*1000;
	      while (System.currentTimeMillis() < endTime) {
	          synchronized (this) {
	              try {
	                  wait(endTime - System.currentTimeMillis());
	              } catch (Exception e) {
	              }
	          }
	      }
	      Log.d(TAG, "onHandleIntent"+Thread.currentThread().getId());
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}
	
	

}
