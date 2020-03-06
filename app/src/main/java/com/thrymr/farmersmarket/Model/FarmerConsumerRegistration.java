package com.thrymr.farmersmarket.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import androidx.annotation.NonNull;


@Entity(tableName = "former_consumer_reg",primaryKeys = {"farmerId","consumerId"},foreignKeys = {
        @ForeignKey(entity = FarmerRegistration.class,parentColumns = "farmerId",childColumns = "farmerId"),
        @ForeignKey(entity = ConsumerRegistration.class,parentColumns = "consumerId",childColumns = "consumerId")})
public class FarmerConsumerRegistration {

    @NonNull
    private Long farmerId;
    @NonNull
    private Long consumerId;

    public Long getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Long formerId) {
        this.farmerId = formerId;
    }

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }

}
