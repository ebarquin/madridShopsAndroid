package com.eugeniobarquin.madridshops.domain.managers.cache;

import android.support.annotation.NonNull;

import com.eugeniobarquin.madridshops.domain.model.Shops;

public interface GetAllShopsFromCacheManagerCompletion {
    void completion(@NonNull final Shops shops);
}
