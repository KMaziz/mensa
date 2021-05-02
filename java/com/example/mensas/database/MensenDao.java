package com.example.mensas.database;

import android.arch.persistence.room.*;


import java.util.List;

@Dao
public interface MensenDao {

    @Query("Select * From mensen")
    List<Mensa> getAll();

    @Query("SELECT * FROM mensen WHERE id IN (:mensaIds)")
    List<Mensa> loadAllByIds(int[] mensaIds);

    @Query("SELECT * FROM mensen WHERE mName LIKE :mensaName")
    Mensa findByName(String mensaName);

 
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Mensa... mens);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Mensa mensa);

    @Delete
    void delete(Mensa mensa);
}
