package com.lyh.musicplayer.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 专辑
 * @author Administrator
 *
 */
public class MyAlbumDao {
	private final String  TABLE_ALBLUM="album_info";
	private Context context;
	public MyAlbumDao(Context context){
		this.context = context;
	}
	public int getDataCount(){
		SQLiteDatabase db = DataBaseHelper.getInstance(context);
		//统计数据库中table_music表中有多少条数据
		String sql = "select count(*) from "+ TABLE_ALBLUM;
		Cursor cursor = db.rawQuery(sql, null);
		int count = -1;
		if(cursor.moveToFirst()){
			count = cursor.getInt(0);
		}
		cursor.close();
		return count;
		
	}
}
