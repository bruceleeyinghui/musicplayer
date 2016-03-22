package com.lyh.musicplayer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 创建数据库
 * 一般操作数据库是不是采用单例模式
 * @author Tony
 *
 */
public class DataBaseHelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "music_tz";
	public static final int DB_VERSION = 1;
	
	private static SQLiteDatabase mDb;
	private static DataBaseHelper mHelper;
	
	private static final String TABLE_ALBUM = "album_info";//专辑
	private static final String TABLE_ARTIST = "artist_info";//歌手
	private static final String TABLE_MUSIC = "music_info";//音乐
	private static final String TABLE_FOLDER = "folder_info";//文件夹
	private static final String TABLE_FAVORITE = "favorite_info";//最爱
	
	public static DataBaseHelper getHelper(Context context){
		if (mHelper == null){
			mHelper = new DataBaseHelper(context);
		}
		return mHelper;
	}
	
	public static SQLiteDatabase getInstance(Context context){
		if (mDb == null){
			mDb = getHelper(context).getWritableDatabase();
		}
		return mDb;
	}

	private DataBaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	public DataBaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//建表   5张表
		db.execSQL("create table "
				+ TABLE_MUSIC
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " songid integer, albumid integer, duration integer, musicname varchar(10), "
				+ "artist char, data char, folder char, musicnamekey char, artistkey char, favorite integer)");
		db.execSQL("create table "
				+ TABLE_ALBUM
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "album_name char, album_id integer, number_of_songs integer, album_art char)");
		db.execSQL("create table "
				+ TABLE_ARTIST
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, artist_name char, number_of_tracks integer)");
		db.execSQL("create table "
				+ TABLE_FOLDER
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, folder_name char, folder_path char)");
		db.execSQL("create table "
				+ TABLE_FAVORITE
				+ " (_id integer,"
				+ " songid integer, albumid integer, duration integer, musicname varchar(10), "
				+ "artist char, data char, folder char, musicnamekey char, artistkey char, favorite integer)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion > oldVersion){
			//删除对应的五张表
			db.execSQL("drop table if exist "+TABLE_MUSIC);
			db.execSQL("drop table if exist "+TABLE_ARTIST);
			db.execSQL("drop table if exist "+TABLE_FAVORITE);
			db.execSQL("drop table if exist "+TABLE_FOLDER);
			db.execSQL("drop table if exist "+TABLE_ALBUM);
			onCreate(db);
		}
	}

}
