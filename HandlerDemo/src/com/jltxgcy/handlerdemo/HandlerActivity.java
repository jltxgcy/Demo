package com.jltxgcy.handlerdemo;



import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.os.Process;

public class HandlerActivity extends Activity {
	private static final String TAG="jltxgcy";
	private final String HANDLER_KEY = "jltxgcy"  ;
	private Handler lab1Handler1 ;
	private Handler lab1Handler2 ;
	private Handler lab2Handler  ;
	private Handler lab3Handler;
	private Handler lab4Handler;
	private Handler lab5_1Handler = new Handler();  
	private Handler lab5_2Handler;
	private Looper mLooper;
	private String result;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
    	lab1Handler2 = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				Toast.makeText(HandlerActivity.this, "[Main Thread]Handler2 Get the message: "+msg.getData().getString(HANDLER_KEY), 5000).show()  ;
			}
			
		} ;
		lab1Handler1 = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				result=msg.getData().getString(HANDLER_KEY);
				Toast.makeText(HandlerActivity.this, "[Main Thread]Handler1 Get the message: "+msg.getData().getString(HANDLER_KEY), 5000).show()  ;
			}
			
		} ;
		


		//Handler Lab1
		findViewById(R.id.buttonHandlerLab1).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				new Thread(new Runnable(){

					@Override
					public void run() {
						
						lab1Handler1.sendMessage(defineNewMessage("Lab1")) ;
						//try to use mainHanlder2 to send meesage and receive . 
						//mainHandler2.sendMessage(defineNewMessage("Lab1")) ;
						
					}
					
				}).start();
				
			}
			
		}) ;
		
		findViewById(R.id.buttonHandlerLab2).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(new Runnable(){
					
					@Override
					public void run() {
						lab2Handler = new Handler(Looper.getMainLooper())
						{

							@Override
							public void handleMessage(Message msg) {
								// TODO Auto-generated method stub
								super.handleMessage(msg);
								Log.d(TAG, "Get the message: "+msg.getData().getString(HANDLER_KEY)+" by Child Thread Handler")  ;
							}
							
						};
						lab2Handler.sendMessage(defineNewMessage("Lab2")) ;
						lab3Handler.sendMessage(defineNewMessage("Lab3")) ;
						
						
					}
					
				}).start() ;
				
			}
		});
		
		findViewById(R.id.buttonHandlerLab3).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(new Runnable(){
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Looper.prepare();
						lab3Handler = new Handler(){

							@Override
							public void handleMessage(Message msg) {
								// TODO Auto-generated method stub
								super.handleMessage(msg);
								Log.d(TAG, "Get the message from "+msg.getData().getString(HANDLER_KEY)+" by Child Thread Handler")  ;
							}
						} ;						
						Looper.loop();
					}
					
				}).start() ;
				
			}
		});
		

		findViewById(R.id.buttonHandlerLab4_1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HandlerThread thread = new HandlerThread("ServiceStartArguments",
			            Process.THREAD_PRIORITY_BACKGROUND);
			    thread.start();
			    
			    // Get the HandlerThread's Looper and use it for our Handler 
			    mLooper = thread.getLooper();
			    lab4Handler = new Handler(mLooper){
			    	@Override
					public void handleMessage(Message msg) {
						// TODO Auto-generated method stub
						super.handleMessage(msg);
			        	Log.d(TAG, "Start message "+msg.getData().getString(HANDLER_KEY)+" by Child Thread Handler")  ;
			        	try {
							Thread.sleep(5*1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
			        	Log.d(TAG, "Finish message "+msg.getData().getString(HANDLER_KEY)+" by Child Thread Handler");
					}
			    };
				lab4Handler.sendMessage(defineNewMessage("Lab4.1")) ;
				lab4Handler.sendMessage(defineNewMessage("Lab4.2")) ;
				
			}
		});
		
		findViewById(R.id.buttonHandlerLab4_2).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				lab4Handler.removeMessages(1);
				
			}
		});
		
		findViewById(R.id.buttonHandlerLab5_1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				lab5_1Handler.postDelayed(new MyRunnable(), 2000);
				Log.d(TAG, ""+Thread.currentThread().getId());
			}
		});
		
		findViewById(R.id.buttonHandlerLab5_2).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HandlerThread handlerThread = new HandlerThread("myHandlerThread");  
		        handlerThread.start();  
		        lab5_2Handler = new Handler(handlerThread.getLooper());  
		        lab5_2Handler.post(new MyRunnable());  
		        Log.d(TAG,""+Thread.currentThread().getId());
			}
		});
    }
    
    private class MyRunnable implements Runnable {  
        public void run() {  
        	Log.d(TAG, "Runnable---The Thread is running");  
            Log.d(TAG, ""+Thread.currentThread().getId()); 
        }  
    }  
       
    private Message defineNewMessage(String messageContent)
	{
		Message returnMsg = new Message() ;
		Bundle data = new Bundle() ;
		data.putString(HANDLER_KEY, messageContent)  ;
		returnMsg.setData(data)  ;
		returnMsg.what=1;
		return returnMsg ;
	}
}
