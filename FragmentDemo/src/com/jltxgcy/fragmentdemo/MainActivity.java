package com.jltxgcy.fragmentdemo;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MainActivity extends FragmentActivity {
	public static final String TAG = "Fragment2";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Fragment2 fragment2 = new Fragment2();  
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment2).commit();
    }
    
    public void testMainActivity(){
    	Fragment1 fragment1 = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.fragment1);
		fragment1.testFragment1();
    }
    
    public void testFindviewById(){
    	findViewById(R.id.tv_display);
    	findViewById(R.id.tv_fragment2);
    }
}
