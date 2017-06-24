package com.eugeniobarquin.madridshops.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.eugeniobarquin.madridshops.MadridShopsApp;
import com.eugeniobarquin.madridshops.R;
import com.eugeniobarquin.madridshops.domain.interactors.ClearCacheInteractor;
import com.eugeniobarquin.madridshops.domain.interactors.ClearCacheInteractorImpl;
import com.eugeniobarquin.madridshops.domain.interactors.SetAllShopsAreCachedInteractor;
import com.eugeniobarquin.madridshops.domain.interactors.SetAllShopsAreCachedInteractorImpl;
import com.eugeniobarquin.madridshops.domain.managers.cache.ClearCacheManager;
import com.eugeniobarquin.madridshops.domain.managers.cache.ClearCacheManagerDAOImpl;
import com.eugeniobarquin.madridshops.navigator.Navigator;
import com.eugeniobarquin.madridshops.util.MainThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main__shops_button) Button shopsButton;
    @BindView(R.id.activity_main__activities_button) Button activitiesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //shopsButton = (Button) findViewById(R.id.activity_main__shops_button);

        shopsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.class.getCanonicalName(),"Hello Shops");

                Navigator.navigateFromMainActivityToShopListActivity(MainActivity.this);

            }
        });

        activitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.class.getCanonicalName(),"Hello Activities");

            }
        });

        launchInBackgroundThread();
    }
    private void launchInBackgroundThread() {

        //onPreexecute



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() { //doInBackground
                Log.d("Hilo", Thread.currentThread().getName());
                final String s = testMultithread();

                //onPostExecute

                //going to main thread, method 1 (Solo cuando estemos en una actividad)

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        shopsButton.setText(s);
                    }
                });

                //method 2 ( Creando una clase MainThread y es accesible desde cualquier parte del código

                MainThread.run(new Runnable() {
                    @Override
                    public void run() {

                    }
                });

            }
        });

        thread.start();


    }

    private String testMultithread() {
        final String web = "http://freegeoip.net/json";
        String result = null;
        try {
            URL url  = new URL(web);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            InputStream is = (InputStream) request.getContent();
            result = streamToString(is);
            Log.d("Web" , is.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;


    }

    String streamToString(InputStream in) throws IOException {
        StringBuilder out = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        for(String line = br.readLine(); line != null; line = br.readLine())
            out.append(line);
        br.close();
        return out.toString();
    }

    @OnClick(R.id.activity_main__clear__cache_button) void clearCache() {
        final ClearCacheManager clearCacheManager = new ClearCacheManagerDAOImpl(this);
            ClearCacheInteractor clearCacheInteractor = new ClearCacheInteractorImpl(clearCacheManager);

            clearCacheInteractor.execute(new Runnable() {
                @Override
                public void run() {
                    SetAllShopsAreCachedInteractor setAllShopsAreCachedInteractor = new SetAllShopsAreCachedInteractorImpl(getBaseContext());
                    setAllShopsAreCachedInteractor.execute(false);
                }
            });

    }
}
