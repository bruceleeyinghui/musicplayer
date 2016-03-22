package com.lyh.musicplayer.activity;

import com.lyh.musicplayer.R;
import com.lyh.musicplayer.contants.IContants;
import com.lyh.musicplayer.db.MyMusicDao;
import com.lyh.musicplayer.utils.MusicUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


public class SplashScreen extends Activity implements IContants{
	private MyMusicDao mMusicDao;
	private Handler mHandler;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		
		/**
		 * 准备主页的数�? ：当数据准备完成之后进入主页
		 * 
		 * 访问本地数据�? 文件  网络 都是耗时操作  ：非UI线程
		 */
		mMusicDao = new MyMusicDao(this);
		mHandler = new Handler(){
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				/**
				 * 进入主页：添加动�?
				 */
				startActivity(new Intent(SplashScreen.this, MainActivity.class));
				overridePendingTransition(R.anim.tween_enter, R.anim.tween_exit);
				finish();
			}
		};
		
		getData();
		
	}

	/**
	 * �?查从本地的数据库查看是否有音乐数据：扫描 耗时  在非UI线程
	 */
	private void getData() {
		new Thread(new Runnable() {
			public void run() {
				if (mMusicDao.hasData()){//本地有数�?
					//如果本地有数据的话，等待3秒跳转到主页
					mHandler.sendMessageDelayed(mHandler.obtainMessage(), 3000);
				}else{//本地数据库没有数据，那么就到手机系统数据库中取数�?  ---ContentProvider
					MusicUtils.queryMusic(SplashScreen.this, START_FROM_LOCAL);
					mHandler.sendEmptyMessage(1);
				}
			}
		}).start();
	}
}









