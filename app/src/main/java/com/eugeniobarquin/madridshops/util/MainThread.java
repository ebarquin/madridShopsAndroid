package com.eugeniobarquin.madridshops.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

public class MainThread {
    public static void  run(@NonNull final  Runnable codeToRunOnMainThrad) {
        if (codeToRunOnMainThrad == null) {
            return;
        }

        Handler uiHandler = new Handler(Looper.getMainLooper());
        uiHandler.post(codeToRunOnMainThrad);

    }
}
