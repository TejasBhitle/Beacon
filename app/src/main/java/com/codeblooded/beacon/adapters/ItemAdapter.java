package com.codeblooded.beacon.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.codeblooded.beacon.R;
import com.codeblooded.beacon.model.Item;

import java.util.ArrayList;

/**
 * Created by tejas on 7/26/17.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private ArrayList<Item> items;
    private Context context;
    int lastPosition = -1;

    public ItemAdapter(ArrayList<Item> items,Context context){
        this.items =items;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView image;
        ViewHolder(View view){
            super(view);
            name = (TextView)view.findViewById(R.id.item_name);
            image = (ImageView)view.findViewById(R.id.image_view);
        }
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.name.setText(item.getName());
        TextDrawable drawable = TextDrawable.builder()
                .buildRoundRect(item.getName().substring(0,1), ColorGenerator.MATERIAL.getRandomColor(),10);
        holder.image.setImageDrawable(drawable);

        if(position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.up_from_bottom);
            holder.itemView.startAnimation(animation);
            lastPosition= position;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
