package com.thrymr.formersmarket.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thrymr.formersmarket.Model.ConsumerRegistration;
import com.thrymr.formersmarket.Model.FarmerConsumerRegistration;

import java.util.List;

@Dao
public interface FarmerConsumerRegDao {

    @Insert
    void insert(FarmerConsumerRegistration registration);

    @Update
    void update(FarmerConsumerRegistration registration);

    @Delete
    void delete(FarmerConsumerRegistration registration);

    @Query("Delete FROM former_consumer_reg")
    void deleteAll();

    @Query("SELECT * FROM former_consumer_reg")
    LiveData<List<FarmerConsumerRegistration>> getAllRegistrations();
}
