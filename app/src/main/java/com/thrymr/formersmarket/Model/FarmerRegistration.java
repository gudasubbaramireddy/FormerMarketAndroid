package com.thrymr.formersmarket.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.util.TableInfo;

import com.google.firebase.database.annotations.NotNull;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "farmer_reg")
public class FarmerRegistration {
    private String farmerName, farmerPhone, farmerPassword, farmerState, farmerDistrict, farmerMandal, farmerVillage;

    @PrimaryKey
    @NotNull
    private Long farmerId;
    private Long consumerId;
    private byte[] photo;

    public FarmerRegistration(String farmerName, String farmerPhone, String farmerPassword, String farmerState, String farmerDistrict, String farmerMandal, String farmerVillage, Long farmerId, Long consumerId, byte[] photo) {
        this.farmerName = farmerName;
        this.farmerPhone = farmerPhone;
        this.farmerPassword = farmerPassword;
        this.farmerState = farmerState;
        this.farmerDistrict = farmerDistrict;
        this.farmerMandal = farmerMandal;
        this.farmerVillage = farmerVillage;
        this.farmerId = farmerId;
        this.consumerId = consumerId;
        this.photo = photo;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getFarmerPhone() {
        return farmerPhone;
    }

    public void setFarmerPhone(String farmerPhone) {
        this.farmerPhone = farmerPhone;
    }

    public String getFarmerPassword() {
        return farmerPassword;
    }

    public void setFarmerPassword(String farmerPassword) {
        this.farmerPassword = farmerPassword;
    }

    public String getFarmerState() {
        return farmerState;
    }

    public void setFarmerState(String farmerState) {
        this.farmerState = farmerState;
    }

    public String getFarmerDistrict() {
        return farmerDistrict;
    }

    public void setFarmerDistrict(String farmerDistrict) {
        this.farmerDistrict = farmerDistrict;
    }

    public String getFarmerMandal() {
        return farmerMandal;
    }

    public void setFarmerMandal(String farmerMandal) {
        this.farmerMandal = farmerMandal;
    }

    public String getFarmerVillage() {
        return farmerVillage;
    }

    public void setFarmerVillage(String farmerVillage) {
        this.farmerVillage = farmerVillage;
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    /*public Bitmap getPhoto() {
        return BitmapFactory.decodeByteArray(photo,0,fPhone.length());
    }*/

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }
}
