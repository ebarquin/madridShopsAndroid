package com.eugeniobarquin.madridshops.domain.managers.cache;

import android.support.annotation.NonNull;

import com.eugeniobarquin.madridshops.domain.managers.network.GetAllShopsManagerCompletion;

public interface GetAllShopsFromCacheManager {
    void execute(@NonNull final GetAllShopsFromCacheManagerCompletion completion);
}
