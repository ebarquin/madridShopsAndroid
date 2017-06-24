package com.eugeniobarquin.madridshops;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.eugeniobarquin.madridshops.domain.interactors.GetAllShopsInteractor;
import com.eugeniobarquin.madridshops.domain.interactors.GetAllShopsInteractorCompletion;
import com.eugeniobarquin.madridshops.domain.interactors.GetAllShopsInteractorImpl;
import com.eugeniobarquin.madridshops.domain.interactors.InteractorErrorCompletion;
import com.eugeniobarquin.madridshops.domain.managers.network.GetAllShopsManagerCompletion;
import com.eugeniobarquin.madridshops.domain.managers.network.GetAllShopsManagerImpl;
import com.eugeniobarquin.madridshops.domain.managers.network.ManagerErrorCompletion;
import com.eugeniobarquin.madridshops.domain.managers.network.NetworkManager;
import com.eugeniobarquin.madridshops.domain.model.Shops;
import com.squareup.picasso.Picasso;


public class MadridShopsApp extends MultiDexApplication {

    public static final String APP_NAME = MadridShopsApp.class.getCanonicalName();

    @Override
    public void onCreate() {
        super.onCreate();

        //init app

        Log.d(APP_NAME, "App starting" + BuildConfig.BASE_URL);

        //Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        //low memory: dump something
    }
}
