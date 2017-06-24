package com.eugeniobarquin.madridshops.domain.interactors;

import com.eugeniobarquin.madridshops.domain.managers.cache.ClearCacheManager;


public class ClearCacheInteractorImpl implements ClearCacheInteractor {
     private ClearCacheManager manager;

    public ClearCacheInteractorImpl(ClearCacheManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(Runnable completion) {
        manager.execute(completion);

    }
}
