package com.eugeniobarquin.madridshops.domain.managers.cache;

import com.eugeniobarquin.madridshops.domain.model.Shops;

public interface SaveAllShopsIntoCacheManager {
    void execute(Shops shops, Runnable completion);
}
