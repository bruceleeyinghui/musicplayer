package com.lyh.musicplayer.fragments;


import com.lyh.musicplayer.R;

import android.view.View;

public class MenuFragment extends BaseFragment {

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.menu_fragment, null);
		return view;
	}

}
