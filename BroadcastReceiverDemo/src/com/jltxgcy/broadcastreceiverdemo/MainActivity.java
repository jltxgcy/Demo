package com.jltxgcy.broadcastreceiverdemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
	public static final String ACTION="com.jltxgcy.receiver";
	public BroadcastReceiver mBroadcastReceiver= new MyReceiver();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.btn_register).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/*IntentFilter filter = new IntentFilter();
				filter.addAction(ACTION);
				filter.setPriority(Integer.MAX_VALUE);
				registerReceiver(mBroadcastReceiver, filter);	*/
			}
		});
        
        findViewById(R.id.btn_unregister).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				unregisterReceiver(mBroadcastReceiver);
				
			}
		});
        
        findViewById(R.id.btn_send).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sendBroadcast(new Intent(ACTION));
			}
		});
    }

	@Override
	protected void onStop() {
//		unregisterReceiver(mBroadcastReceiver);
		super.onStop();
	}
    
    

}
