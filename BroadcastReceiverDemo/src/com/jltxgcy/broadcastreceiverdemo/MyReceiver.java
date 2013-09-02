package com.jltxgcy.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		Log.d("jltxgcy", "onReceiver");
		Intent intent = new Intent(context, HelloIntentService.class);
		context.startService(intent);
	}

}
