package com.braincol.aidl.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.braincol.aidl.service.RemoteWebPage;

public class ClientActivity extends Activity implements OnClickListener {
	private final static String TAG="jltxgcy";
	Button btn_bind ;
	String actionName = "com.braincol.aidl.remote.webpage";
	RemoteWebPage remoteWebPage=null;
	String allInfo = null;
	boolean isBinded=false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		btn_bind = (Button) findViewById(R.id.btn_bind);
		btn_bind.setOnClickListener(this);

	}
	@Override
	protected void onPause(){
		super.onPause();
		if(isBinded){
			Log.d(TAG,"unbind");
			unbindService(connection);	
		}
	}
	private class MyServiceConnection implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i(TAG, "建立连接...");
			remoteWebPage = RemoteWebPage.Stub.asInterface(service);
			try {
				Log.d(TAG, remoteWebPage.getCurrentPageUrl());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i(TAG, "onServiceDisconnected...");
		}

	}
	MyServiceConnection connection = new MyServiceConnection();

	@Override
	public void onClick(View v) {
		if(v==this.btn_bind){
			if(!isBinded){
				Intent intent  = new Intent(actionName);
				bindService(intent, connection, Context.BIND_AUTO_CREATE);	
				isBinded= true;
		}
	}

}
}