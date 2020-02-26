package com.thrymr.formersmarket.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.thrymr.formersmarket.Daos.ConsumerRegDao;
import com.thrymr.formersmarket.Daos.FarmerConsumerRegDao;
import com.thrymr.formersmarket.Daos.FarmerRegDao;
import com.thrymr.formersmarket.Daos.ServerImageEventDao;
import com.thrymr.formersmarket.Model.ConsumerRegistration;
import com.thrymr.formersmarket.Model.FarmerConsumerRegistration;
import com.thrymr.formersmarket.Model.FarmerRegistration;
import com.thrymr.formersmarket.Model.ServerImageEvent;

@Database(entities = {ConsumerRegistration.class, FarmerRegistration.class, FarmerConsumerRegistration.class,ServerImageEvent.class},version = 1)
public abstract class FarmersMarketDatabase extends RoomDatabase {

    private static FarmersMarketDatabase instance;

    public abstract ConsumerRegDao consumerRegDao();
    public abstract FarmerRegDao farmerRegDao();
    public abstract ServerImageEventDao serverImageEventDao();
    public abstract FarmerConsumerRegDao farmerConsumerRegDao();

    /**
 * this is for to create instance only once because it is singleton class
 * */

    public static synchronized FarmersMarketDatabase getInstance(Context context) {
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(), FarmersMarketDatabase.class,"former_market_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
