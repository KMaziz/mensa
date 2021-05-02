package com.example.mensas.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName="mensen")
public class Mensa implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int mid;

    public String mName;
    public String mAddr;
    public String mPlz;
    public String mLat;
    public String mLon;

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isFavorite;

    public Mensa(int mid, String mName, String mAddr, String mPlz, String mLat, String mLon) {
        this.mid=mid;
        this.mName=mName;
        this.mAddr=mAddr;
        this.mPlz=mPlz;
        this.mLat=mLat;
        this.mLon=mLon;

    }
    public Mensa(){

    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddr() {
        return mAddr;
    }

    public void setmAddr(String mAddr) {
        this.mAddr = mAddr;
    }

    public String getmPlz() {
        return mPlz;
    }

    public void setmPlz(String mPlz) {
        this.mPlz = mPlz;
    }

    public String getmLat() {
        return mLat;
    }

    public void setmLat(String mLat) {
        this.mLat = mLat;
    }

    public String getmLon() {
        return mLon;
    }

    public void setmLon(String mLon) {
        this.mLon = mLon;
    }

    @Override
    public String toString() {
        return "Mensa{" +
                "mid=" + mid +
                ", mName='" + mName + '\'' +
                ", mAddr='" + mAddr + '\'' +
                ", mPlz='" + mPlz + '\'' +
                ", mLat='" + mLat + '\'' +
                ", mLon='" + mLon + '\'' +
                '}';
    }
}
