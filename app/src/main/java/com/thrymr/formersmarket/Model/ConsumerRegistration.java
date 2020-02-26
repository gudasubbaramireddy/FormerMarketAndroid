package com.thrymr.formersmarket.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.database.annotations.NotNull;

@Entity(tableName = "consumer_reg")
public class ConsumerRegistration {
    private String consumerName, consumerPhone, consumerPassword, consumerState, consumerDistrict, consumerIsCity, consumerCity, consumerMandal,consumerVillage;

    private Long farmerId;
    @PrimaryKey
    @NotNull
    private Long consumerId;


    public ConsumerRegistration(String consumerName, String consumerPhone, String consumerPassword, String consumerState, String consumerDistrict, String consumerIsCity, String consumerCity, String consumerMandal, String consumerVillage, Long farmerId, Long consumerId) {
        this.consumerName = consumerName;
        this.consumerPhone = consumerPhone;
        this.consumerPassword = consumerPassword;
        this.consumerState = consumerState;
        this.consumerDistrict = consumerDistrict;
        this.consumerIsCity = consumerIsCity;
        this.consumerCity = consumerCity;
        this.consumerMandal = consumerMandal;
        this.consumerVillage = consumerVillage;
        this.farmerId = farmerId;
        this.consumerId = consumerId;
    }
    public ConsumerRegistration(){}

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerPhone() {
        return consumerPhone;
    }

    public void setConsumerPhone(String consumerPhone) {
        this.consumerPhone = consumerPhone;
    }

    public String getConsumerPassword() {
        return consumerPassword;
    }

    public void setConsumerPassword(String consumerPassword) {
        this.consumerPassword = consumerPassword;
    }

    public String getConsumerState() {
        return consumerState;
    }

    public void setConsumerState(String consumerState) {
        this.consumerState = consumerState;
    }

    public String getConsumerDistrict() {
        return consumerDistrict;
    }

    public void setConsumerDistrict(String consumerDistrict) {
        this.consumerDistrict = consumerDistrict;
    }

    public String getConsumerIsCity() {
        return consumerIsCity;
    }

    public void setConsumerIsCity(String consumerIsCity) {
        this.consumerIsCity = consumerIsCity;
    }

    public String getConsumerCity() {
        return consumerCity;
    }

    public void setConsumerCity(String consumerCity) {
        this.consumerCity = consumerCity;
    }

    public String getConsumerMandal() {
        return consumerMandal;
    }

    public void setConsumerMandal(String consumerMandal) {
        this.consumerMandal = consumerMandal;
    }

    public String getConsumerVillage() {
        return consumerVillage;
    }

    public void setConsumerVillage(String consumerVillage) {
        this.consumerVillage = consumerVillage;
    }

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }
}
