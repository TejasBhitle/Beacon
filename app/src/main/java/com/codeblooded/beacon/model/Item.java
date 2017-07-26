package com.codeblooded.beacon.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tejas on 7/26/17.
 */

public class Item implements Parcelable {

    private String name,description,type;

    public Item(String name,String type) {
        this.name = name;
        this.type = type;
    }

    /*public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }*/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.type);
    }

    protected Item(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
