package com.example.summoner2.app.activities;


import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.summoner2.app.AsyncTasks.AsyncSummoner;
import com.example.summoner2.app.MainActivity;
import com.example.summoner2.app.R;
import com.example.summoner2.app.models.Category;
import com.example.summoner2.app.models.League;
import com.example.summoner2.app.models.Summoner;
import com.example.summoner2.app.providers.LeagueApiProvider;
import com.example.summoner2.app.adapters.leagueExpListApdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SummonerActivity extends ActionBarActivity {

    List<Category> catList = new ArrayList<Category>();
    TextView sName;
    TextView sLevel;
    Summoner summoner;
    String region;
    ExpandableListView exList;
    PopupWindow pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoner);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        region = intent.getStringExtra("region");
        summoner = (Summoner) intent.getSerializableExtra("summ");
        //summoner = new Summoner(summonerName, region, this);

        // Create the text view
        sName = (TextView) findViewById(R.id.summoner_name);
        sLevel = (TextView) findViewById(R.id.summoner_level);

        sName.setText(summoner.getName());
        //WTF ?????
        String slevel = summoner.getSummonerLevel() + "";

        sLevel.setText(slevel);

        exList = (ExpandableListView) findViewById(R.id.expandableListView);
        TextView regionView = (TextView) findViewById(R.id.regionTextView);
        regionView.setText(region);

    }

    @Override
    protected void onResume() {
        super.onResume();

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Ranked stats not found!");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        if (summoner.getSummonerLevel() == 30) {
            // Get the message from the intent

            //-------------------------------------------------------//
            AsyncSummoner async = new AsyncSummoner(this);
            LeagueApiProvider leagueApi = new LeagueApiProvider(summoner.getId(), region);

            String league_api_response = "";
            try {
                league_api_response = async.execute(leagueApi.getLeague_by_summoner_id()).get();

            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            JSONArray array = null;
            try {
                array = new JSONArray(league_api_response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < array.length(); i++) {
                JSONObject row = null;
                try {
                    row = array.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ArrayList<League> leagues_info = new ArrayList<League>();
                League tmp = new League(row, i);
                leagues_info.add(tmp);
                Category cat = new Category(i, tmp.getQueueType(), tmp.getLeagueName());
                cat.setItemList(leagues_info);
                catList.add(cat);
            }


            exList.setIndicatorBounds(5, 5);
            leagueExpListApdapter exAdpt = new leagueExpListApdapter(catList, this);
            exList.setIndicatorBounds(0, 20);
            exList.setAdapter(exAdpt);
        } else{
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.summoner, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.chemps) {

        }
        return super.onOptionsItemSelected(item);
    }


}
