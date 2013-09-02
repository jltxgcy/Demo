package com.jltxgcy.bindservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.jltxgcy.bindservice.LocalService.LocalBinder;

public class BindActivity extends Activity {

	LocalService mService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);
        findViewById(R.id.buttonUseService).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mBound) {
		            // Call a method from the LocalService.
		            // However, if this call were something that might hang, then this request should
		            // occur in a separate thread to avoid slowing down the activity performance.
		            int num = mService.getRandomNumber();
		            Toast.makeText(BindActivity.this, "number: " + num, Toast.LENGTH_SHORT).show();
		        }
				
			}
		});
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, LocalService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }
    

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            LocalBinder binder = (LocalBinder) service;
            mService = binder.getService();
            mBound = true;
            Log.d("jltxgcy", "onServiceConnected");
        }

        //Called when the connection with the service disconnects unexpectedly
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
            Log.d("jltxgcy", "onServiceDisconnected");
        }
    };
}
