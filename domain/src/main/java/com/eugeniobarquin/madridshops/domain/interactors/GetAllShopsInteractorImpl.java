package com.eugeniobarquin.madridshops.domain.interactors;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.eugeniobarquin.madridshops.domain.managers.network.GetAllShopsManagerCompletion;
import com.eugeniobarquin.madridshops.domain.managers.network.GetAllShopsManagerImpl;
import com.eugeniobarquin.madridshops.domain.managers.network.ManagerErrorCompletion;
import com.eugeniobarquin.madridshops.domain.managers.network.NetworkManager;
import com.eugeniobarquin.madridshops.domain.managers.network.entities.ShopEntity;

import java.util.List;

public class GetAllShopsInteractorImpl implements GetAllShopsInteractor {

    NetworkManager networkManager;

    public GetAllShopsInteractorImpl(@NonNull final NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    @Override
    public void execute(@NonNull GetAllShopsInteractorCompletion completion, @Nullable InteractorErrorCompletion onError) {
        if (this.networkManager == null) {
            if (onError == null) {
                throw new IllegalStateException("Network manager can't be null");
            } else {
                onError.OnError("");
            }
        }

        this.networkManager.getShopsFromServer(new GetAllShopsManagerCompletion() {
            @Override
            public void completion(@NonNull List<ShopEntity> shopsEntities) {
                Log.d("SHOPS", shopsEntities.toString());

            }
        }, new ManagerErrorCompletion() {
            @Override
            public void onError(String errorDescription) {

            }
        });
    }


}
