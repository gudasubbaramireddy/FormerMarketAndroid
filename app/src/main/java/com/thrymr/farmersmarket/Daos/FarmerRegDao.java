package com.thrymr.farmersmarket.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thrymr.farmersmarket.Model.FarmerRegistration;

import java.util.List;

@Dao
public interface FarmerRegDao {

    @Insert
    void insert(FarmerRegistration registration);

    @Update
    void update(FarmerRegistration registration);

    @Delete
    void delete(FarmerRegistration registration);

    @Query("Delete FROM farmer_reg")
    void deleteAll();

    @Query("SELECT * FROM farmer_reg")
    LiveData<List<FarmerRegistration>> getAllRegistrations();
}
