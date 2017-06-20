package com.eugeniobarquin.madridshops.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eugeniobarquin.madridshops.R;
import com.eugeniobarquin.madridshops.domain.interactors.GetAllShopsInteractor;
import com.eugeniobarquin.madridshops.domain.interactors.GetAllShopsInteractorCompletion;
import com.eugeniobarquin.madridshops.domain.interactors.GetAllShopsInteractorFakeImpl;
import com.eugeniobarquin.madridshops.domain.interactors.InteractorErrorCompletion;
import com.eugeniobarquin.madridshops.domain.model.Shop;
import com.eugeniobarquin.madridshops.domain.model.Shops;
import com.eugeniobarquin.madridshops.fragments.ShopsFragment;

public class ShopListActivity extends AppCompatActivity {

    ShopsFragment shopsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shop_list__fragment_shops);

        //obtain shops list

        GetAllShopsInteractor getAllShopsInteractor = new GetAllShopsInteractorFakeImpl();
        getAllShopsInteractor.execute(new GetAllShopsInteractorCompletion() {
            @Override
            public void completion(Shops shops) {
                System.out.println("Hello hello");
                shopsFragment.setShops(shops);
            }
        }, new InteractorErrorCompletion() {
            @Override
            public void OnError(String errorDescription) {

            }
        });

    }
}
