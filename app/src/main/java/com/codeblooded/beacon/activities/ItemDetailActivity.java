package com.codeblooded.beacon.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.codeblooded.beacon.R;
import com.codeblooded.beacon.model.Item;

/**
 * Created by tejas on 7/26/17.
 */

public class ItemDetailActivity extends AppCompatActivity{

    TextView sampleTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sampleTextView = (TextView)findViewById(R.id.sampleTextView);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            Item item = bundle.getParcelable("item");
            if(item!=null) {
                sampleTextView.setText(item.getName());
            }
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return true;
    }
}
