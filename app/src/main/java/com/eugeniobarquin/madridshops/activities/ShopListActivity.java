package com.eugeniobarquin.madridshops.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eugeniobarquin.madridshops.R;
import com.eugeniobarquin.madridshops.domain.interactors.GetAllShopsInteractor;
import com.eugeniobarquin.madridshops.domain.interactors.GetAllShopsInteractorCompletion;
import com.eugeniobarquin.madridshops.domain.interactors.GetAllShopsInteractorFakeImpl;
import com.eugeniobarquin.madridshops.domain.interactors.GetAllShopsInteractorImpl;
import com.eugeniobarquin.madridshops.domain.interactors.InteractorErrorCompletion;
import com.eugeniobarquin.madridshops.domain.managers.network.GetAllShopsManagerImpl;
import com.eugeniobarquin.madridshops.domain.managers.network.NetworkManager;
import com.eugeniobarquin.madridshops.domain.model.Shop;
import com.eugeniobarquin.madridshops.domain.model.Shops;
import com.eugeniobarquin.madridshops.fragments.ShopsFragment;
import com.eugeniobarquin.madridshops.navigator.Navigator;
import com.eugeniobarquin.madridshops.views.OnElementClick;

public class ShopListActivity extends AppCompatActivity {

    ShopsFragment shopsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shop_list__fragment_shops);

        //obtain shops list
        NetworkManager manager = new GetAllShopsManagerImpl(this);

        GetAllShopsInteractor getAllShopsInteractor = new GetAllShopsInteractorImpl(manager);
        getAllShopsInteractor.execute(new GetAllShopsInteractorCompletion() {
            @Override
            public void completion(Shops shops) {
                System.out.println("Hello hello");
                shopsFragment.setShops(shops);
                shopsFragment.setOnElementClickListener(new OnElementClick<Shop>() {
                    @Override
                    public void clikedOn(@NonNull Shop element, int position) {
                        //TODO: finish
                        Navigator.navigateFromShopListAcivityToShopDetailActivity(ShopListActivity.this, element, position);
                    }
                });
            }
        }, new InteractorErrorCompletion() {
            @Override
            public void OnError(String errorDescription) {

            }
        });

    }
}
