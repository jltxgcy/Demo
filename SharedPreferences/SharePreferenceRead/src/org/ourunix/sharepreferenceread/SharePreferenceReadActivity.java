package org.ourunix.sharepreferenceread;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;

public class SharePreferenceReadActivity extends Activity {
    /** Called when the activity is first created. */
	private SharedPreferences prference;
	private String NAME = "PREF_NAME";
	private String KEY = "TestValue";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        setContentView(tv);
        
        String tmp;
        Context c = null;
        
        try {
			c = this.createPackageContext("org.ourunix.android.sharepreferencewrite", CONTEXT_IGNORE_SECURITY);

		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
        if (c != null)
        prference = c.getSharedPreferences(NAME, 0);
        tmp = prference.getString(KEY, "nothing");
        tv.setText(tmp);
    }
}