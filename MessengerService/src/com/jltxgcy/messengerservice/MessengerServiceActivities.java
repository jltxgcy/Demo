package com.jltxgcy.messengerservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MessengerServiceActivities extends Activity{
 
    

        Messenger mServiceMessage = null;
        boolean mIsBound;
        TextView mCallbackText;
        

        class IncomingHandler extends Handler {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MessengerService.MSG_SET_VALUE:
                        mCallbackText.setText("Received from service: " + msg.arg1);
                        break;
                    default:
                        super.handleMessage(msg);
                }
            }
        }
        

        final Messenger mMessenger = new Messenger(new IncomingHandler());
        
        private ServiceConnection mConnection = new ServiceConnection() {
            public void onServiceConnected(ComponentName className,
                    IBinder service) {
            	Log.d("jltxgcy", "onServiceConnected");
                mServiceMessage = new Messenger(service);
                mCallbackText.setText("Attached.");
                try {
                    Message msg = Message.obtain(null,
                            MessengerService.MSG_REGISTER_CLIENT);
                    msg.replyTo = mMessenger;
                    
                    mServiceMessage.send(msg);
                    msg = Message.obtain(null,
                            MessengerService.MSG_SET_VALUE, this.hashCode(), 0);
                    mServiceMessage.send(msg);
                } catch (RemoteException e) {
                }
                
            }

            public void onServiceDisconnected(ComponentName className) {
                mServiceMessage = null;
                Log.d("jltxgcy", "onServiceDisconnected");
            }
        };
        
        void doBindService() {
     
            bindService(new Intent(MessengerServiceActivities.this, 
                    MessengerService.class), mConnection, Context.BIND_AUTO_CREATE);
            mIsBound = true;
        }
        
        void doUnbindService() {
            if (mIsBound) {
                if (mServiceMessage != null) {
                    try {
                        Message msg = Message.obtain(null,
                                MessengerService.MSG_UNREGISTER_CLIENT);
                        msg.replyTo = mMessenger;
                        mServiceMessage.send(msg);
                    } catch (RemoteException e) {
                    }
                }
                
                unbindService(mConnection);
                mIsBound = false;
            }
        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.messenger_service_binding);

            Button button = (Button)findViewById(R.id.bind);
            button.setOnClickListener(mBindListener);
            button = (Button)findViewById(R.id.unbind);
            button.setOnClickListener(mUnbindListener);
            
            mCallbackText = (TextView)findViewById(R.id.callback);
          
        }

        private OnClickListener mBindListener = new OnClickListener() {
            public void onClick(View v) {
                doBindService();
            }
        };

        private OnClickListener mUnbindListener = new OnClickListener() {
            public void onClick(View v) {
                doUnbindService();
            }
        };
    
}
