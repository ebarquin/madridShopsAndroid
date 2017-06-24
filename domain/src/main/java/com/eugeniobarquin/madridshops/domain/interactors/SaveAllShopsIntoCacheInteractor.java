package com.eugeniobarquin.madridshops.domain.interactors;

import com.eugeniobarquin.madridshops.domain.model.Shops;

public interface SaveAllShopsIntoCacheInteractor {
    void execute(Shops shops, Runnable completion);
}
