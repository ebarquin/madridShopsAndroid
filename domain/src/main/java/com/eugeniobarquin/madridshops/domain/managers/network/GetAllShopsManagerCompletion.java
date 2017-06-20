package com.eugeniobarquin.madridshops.domain.managers.network;

import android.support.annotation.NonNull;

import com.eugeniobarquin.madridshops.domain.managers.network.entities.ShopEntity;
import com.eugeniobarquin.madridshops.domain.model.Shops;

import java.util.List;

public interface GetAllShopsManagerCompletion {


    void completion(@NonNull final List<ShopEntity> shopsEntities);
}
