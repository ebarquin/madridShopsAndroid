package com.eugeniobarquin.madridshops.domain.managers.cache;

import android.content.Context;
import android.support.annotation.NonNull;

import com.eugeniobarquin.madridshops.domain.managers.db.ShopDAO;

import java.lang.ref.WeakReference;

public class ClearCacheManagerDAOImpl implements ClearCacheManager {
    private WeakReference<Context> mContextWeakReference;

    public ClearCacheManagerDAOImpl(Context contextWeakReference) {
        this.mContextWeakReference = new WeakReference<Context> (contextWeakReference);
    }

    @Override
    public void execute(@NonNull Runnable completion) {
        ShopDAO dao = new ShopDAO(mContextWeakReference.get());
        dao.deleteAll();
        completion.run();

    }
}
