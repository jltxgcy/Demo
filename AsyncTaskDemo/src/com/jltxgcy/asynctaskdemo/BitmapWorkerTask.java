package com.jltxgcy.asynctaskdemo;

import android.graphics.Bitmap;
import android.util.Log;


public class BitmapWorkerTask extends ImageAsyncTask<Object, Void, Bitmap> {

    @Override
    protected Bitmap doInBackground(Object... params) {
    	
        String data = (String)params[0];
       Log.d("jltxgcy", data);
        // Log.d("jltxgcy", Thread.currentThread().getName());//记住不要看线程名称，看传递过来的url
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        return null;
    }

    /**
     * Once the image is processed, associates it to the imageView
     */
    @Override
    protected void onPostExecute(Bitmap bitmap) {
       
    }
}
