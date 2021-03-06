package com.eugeniobarquin.madridshops.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.eugeniobarquin.madridshops.R;
import com.eugeniobarquin.madridshops.domain.interactors.GetAllShopsFromCacheInteractor;
import com.eugeniobarquin.madridshops.domain.interactors.GetAllShopsFromCacheInteractorImpl;
import com.eugeniobarquin.madridshops.domain.interactors.GetIfAllShopsAreCachedInteractor;
import com.eugeniobarquin.madridshops.domain.interactors.GetIfAllShopsAreCachedInteractorImpl;
import com.eugeniobarquin.madridshops.domain.interactors.GetAllShopsInteractor;
import com.eugeniobarquin.madridshops.domain.interactors.GetAllShopsInteractorCompletion;
import com.eugeniobarquin.madridshops.domain.interactors.GetAllShopsInteractorImpl;
import com.eugeniobarquin.madridshops.domain.interactors.InteractorErrorCompletion;
import com.eugeniobarquin.madridshops.domain.interactors.SaveAllShopsIntoCacheInteractor;
import com.eugeniobarquin.madridshops.domain.interactors.SaveAllShopsIntoCacheInteractorImpl;
import com.eugeniobarquin.madridshops.domain.interactors.SetAllShopsAreCachedInteractor;
import com.eugeniobarquin.madridshops.domain.interactors.SetAllShopsAreCachedInteractorImpl;
import com.eugeniobarquin.madridshops.domain.managers.cache.GetAllShopsFromCacheManager;
import com.eugeniobarquin.madridshops.domain.managers.cache.GetAllShopsFromCacheManagerDAOImpl;
import com.eugeniobarquin.madridshops.domain.managers.cache.SaveAllShopsIntoCacheManager;
import com.eugeniobarquin.madridshops.domain.managers.cache.SaveAllShopsIntoCacheManagerDAOImpl;
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

        GetIfAllShopsAreCachedInteractor getIfAllShopsAreCachedInteractor = new GetIfAllShopsAreCachedInteractorImpl(this);
        getIfAllShopsAreCachedInteractor.execute(new Runnable() {
            @Override
            public void run() {
                //all cached already, no need to download things, just read from DB
                readDataFromCache();
            }
        }, new Runnable() {
            @Override
            public void run() {
                //nothing cached yet
                obtainShopsList();

            }
        });

    }

    private void readDataFromCache(){
        GetAllShopsFromCacheManager getAllShopsFromCacheManager = new GetAllShopsFromCacheManagerDAOImpl(this);
        GetAllShopsFromCacheInteractor getAllShopsFromCacheInteractor = new GetAllShopsFromCacheInteractorImpl(getAllShopsFromCacheManager);
        getAllShopsFromCacheInteractor.execute(new GetAllShopsInteractorCompletion() {
            @Override
            public void completion(@NonNull Shops shops) {
                configShopsFragment(shops);


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

                SaveAllShopsIntoCacheManager saveManager = new SaveAllShopsIntoCacheManagerDAOImpl(getBaseContext());
                SaveAllShopsIntoCacheInteractor saveInteractor = new SaveAllShopsIntoCacheInteractorImpl(saveManager);
                saveInteractor.execute(shops, new Runnable() {
                    @Override
                    public void run() {
                        SetAllShopsAreCachedInteractor setAllShopsCachedInteractor = new SetAllShopsAreCachedInteractorImpl(getBaseContext());
                        setAllShopsCachedInteractor.execute(true);

                    }
                });


                configShopsFragment(shops);
                progressBar.setVisibility(View.INVISIBLE);


            }
        }, new InteractorErrorCompletion() {
            @Override
            public void OnError(String errorDescription) {
                progressBar.setVisibility(View.INVISIBLE);

            }
        });
    }

    private void configShopsFragment(final Shops shops) {
        shopsFragment.setShops(shops);
        shopsFragment.setOnElementClickListener(new OnElementClick<Shop>() {
            @Override
            public void clikedOn(@NonNull Shop element, int position) {
                //TODO: finish
                Navigator.navigateFromShopListAcivityToShopDetailActivity(ShopListActivity.this, element, position);
            }
        });

    }
}
