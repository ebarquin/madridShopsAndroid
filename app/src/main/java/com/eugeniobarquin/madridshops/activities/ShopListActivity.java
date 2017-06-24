package com.eugeniobarquin.madridshops.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.eugeniobarquin.madridshops.R;
import com.eugeniobarquin.madridshops.domain.interactors.GetIfAllShopsAreCachedInteractor;
import com.eugeniobarquin.madridshops.domain.interactors.GetIfAllShopsAreCachedInteractorImpl;
import com.eugeniobarquin.madridshops.domain.interactors.GetAllShopsInteractor;
import com.eugeniobarquin.madridshops.domain.interactors.GetAllShopsInteractorCompletion;
import com.eugeniobarquin.madridshops.domain.interactors.GetAllShopsInteractorImpl;
import com.eugeniobarquin.madridshops.domain.interactors.InteractorErrorCompletion;
import com.eugeniobarquin.madridshops.domain.interactors.SetAllShopsAreCachedInteractor;
import com.eugeniobarquin.madridshops.domain.interactors.SetAllShopsAreCachedInteractorImpl;
import com.eugeniobarquin.madridshops.domain.managers.network.GetAllShopsManagerImpl;
import com.eugeniobarquin.madridshops.domain.managers.network.NetworkManager;
import com.eugeniobarquin.madridshops.domain.model.Shop;
import com.eugeniobarquin.madridshops.domain.model.Shops;
import com.eugeniobarquin.madridshops.fragments.ShopsFragment;
import com.eugeniobarquin.madridshops.navigator.Navigator;
import com.eugeniobarquin.madridshops.views.OnElementClick;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopListActivity extends AppCompatActivity {
    @BindView(R.id.activity_shop_list__progress_bar) ProgressBar progressBar;

    ShopsFragment shopsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        ButterKnife.bind(this);

        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shop_list__fragment_shops);

        GetIfAllShopsAreCachedInteractor getAllShopsCachedInteractor = new GetIfAllShopsAreCachedInteractorImpl(this);
        getAllShopsCachedInteractor.execute(new Runnable() {
            @Override
            public void run() {
                //all cached already, no need to download things, just read from DB
            }
        }, new Runnable() {
            @Override
            public void run() {
                //nothing cached yet
                obtainShopsList();

            }
        });



    }

    private void obtainShopsList() {
        progressBar.setVisibility(View.VISIBLE);

        NetworkManager manager = new GetAllShopsManagerImpl(this);

        GetAllShopsInteractor getAllShopsInteractor = new GetAllShopsInteractorImpl(manager);
        getAllShopsInteractor.execute(new GetAllShopsInteractorCompletion() {
            @Override
            public void completion(Shops shops) {
                System.out.println("Hello hello");

                progressBar.setVisibility(View.INVISIBLE);

                //TODO: persist in cache all shops

                SetAllShopsAreCachedInteractor setAllShopsCachedInteractor = new SetAllShopsAreCachedInteractorImpl(getBaseContext());
                setAllShopsCachedInteractor.execute(true);

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
                progressBar.setVisibility(View.INVISIBLE);

            }
        });
    }
}
