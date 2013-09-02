package com.jltxgcy.lifecycledemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static final String TAG="jltxgcy";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(TAG, "MainActivity onCreate");
        
        findViewById(R.id.startPartActivity).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, PartActivity.class);
				startActivity(intent);
				
			}
		});
        
        findViewById(R.id.startDialog).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final CharSequence[] items = {"Red", "Green", "Blue"};

				//µ÷ÓÃdialog
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("Pick a color");
				builder.setItems(items, new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog, int item) {
				        Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
				    }
				});
				AlertDialog alert = builder.create();
				alert.show()  ;
				
			}
		});
    }
    
    @Override
   	protected void onStart() {
   		// TODO Auto-generated method stub
   		Log.d(TAG, "MainActivity onStart");
   		super.onStart();
   	}

   	@Override
   	protected void onResume() {
   		Log.d(TAG, "MainActivity onResume");
   		super.onResume();
   	}

   	@Override
   	protected void onPause() {
   		Log.d(TAG, "MainActivity onPause");
   		super.onPause();
   	}

   	@Override
   	protected void onStop() {
   		Log.d(TAG, "MainActivity onStop");
   		super.onStop();
   	}

   	@Override
   	protected void onDestroy() {
   		Log.d(TAG, "MainActivity onDestroy");
   		super.onDestroy();
   	}

   	@Override
   	protected void onRestart() {
   		Log.d(TAG, "MainActivity onRestart");
   		super.onRestart();
   	}
   	
   	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d("gcyjltx", "onSaveInstanceState");
	}

}
