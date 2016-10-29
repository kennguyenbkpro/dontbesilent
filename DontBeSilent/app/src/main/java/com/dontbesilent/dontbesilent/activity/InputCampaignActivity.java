package com.dontbesilent.dontbesilent.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.dontbesilent.dontbesilent.R;

public class InputCampaignActivity extends AppCompatActivity {

    private EditText name, imgUrl, address, startTime, endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_campaign);
        name = (EditText) findViewById(R.id.name);
        imgUrl = (EditText) findViewById(R.id.imgUrl);
        address = (EditText) findViewById(R.id.address);
        startTime = (EditText) findViewById(R.id.startTime);
        endTime = (EditText) findViewById(R.id.endTime);
        name = (EditText) findViewById(R.id.name);
        name = (EditText) findViewById(R.id.name);
    }

}
