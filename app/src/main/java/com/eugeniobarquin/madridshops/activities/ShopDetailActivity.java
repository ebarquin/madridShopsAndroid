package com.eugeniobarquin.madridshops.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.eugeniobarquin.madridshops.R;
import com.eugeniobarquin.madridshops.domain.model.Shop;
import com.eugeniobarquin.madridshops.util.Constants;
import com.eugeniobarquin.madridshops.util.StaticMapImage;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopDetailActivity extends AppCompatActivity {

    @BindView(R.id.activity_shop_detail__shop_address) TextView address;
    @BindView(R.id.activity_shop_detail__shop_description) TextView description;
    @BindView(R.id.activity_shop_detail__shop_image) ImageView shopImage;
    @BindView(R.id.activity_shop_detail__shop_name) TextView name;
    @BindView(R.id.activity_shop_detail__shop_map) ImageView mapImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
           Shop shop = (Shop) intent.getSerializableExtra(Constants.INTENT_SHOP_DETAIL);
            name.setText(shop.getName());
            address.setText(shop.getAddress());
            description.setText(shop.getDescription());
            Picasso.with(this).load(shop.getImageUrl()).placeholder(R.drawable.shop_icon).into(shopImage);

            String staticMapUrl = StaticMapImage.getMapImage(shop);
            Picasso.with(this).load(staticMapUrl).placeholder(R.drawable.map_placeholder).into(mapImage);
        }
    }
}
