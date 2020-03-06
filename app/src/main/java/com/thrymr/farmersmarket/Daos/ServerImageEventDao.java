package com.thrymr.farmersmarket.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thrymr.farmersmarket.Model.ServerImageEvent;

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
