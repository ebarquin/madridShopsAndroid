package com.eugeniobarquin.madridshops.views;

import android.support.annotation.NonNull;

public interface OnElementClick<T> {
   void  clikedOn(@NonNull T element, int position);
}
