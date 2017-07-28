package com.codeblooded.beacon.activities.tempactivites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.codeblooded.beacon.R;

/**
 * Created by tejas on 7/29/17.
 */

public class ItemFullDetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_full_detail);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_close_white_24dp));


    }
}
