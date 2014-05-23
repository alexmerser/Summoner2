package com.example.summoner2.app.providers;

import android.util.Log;

/**
 * Created by alex on 30.04.14.
 */

public class ApiProvider {
    private String api;

    private String api_key;
    private String realm;

    public ApiProvider(){}

    public ApiProvider(String realm){
        this.setRealm(""+realm+"/");
        Log.d("REALM", realm);
        if (realm.toLowerCase().equals("ru")) {
            Log.d("TRUE","RUE");
            this.setApi("http://eu.api.pvp.net/api/lol/");
        } else {
            this.setApi("https://prod.api.pvp.net/api/lol/");
        }

        this.setApi_key("?api_key=30e093b0-daec-43d4-b198-e6cb11439dff");
    }


    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
}
