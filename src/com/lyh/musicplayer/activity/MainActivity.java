package com.lyh.musicplayer.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.lyh.musicplayer.R;
import com.lyh.musicplayer.fragments.MainFragment;
import com.lyh.musicplayer.fragments.MenuFragment;


public class MainActivity extends SlidingFragmentActivity {
	private SlidingMenu slidingMenu;
	private final String MAIN_FRAGMENT = "mainFragment";
	private final String MENU_FRAGMENT = "menuFragment";
	
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);//主页
        //slidingMenu
        setBehindContentView(R.layout.right_menu);//右边栏
        
        slidingMenu = getSlidingMenu();
        slidingMenu.setTouchModeAbove(SlidingMenu.FOCUSABLES_TOUCH_MODE);//全屏响应
        slidingMenu.setMode(slidingMenu.RIGHT);//设置侧边在右边
        slidingMenu.setBehindOffset(100);//设置侧栏的边距  要适配
        
        
        //初始化界面:往主页和侧边栏放Fragment
        initFragment();
    }


    /**
     * 往主页和侧边栏放Fragment
     */
	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.fl_maincontent, new MainFragment(), MAIN_FRAGMENT);
		transaction.replace(R.id.fl_RightMenu, new MenuFragment(), MENU_FRAGMENT);
		
		transaction.commit();
		
	}
}



