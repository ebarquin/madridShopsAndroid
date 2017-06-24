package com.eugeniobarquin.madridshops.domain.managers.cache;

import android.support.annotation.NonNull;

public interface ClearCacheManager {
    void execute(@NonNull Runnable completion);
}
