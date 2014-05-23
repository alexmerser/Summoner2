package com.example.summoner2.app.models;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.example.summoner2.app.AsyncTasks.AsyncSummoner;
import com.example.summoner2.app.MainActivity;
import com.example.summoner2.app.providers.SummonerApiProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

/**
 * Created by alex on 30.04.14.
 */
public class Summoner implements Serializable {
    private int id;
    private String name;
    private int profileIconId;
    private int summonerLevel;

    public Summoner(String name, String region, ActionBarActivity activity){
        AsyncSummoner async = new AsyncSummoner(activity);
        SummonerApiProvider summonerApi = new SummonerApiProvider(region);

        String resp = "";

            try {
                resp = async.execute(summonerApi.getSummoner_by_name_url(name)).get();

            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            try {
            /* create json from response */
                JSONObject jresp = new JSONObject(resp);
                JSONObject summJson = jresp.getJSONObject(name.toLowerCase());

                Log.d("JSON", summJson.toString());

                this.setId(summJson.getInt("id"));
                this.setName(summJson.getString("name"));
                this.setProfileIconId(summJson.getInt("profileIconId"));
                this.setSummonerLevel(summJson.getInt("summonerLevel"));

            } catch (JSONException e){
                e.printStackTrace();
            }


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }
}
