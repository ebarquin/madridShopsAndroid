package com.eugeniobarquin.madridshops.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eugeniobarquin.madridshops.R;
import com.eugeniobarquin.madridshops.domain.model.Shop;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

public class ShopRowViewHolder extends RecyclerView.ViewHolder {

    private TextView shopNameTextView;
    private ImageView shopLogoImageView;
    WeakReference<Context> context;

    public ShopRowViewHolder(View rowShop) {
        super(rowShop);

        this.context = new WeakReference<Context>(rowShop.getContext());

        shopNameTextView = (TextView) rowShop.findViewById(R.id.row_shop__shop_name);
        shopLogoImageView = (ImageView) rowShop.findViewById(R.id.row_shop__shop_logo);
    }

    public void setShop(Shop shop) {
        if (shop == null) {
            return;
        }

        shopNameTextView.setText(shop.getName());
        Picasso.with(context.get()).
                load(shop.getLogoUrl()).
                placeholder(R.drawable.shop_icon).
                //networkPolicy(NetworkPolicy.NO_CACHE).
                into(shopLogoImageView);
    }
}
