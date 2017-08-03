package com.codeblooded.beacon.activities.tempactivites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.codeblooded.beacon.R;

/**
 * Created by tejas on 7/28/17.
 */

public class SearchActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        progressDialog = new ProgressDialog(SearchActivity.this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressDialog.setMessage("Searching...");
        progressDialog.show();
        new Handler().postDelayed(
                new Runnable(){
                    @Override
                    public void run() {
                        progressDialog.cancel();
                        startActivity(new Intent(SearchActivity.this, ItemDetailActivity.class));
                        overridePendingTransition(R.anim.silde_in_up,R.anim.do_nothing);
                    }
                },2000
        );
    }
}
