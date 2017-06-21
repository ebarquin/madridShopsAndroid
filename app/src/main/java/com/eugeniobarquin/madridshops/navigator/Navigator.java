package com.eugeniobarquin.madridshops.navigator;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.eugeniobarquin.madridshops.activities.MainActivity;
import com.eugeniobarquin.madridshops.activities.ShopDetailActivity;
import com.eugeniobarquin.madridshops.activities.ShopListActivity;
import com.eugeniobarquin.madridshops.domain.model.Shop;

import static com.eugeniobarquin.madridshops.util.Constants.INTENT_SHOP_DETAIL;

public class Navigator {
    public static Intent navigateFromMainActivityToShopListActivity(@NonNull final MainActivity mainActivity) {
        assert (mainActivity != null);

        final Intent i = new Intent(mainActivity, ShopListActivity.class);
        mainActivity.startActivity(i);

        return i;

    }

    public static Intent navigateFromShopListAcivityToShopDetailActivity(@NonNull final ShopListActivity shopListActivity, final Shop shop, final int position) {
        assert(shopListActivity != null);

        final Intent i = new Intent(shopListActivity, ShopDetailActivity.class);
        i.putExtra(INTENT_SHOP_DETAIL, shop);
        shopListActivity.startActivity(i);

        return i;

    }
}
