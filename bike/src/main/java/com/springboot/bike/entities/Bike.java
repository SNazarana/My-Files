package com.springboot.bike.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Positive;

import org.springframework.validation.annotation.Validated;


@Entity
@Table(name="bikes")
@Validated
public class Bike {
    @Id
    private Long bikeId;
    @NotBlank(message = "Invalid Name:Name should not be blank")
    private String name;
    @NotBlank(message = "Invalid serialnumber:serialnumber should not be blank")
    private String serialnumber;
    @NotBlank(message = "Invalid purchasedate: purchasedate should not be blank")
    private String purchasedate;
    @Positive(message="Invalid purchaseprice:purchaseprice should not be zero or negative")
    @NotNull(message = "Invalid purchaseprice: purchaseprice is NULL")
    private Float purchaseprice;

    public Bike(String name, String serialnumber, Float purchaseprice, String purchasedate) {
        super();
        this.bikeId = bikeId;
        this.name = name;
        this.serialnumber = serialnumber;
        this.purchaseprice = purchaseprice;
        this.purchasedate = purchasedate;
    }

    public Bike(){}

    public Long getBikeId() {
        return bikeId;
    }

    public void setBikeId(Long bikeId) {
        this.bikeId = bikeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public Float getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(Float purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public String getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(String purchasedate) {
        this.purchasedate = purchasedate;
    }


}
