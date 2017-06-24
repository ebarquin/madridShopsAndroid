package com.eugeniobarquin.madridshops.domain.managers.cache;

import android.content.Context;
import android.support.annotation.NonNull;

import com.eugeniobarquin.madridshops.domain.managers.db.ShopDAO;
import com.eugeniobarquin.madridshops.domain.managers.network.GetAllShopsManagerCompletion;
import com.eugeniobarquin.madridshops.domain.model.Shop;
import com.eugeniobarquin.madridshops.domain.model.Shops;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetAllShopsFromCacheManagerDAOImpl implements GetAllShopsFromCacheManager {

    private WeakReference<Context> contextWeakReference;

    public GetAllShopsFromCacheManagerDAOImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void execute(@NonNull GetAllShopsFromCacheManagerCompletion completion) {
        ShopDAO dao = new ShopDAO(contextWeakReference.get());
        List<Shop> shopList = dao.query();
        if (shopList == null) {

            return;
        }
        Shops shops = Shops.from(shopList);
        completion.completion(shops);
    }
}
