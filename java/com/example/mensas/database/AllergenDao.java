package com.example.mensas.database;

import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface AllergenDao {

    @Query("SELECT * FROM allergen")
    List<Allergen> getAll();

    @Query("SELECT * FROM allergen WHERE id = :id")
    Allergen findAllergenById(int id);

    @Query("SELECT COUNT(*) FROM allergen")
    int countAllergen();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllergens(List<Allergen> allergens);
}
