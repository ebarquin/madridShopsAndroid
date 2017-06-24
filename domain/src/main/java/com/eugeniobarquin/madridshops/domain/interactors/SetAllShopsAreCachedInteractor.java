package com.eugeniobarquin.madridshops.domain.interactors;

public interface SetAllShopsAreCachedInteractor {
    public static final String SHOPS_SAVED = "SHOP_SAVED";

    void execute (boolean shopsSaved);
}
