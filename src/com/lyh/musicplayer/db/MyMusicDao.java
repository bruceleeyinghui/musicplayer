package com.lyh.musicplayer.db;

import java.util.ArrayList;
import java.util.List;

import com.lyh.musicplayer.bean.MusicInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
/**
 * 我的音乐
 * @author Administrator
 */
public class MyMusicDao {
	private Context context;
	private static final String TABLE_MUSIC = "music_info";
	public MyMusicDao(Context context){
		this.context = context;
	}
	//保存歌曲
	public void saveMusicInfo(List<MusicInfo> list){
		SQLiteDatabase db = DataBaseHelper.getInstance(context);
		for(MusicInfo music : list){
			ContentValues values = new ContentValues();
			values.put("songid", music.songId);
			values.put("albumid", music.albumId);
			values.put("duration", music.duration);
			values.put("musicname", music.musicName);
			values.put("artist", music.artist);
			values.put("data",music.data);
			values.put("folder", music.folder);
			values.put("musicnamekey", music.musicNameKey);
			values.put("artistkey", music.artistKey);
			values.put("favorite", music.favorite);
			
			db.insert(TABLE_MUSIC, null,values);
		}
	}
	//获取歌曲
	public List<MusicInfo> getMusicInfo(){
		SQLiteDatabase db = DataBaseHelper.getInstance(context);
		String sql = "select * from "+TABLE_MUSIC;
		return parseCursor(db.rawQuery(sql, null));
	}
	/**
	 * 解析游标中的数据
	 * @param rawQuery
	 * @return
	 */
	private List<MusicInfo> parseCursor(Cursor cursor) {
		List<MusicInfo> list = new ArrayList<MusicInfo>();
		while(cursor.moveToNext()){
			MusicInfo music = new MusicInfo();
			music._id = cursor.getInt(cursor.getColumnIndex("_id"));
			music.songId = cursor.getInt(cursor.getColumnIndex("songid"));
			music.albumId = cursor.getInt(cursor.getColumnIndex("albumid"));
			music.duration = cursor.getInt(cursor.getColumnIndex("duration"));
			music.musicName = cursor.getString(cursor.getColumnIndex("musicname"));
			music.artist = cursor.getString(cursor.getColumnIndex("artist"));
			music.data = cursor.getString(cursor.getColumnIndex("data"));
			music.folder = cursor.getString(cursor.getColumnIndex("folder"));
			music.musicNameKey = cursor.getString(cursor.getColumnIndex("musicnamekey"));
			music.artistKey = cursor.getString(cursor.getColumnIndex("artistkey"));
			music.favorite = cursor.getInt(cursor.getColumnIndex("favorite"));
			list.add(music);
		}
		cursor.close();
		return list;
	}
	public boolean hasData(){
		SQLiteDatabase db = DataBaseHelper.getInstance(context);
		//统计数据库中table_music表中有多少条数据
		String sql = "select count(*) from "+ TABLE_MUSIC;
		Cursor cursor = db.rawQuery(sql, null);
		boolean has = false;
		if(cursor.moveToFirst()){
			int count = cursor.getInt(0);
			if(count > 0){
				has =true;
			}
		}
		cursor.close();
		return has;
	}
	public int getDataCount(){
		SQLiteDatabase db = DataBaseHelper.getInstance(context);
		//统计数据库中table_music表中有多少条数据
		String sql = "select count(*) from "+ TABLE_MUSIC;
		Cursor cursor = db.rawQuery(sql, null);
		int count = -1;
		if(cursor.moveToFirst()){
			count = cursor.getInt(0);
		}
		cursor.close();
		return count;
		
	}
	
	
}



