package com.thrymr.formersmarket.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thrymr.formersmarket.Model.FarmerRegistration;
import com.thrymr.formersmarket.Model.ServerImageEvent;

import java.util.List;

@Dao
public interface ServerImageEventDao {

    @Insert
    void insert(ServerImageEvent registration);

    @Update
    void update(ServerImageEvent registration);

    @Delete
    void delete(ServerImageEvent registration);

    @Query("Delete FROM server_image_event")
    void deleteAll();

    /*@Query("SELECT * FROM server_image_event")
    LiveData<List<FarmerRegistration>> getAllRegistrations();*/
}
