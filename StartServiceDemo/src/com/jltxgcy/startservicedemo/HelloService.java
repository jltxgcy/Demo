package com.jltxgcy.startservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import android.os.Process;

public class HelloService extends Service {

	  private Looper mServiceLooper;
	  private ServiceHandler mServiceHandler;
	  public static final String TAG="jltxgcy";


	  private final class ServiceHandler extends Handler {
	      public ServiceHandler(Looper looper) {
	          super(looper);
	      }
	      @Override
	      public void handleMessage(Message msg) {
	    	  Log.d(TAG, "handleMessage"+Thread.currentThread().getId());
	          long endTime = System.currentTimeMillis() + 5*1000;
	          while (System.currentTimeMillis() < endTime) {
	              synchronized (this) {
	                  try {
	                      wait(endTime - System.currentTimeMillis());
	                  } catch (Exception e) {
	                  }
	              }
	          }

	          stopSelf(msg.arg1);
	      }
	  }

	  @Override
	  public void onCreate() {

	    HandlerThread thread = new HandlerThread("ServiceStartArguments",
	            Process.THREAD_PRIORITY_BACKGROUND);
	    thread.start();

	    mServiceLooper = thread.getLooper();
	    mServiceHandler = new ServiceHandler(mServiceLooper);
	  }

	  @Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
	      Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
	      Message msg = mServiceHandler.obtainMessage();
	      msg.arg1 = startId;
	      Log.d(TAG, "onStartCommand"+Thread.currentThread().getId());
	      mServiceHandler.sendMessage(msg);
	      return START_STICKY;
	  }

	  @Override
	  public IBinder onBind(Intent intent) {
	      return null;
	  }
	  
	  @Override
	  public void onDestroy() {
	    Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show(); 
	  }
	
	

}
