package org.ourunix.android.sharepreferencewrite;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SharePreferenceWriteActivity extends Activity implements OnClickListener{
	private SharedPreferences prference;
	private String PREF_NAME = "PREF_NAME";
	private String KEY = "TestValue";
	private EditText mEditText;
	private Button mButton;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mEditText = (EditText) findViewById(R.id.writeTV);
        mButton = (Button) findViewById(R.id.submit);
        mButton.setOnClickListener(this);
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		prference = getSharedPreferences(PREF_NAME, MODE_WORLD_READABLE );
        prference.edit().putString(KEY, mEditText.getText().toString()).commit();
	}
}