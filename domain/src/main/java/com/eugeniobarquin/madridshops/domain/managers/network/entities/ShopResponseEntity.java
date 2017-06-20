package com.eugeniobarquin.madridshops.domain.managers.network.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopResponseEntity {
    @SerializedName("result") private List<ShopEntity> result;

    public List<ShopEntity> getResult() {
        return result;
    }
}
