package com.eugeniobarquin.madridshops.domain.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;

public class GetIfAllShopsAreCachedInteractorImpl implements GetIfAllShopsAreCachedInteractor {
    private WeakReference<Context> context;

    public GetIfAllShopsAreCachedInteractorImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }



    @Override
    public void execute(Runnable onAllShopsAreCached, Runnable onAllShopsAreNotCached) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        boolean shopSaved = preferences.getBoolean(SetAllShopsAreCachedInteractorImpl.SHOPS_SAVED, false);

        if (shopSaved) {
            onAllShopsAreCached.run();

        } else {
            onAllShopsAreNotCached.run();
        }
    }
}
