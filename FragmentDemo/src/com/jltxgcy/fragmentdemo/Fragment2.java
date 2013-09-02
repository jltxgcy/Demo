package com.jltxgcy.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment2 extends Fragment {
	private ViewGroup mViewGroup;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mViewGroup = container;
		testFragment2();
		return inflater.inflate(R.layout.fragment2, container, false);
	}
	
	public void testFragment2(){
		MainActivity mainActivity = (MainActivity)getActivity();
		mainActivity.testMainActivity();
		Fragment1 fragment1 = (Fragment1) getFragmentManager().findFragmentById(R.id.fragment1);
		fragment1.testFragment1();
	}
	
	public void testFindviewById(){
		mViewGroup.findViewById(R.id.tv_fragment2);
		getActivity().findViewById(R.id.tv_display);
		getActivity().findViewById(R.id.tv_fragment1);
	}

}
