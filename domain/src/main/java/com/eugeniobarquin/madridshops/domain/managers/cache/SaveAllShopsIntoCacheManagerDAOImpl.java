package com.eugeniobarquin.madridshops.domain.managers.cache;

import android.content.Context;

import com.eugeniobarquin.madridshops.domain.managers.db.ShopDAO;
import com.eugeniobarquin.madridshops.domain.model.Shop;
import com.eugeniobarquin.madridshops.domain.model.Shops;

import java.lang.ref.WeakReference;

public class SaveAllShopsIntoCacheManagerDAOImpl implements SaveAllShopsIntoCacheManager {

    private WeakReference<Context> contextWeakReference;

    public SaveAllShopsIntoCacheManagerDAOImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);

    }

    @Override
    public void execute(Shops shops, Runnable completion) {
        ShopDAO dao = new ShopDAO(contextWeakReference.get());
        for (Shop shop : shops.allShops()) {
            dao.insert(shop);
        }

        completion.run();
    }
}
