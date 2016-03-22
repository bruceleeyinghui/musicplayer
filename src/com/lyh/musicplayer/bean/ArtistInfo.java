/**
 * Copyright (c) www.longdw.com
 */
package com.lyh.musicplayer.bean;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class ArtistInfo implements Parcelable {
	
	public static final String KEY_ARTIST_NAME = "artist_name";
	public static final String KEY_NUMBER_OF_TRACKS = "number_of_tracks";
	
	public String artist_name;
	public int number_of_tracks;
	
	
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		Bundle bundle = new Bundle();
		bundle.putString(KEY_ARTIST_NAME, artist_name);
		bundle.putInt(KEY_NUMBER_OF_TRACKS, number_of_tracks);
		dest.writeBundle(bundle);
	}
	
	public static final Parcelable.Creator<ArtistInfo> CREATOR = new Parcelable.Creator<ArtistInfo>() {

		public ArtistInfo createFromParcel(Parcel source) {
			Bundle bundle = source.readBundle();
			ArtistInfo info = new ArtistInfo();
			info.artist_name = bundle.getString(KEY_ARTIST_NAME);
			info.number_of_tracks = bundle.getInt(KEY_NUMBER_OF_TRACKS);
			return info;
		}

		public ArtistInfo[] newArray(int size) {
			return new ArtistInfo[size];
		}
	}; 

}
