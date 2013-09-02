package com.jltxgcy.iocdemo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.jltxgcy.framework.BaseActivity;
import com.jltxgcy.framework.annotation.InjectResource;
import com.jltxgcy.framework.annotation.InjectView;

public class MainActivity extends BaseActivity {
	@InjectResource(id=R.string.app_name)
    String appNameString;
	@InjectView(id=R.id.tv_display)
	TextView tView;
	@InjectView(id=R.id.btn_imagecache)
	Button btnImagecache;
	@InjectView(id=R.id.btn_http)
	Button btnHttp;

	@Override
	protected void onAfterOnCreate(Bundle savedInstanceState)
	{
		tView.setText(appNameString);
		super.onAfterOnCreate(savedInstanceState);
		
	}
	
	@Override
	protected void onAfterSetContentView()
	{
		// TODO Auto-generated method stub
		super.onAfterSetContentView();
		OnClickListener onClickListener = new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				switch (v.getId())
				{
				case R.id.btn_imagecache:
					tView.setText("Í¼Æ¬»º´æÄ£¿é");
					break;
				case R.id.btn_http:
					tView.setText("httpÄ£¿é");
					break;
				default:
					break;
				}
			}
		};
		btnImagecache.setOnClickListener(onClickListener);
		btnHttp.setOnClickListener(onClickListener);
	}

}
