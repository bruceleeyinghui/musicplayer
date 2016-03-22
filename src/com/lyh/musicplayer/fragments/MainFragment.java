package com.lyh.musicplayer.fragments;


import com.lyh.musicplayer.R;

import android.view.View;

public class MainFragment extends BaseFragment {

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.content_fragment, null);
		return view;
	}
	
	/**
	 *  带完善主页的数据初始化
	 */
	public void initData() {
		super.initData();
	}

}
