package com.example.johnny.tundr;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Johnny on 2017-02-17.
 */

public class Playlist {

    private String mName;
    private double mLongitude;
    private double mLatitude;
    private List<String> mSongs;

    public Playlist(){
        //dummy data
        mName = "test";
        mLatitude = 56;
        mLongitude = 29;
        mSongs= Arrays.asList("song1", "song2", "song3");;

    }

    public Playlist(String n, double lat, double lon, List<String> s){
        mName = n;
        mLatitude = lat;
        mLongitude = lon;
        mSongs= s;
    }







    public void setmName(String mName) {
        this.mName = mName;
    }
    public String getmName() {
        return mName;
    }

    public double getmLatitude() {
        return mLatitude;
    }
    public void setmLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getmLongitude() {
        return mLongitude;
    }
    public void setmLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public List<String> getmSongs() {
        return mSongs;
    }
    public void setmSongs(List<String> mSongs) {
        this.mSongs = mSongs;
    }
}
