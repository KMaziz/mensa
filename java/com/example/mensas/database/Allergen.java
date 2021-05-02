package com.example.mensas.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "allergen")
public class Allergen implements Serializable {

    @PrimaryKey
    @NonNull
    private int aid;

    private String aName;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAName() {
        return aName;
    }

    public void setAName(String aName) {
        this.aName = aName;
    }

    public Allergen(int aid, String aName) {
        this.aid = aid;
        this.aName = aName;
    }

    public Allergen() {
    }
}
