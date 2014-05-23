package com.example.summoner2.app.AsyncTasks;

/**
 * Created by alex on 30.04.14.
 */
import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.SyncStateContract;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.example.summoner2.app.MainActivity;
import com.example.summoner2.app.activities.SummonerActivity;
import com.example.summoner2.app.providers.SummonerApiProvider;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class AsyncSummoner extends AsyncTask<String, String, String> {

    private String api_response = "None";
    private ProgressDialog dialog;

    public AsyncSummoner(ActionBarActivity activity) {
        dialog = new ProgressDialog(activity);
    }


    protected void onPreExecute() {

        dialog.setMessage("Loading");
        dialog.setIndeterminate(false);
        dialog.show();
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... params) {
        //Make get request
        try {
            HttpClient client = new DefaultHttpClient();
            String url = params[0];
            Log.d("GET URL", url);
            HttpGet get = new HttpGet(url);
            HttpResponse response = client.execute(get);
            HttpEntity resEntity = response.getEntity();
            if(resEntity != null){
                api_response = EntityUtils.toString(resEntity);
                Log.i("GET RESPONSE", api_response);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return api_response;

    }


    protected void onPostExecute(String result) {
        // execution of result of Long time consuming operation
        super.onPostExecute(result);
        dialog.dismiss();
    }

    /*
     * (non-Javadoc)
     *
     * @see android.os.AsyncTask#onPreExecute()
     */


    protected void onProgressUpdate(String... text) {
        super.onProgressUpdate(text[0]);
    }

}
