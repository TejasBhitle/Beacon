package com.codeblooded.beacon.activities;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.codeblooded.beacon.R;
import com.codeblooded.beacon.activities.tempactivites.ItemFullDetailActivity;
import com.codeblooded.beacon.activities.tempactivites.SearchActivity;

/**
 * Created by tejas on 7/26/17.
 */

public class ItemDetailActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onStart() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide(Gravity.BOTTOM);

            slide.addTarget(R.id.card1);
            slide.addTarget(R.id.card2);
            slide.setInterpolator(new LinearOutSlowInInterpolator());
            getWindow().setEnterTransition(slide);
            getWindow().setExitTransition(slide);
            getWindow().setReenterTransition(slide);

        }
        super.onStart();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void show(View view,long delay) {
        // previously invisible view
        int centerX = (view.getLeft() + view.getRight()) / 2;
        int centerY = (view.getTop() + view.getBottom()) / 2;

        int startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        anim.setDuration(delay);
        view.setVisibility(View.VISIBLE);
        anim.start();
    }

    //TextView sampleTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_close_white_24dp));


        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(ItemDetailActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                new Handler().postDelayed(
                        new Runnable(){
                            @Override
                            public void run() {
                                progressDialog.cancel();
                                Intent intent = new Intent(ItemDetailActivity.this, ItemFullDetailActivity.class);
                                Bundle bundle =new Bundle();
                                bundle.putInt("key",1);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        },2000
                );

            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(ItemDetailActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                new Handler().postDelayed(
                        new Runnable(){
                            @Override
                            public void run() {
                                progressDialog.cancel();
                                Intent intent = new Intent(ItemDetailActivity.this, ItemFullDetailActivity.class);
                                Bundle bundle =new Bundle();
                                bundle.putInt("key",2);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        },2000
                );

            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(ItemDetailActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                new Handler().postDelayed(
                        new Runnable(){
                            @Override
                            public void run() {
                                progressDialog.cancel();
                                Intent intent = new Intent(ItemDetailActivity.this, ItemFullDetailActivity.class);
                                Bundle bundle =new Bundle();
                                bundle.putInt("key",3);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        },2000
                );

            }
        });
        transition();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void transition(){
        new Handler().postDelayed(
                new Runnable(){
                    @Override
                    public void run() {
                        show(findViewById(R.id.content1),750);
                        show(findViewById(R.id.content2),1000);
                        show(findViewById(R.id.content3),1250);
                    }
                },500
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return true;
    }
}
