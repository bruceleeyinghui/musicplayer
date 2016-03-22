/**
 * Copyright (c) www.longdw.com
 */
package com.lyh.musicplayer.bean;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class FolderInfo implements Parcelable {
	
	public static String KEY_FOLDER_NAME = "folder_name";
	public static String KEY_FOLDER_PATH = "folder_path";
	
	public String folder_name;
	public String folder_path;

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		Bundle bundle = new Bundle();
		bundle.putString(KEY_FOLDER_NAME, folder_name);
		bundle.putString(KEY_FOLDER_PATH, folder_path);
		dest.writeBundle(bundle);
	}
	
	// 用来创建自定义的Parcelable的对�??
	public static Parcelable.Creator<FolderInfo> CREATOR = new Parcelable.Creator<FolderInfo>() {

		public FolderInfo createFromParcel(Parcel source) {
			FolderInfo info = new FolderInfo();
			Bundle bundle = source.readBundle();
			info.folder_name = bundle.getString(KEY_FOLDER_NAME);
			info.folder_path = bundle.getString(KEY_FOLDER_PATH);
			return info;
		}

		public FolderInfo[] newArray(int size) {
			return new FolderInfo[size];
		}
	};

}
