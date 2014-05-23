package com.example.summoner2.app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.summoner2.app.activities.SummonerActivity;
import com.example.summoner2.app.models.Summoner;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String region;

    public void findSummoner(View view) {
        final Intent intent = new Intent(this, SummonerActivity.class);
        EditText summoner_name = (EditText) findViewById(R.id.summoner_name);
        String summonerName = summoner_name.getText().toString();

        // This will get the radiogroup
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radio_region);

        // This will get the radiobutton in the radiogroup that is checked
        RadioButton checkedRadioButton = (RadioButton) radiogroup.findViewById(radiogroup.getCheckedRadioButtonId());

        region = checkedRadioButton.getText().toString();

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup radiogroup, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton) radiogroup.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked)
                {
                    region = checkedRadioButton.getText().toString();
                }
            }
        });

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Summoner not found!");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        Summoner summoner = new Summoner(summonerName, region, MainActivity.this);

        if ((summoner.getName() != null) && !summoner.getName().isEmpty()) {
            intent.putExtra("region", region);
            intent.putExtra("summ", summoner);
            startActivity(intent);

        } else {
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
