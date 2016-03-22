package com.lyh.musicplayer.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;









import com.lyh.musicplayer.bean.MusicInfo;
import com.lyh.musicplayer.contants.IContants;
import com.lyh.musicplayer.db.MyAlbumDao;
import com.lyh.musicplayer.db.MyArtistDao;
import com.lyh.musicplayer.db.MyFavoriteDao;
import com.lyh.musicplayer.db.MyFolderDao;
import com.lyh.musicplayer.db.MyMusicDao;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class MusicUtils implements IContants{
	private static MyMusicDao mMusicDao;
	private static MyAlbumDao mMyAlbumDao;
	private static MyArtistDao mMyArtistDao;
	private static MyFavoriteDao mMyFavoriteDao;
	private static MyFolderDao mMyFolderDao;
	public static String[] proj_music={
			MediaStore.Audio.Media._ID,MediaStore.Audio.Media.TITLE,
			MediaStore.Audio.Media.DATA,MediaStore.Audio.Media.ALBUM_ID,
			//歌手   歌手的id 系统数据库 保存音频信息安歌手id进行排序
			MediaStore.Audio.Media.ARTIST,MediaStore.Audio.Media.ARTIST_ID,
			MediaStore.Audio.Media.DURATION};
	
	/**
	 * 
	 * @param context
	 * @param form   是主页的那个模块需要查找音乐文件
	 * @return
	 */
	public static List<MusicInfo> queryMusic(Context context,int form){
		//查看系统的contentProvider源码得到的uri
		if(mMusicDao == null){
			mMusicDao = new MyMusicDao(context);
		}
		
		Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		ContentResolver cr = context.getContentResolver();
		switch(form){
		case START_FROM_LOCAL:
			if(mMusicDao.hasData()){//再一次判断本地是否有数据
				return mMusicDao.getMusicInfo();
			}else{//本地没有数据 只能通过跨进程访问 系统数据库（ContentProvider）
				List<MusicInfo> list = getMusicList(cr.query(uri, 
						proj_music, null, null,MediaStore.Audio.Media.ARTIST_ID));
				//查出的数据 立即保存到数据
				mMusicDao.saveMusicInfo(list);
			}
			
			break;
		case START_FROM_ALBUM:
			break;
		case START_FROM_ARTIST:
			break;
		case START_FROM_FAVORITE:
			break;
		case START_FROM_FOLDER:
			break;
		}
		
		return null;
	}
	/**
	 * 将系统查出来的数据 封装到集合中
	 * @param query
	 * @return
	 */

	private static List<MusicInfo> getMusicList(Cursor cursor) {
		if(cursor==null){
			return null;
		}
		List<MusicInfo> list = new ArrayList<MusicInfo>();
		while(cursor.moveToNext()){
			MusicInfo music = new  MusicInfo();
			music.songId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
			music.albumId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
			music.duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
			music.musicName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
			music.artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
			String filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
			music.data = filePath;//  /mnt/muic/test.mp3
			String folderPath = filePath.substring(0,filePath.lastIndexOf(File.separator));
			music.folder = folderPath;
			list.add(music);
		}
		cursor.close();
		return list;
	}
}
