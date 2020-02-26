package com.thrymr.formersmarket.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thrymr.formersmarket.Model.ConsumerRegistration;

import java.util.List;

@Dao
public interface ConsumerRegDao  {

    @Insert
    void insert(ConsumerRegistration registration);

    @Update
    void update(ConsumerRegistration registration);

    @Delete
    void delete(ConsumerRegistration registration);

    @Query("Delete FROM consumer_reg")
    void deleteAll();

    @Query("SELECT * FROM consumer_reg")
    LiveData<List<ConsumerRegistration>> getAllRegistrations();
}
