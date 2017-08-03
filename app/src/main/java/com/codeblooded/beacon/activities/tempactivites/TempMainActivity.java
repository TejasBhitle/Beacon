package com.codeblooded.beacon.activities.tempactivites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.codeblooded.beacon.adapters.ItemAdapter;
import com.codeblooded.beacon.R;
import com.codeblooded.beacon.model.Item;
import com.codeblooded.beacon.util.RecyclerItemClickListener;

import java.util.ArrayList;

public class TempMainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton expandButton;
    LinearLayout expandView;
    EditText filterTextView;
    AppCompatCheckBox allCheckBox,fashionCheckBox;
    RecyclerView recyclerView;
    ArrayList<Item> items,items1;
    ProgressDialog progressDialog;

    private void init(){
        items = new ArrayList<>();
        items.add(new Item("Nike","Fashion"));
        items.add(new Item("Samsung","Electronics"));
        items.add(new Item("Rayban","Fashion"));

        items1 = new ArrayList<>();
        items1.add(new Item("Nike","Fashion"));
        items1.add(new Item("Rayban","Fashion"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);


        init();

        expandView = (LinearLayout)findViewById(R.id.expandView);
        expandView.setVisibility(View.GONE);

        filterTextView = (EditText)findViewById(R.id.filterTextView);
        filterTextView.setFocusable(false);

        allCheckBox = (AppCompatCheckBox)findViewById(R.id.checkbox_all);
        fashionCheckBox =(AppCompatCheckBox)findViewById(R.id.checkbox_fashion);

        fashionCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    allCheckBox.setChecked(false);
                    filterTextView.setText("Fashion");
                    //expandFilter();
                    setRecyclerView(items1);
                }
                else setRecyclerView(items);
            }
        });

        allCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    setRecyclerView(items);
                    fashionCheckBox.setChecked(false);
                    filterTextView.setText("None");
                }
            }
        });

        expandButton = (ImageButton)findViewById(R.id.expandButton);
        expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandFilter();
            }
        });

        progressDialog = new ProgressDialog(TempMainActivity.this);
        progressDialog.setMessage("Searching...");
        progressDialog.show();
        new Handler().postDelayed(
                new Runnable(){
                    @Override
                    public void run() {
                        progressDialog.cancel();
                        setRecyclerView(items);
                    }
                },2000
        );

    }

    private void setRecyclerView(final ArrayList<Item> itemArrayList){
        recyclerView.setLayoutManager(new LinearLayoutManager(TempMainActivity.this));
        recyclerView.setAdapter(new ItemAdapter(itemArrayList,TempMainActivity.this));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(TempMainActivity.this,
                new RecyclerItemClickListener.OnClickItemInterface() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("item",itemArrayList.get(position));
                Intent i = new Intent(TempMainActivity.this,ItemDetailActivity.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        }));
    }

    private void expandFilter(){
        if(expandView.getVisibility() == View.GONE) {
            expandView.setVisibility(View.VISIBLE);
            expandButton.setImageDrawable(getResources().getDrawable(R.drawable.svg_expand_less_black_24dp));
        }
        else {
            expandButton.setImageDrawable(getResources().getDrawable(R.drawable.svg_expand_more_black_24dp));
            expandView.setVisibility(View.GONE);
        }
    }

}
