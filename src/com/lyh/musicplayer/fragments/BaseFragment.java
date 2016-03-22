package com.lyh.musicplayer.fragments;


import com.lyh.musicplayer.activity.MainActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public abstract class BaseFragment extends Fragment {
	public MainActivity mActivity;
	
	//fragment创建成功
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = (MainActivity) getActivity();
	}
	
	//填充Fragment的内容视图
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return initView();
	}
	
	public void initData(){
		
	}

	public abstract View initView() ;
	
	

}
