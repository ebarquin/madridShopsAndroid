package com.eugeniobarquin.madridshops.domain.interactors;

import android.support.annotation.NonNull;

import com.eugeniobarquin.madridshops.domain.model.Shops;

public interface GetAllShopsInteractorCompletion {
    void completion(@NonNull final Shops shops);
}
