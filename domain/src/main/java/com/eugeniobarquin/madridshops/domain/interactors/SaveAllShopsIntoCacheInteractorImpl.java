package com.eugeniobarquin.madridshops.domain.interactors;

import com.eugeniobarquin.madridshops.domain.managers.cache.SaveAllShopsIntoCacheManager;
import com.eugeniobarquin.madridshops.domain.model.Shops;

public class SaveAllShopsIntoCacheInteractorImpl implements SaveAllShopsIntoCacheInteractor {

    public SaveAllShopsIntoCacheInteractorImpl(SaveAllShopsIntoCacheManager manager) {
        this.manager = manager;
    }

    private SaveAllShopsIntoCacheManager manager;

    @Override
    public void execute(Shops shops, Runnable completion) {
        manager.execute(shops, completion);

    }
}
