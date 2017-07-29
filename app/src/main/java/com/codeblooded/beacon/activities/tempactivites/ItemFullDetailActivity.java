package com.codeblooded.beacon.activities.tempactivites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

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

        TextView name = (TextView)findViewById(R.id.name);
        TextView discount = (TextView)findViewById(R.id.discount);
        TextView footer = (TextView)findViewById(R.id.footer);
        ImageView imageView = (ImageView)findViewById(R.id.image);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            switch (bundle.getInt("key")){
                case 1:
                    name.setText("Super Elite Running Shoes");
                    discount.setText("₹1,745     25% OFF\nSpecial Discount Ends in few hours");
                    footer.setText("Colour: Blue\nMaterial:- Mesh");
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.rebook));
                    break;
                case 2:
                    name.setText("Court Sneakers");
                    discount.setText("₹1,348     10% OFF\nSpecial Discount Ends in few hours");
                    footer.setText("Colour: Navy, Blue, White\nMaterial:- Canvas");
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.reebok2));
                    break;
                case 3:
                    name.setText("Core Flip Slippers");
                    discount.setText("₹848     35% OFF\nSpecial Discount Ends in few hours");
                    footer.setText("Grey Color\nType:- Slippers");
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.reebok3));
                    break;
                default://nothing
            }
        }

    }
}
