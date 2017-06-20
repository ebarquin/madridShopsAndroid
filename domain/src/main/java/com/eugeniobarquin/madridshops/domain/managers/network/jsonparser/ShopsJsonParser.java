package com.eugeniobarquin.madridshops.domain.managers.network.jsonparser;

import com.eugeniobarquin.madridshops.domain.managers.network.entities.ShopEntity;
import com.eugeniobarquin.madridshops.domain.managers.network.entities.ShopResponseEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

public class ShopsJsonParser {

    public List<ShopEntity> parse(String response){
        List<ShopEntity> shopEntities = null;

        try {

            Gson gson = new GsonBuilder().create();

            Reader reader = new StringReader(response);

            ShopResponseEntity shopResponseEntity = gson.fromJson(reader, ShopResponseEntity.class);
            shopEntities = shopResponseEntity.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shopEntities;
    }
}
