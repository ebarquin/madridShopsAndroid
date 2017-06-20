package com.eugeniobarquin.madridshops.navigator;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.eugeniobarquin.madridshops.activities.MainActivity;
import com.eugeniobarquin.madridshops.activities.ShopListActivity;

public class Navigator {
    public static Intent navigateFromMainActivityToShopListActivity(@NonNull final MainActivity mainActivity) {
        assert (mainActivity != null);

        final Intent i = new Intent(mainActivity, ShopListActivity.class);
        mainActivity.startActivity(i);

        return i;

    }
}
