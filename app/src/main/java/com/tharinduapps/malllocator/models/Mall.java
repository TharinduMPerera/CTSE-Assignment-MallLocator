package com.tharinduapps.malllocator.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tharindu on 3/23/18.
 */

public class Mall implements Parcelable {
    private int id;
    private String name;
    private String city;
    private String telephone;
    private String image;
    private float rate;
    private double lat;
    private double lon;

    public Mall() {
    }

    public Mall(int id, String name, String city, String telephone, String image, float rate, double lat, double lon) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.telephone = telephone;
        this.image = image;
        this.rate = rate;
        this.lat = lat;
        this.lon = lon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.city);
        dest.writeString(this.telephone);
        dest.writeString(this.image);
        dest.writeFloat(this.rate);
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lon);
    }

    protected Mall(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.city = in.readString();
        this.telephone = in.readString();
        this.image = in.readString();
        this.rate = in.readFloat();
        this.lat = in.readDouble();
        this.lon = in.readDouble();
    }

    public static final Parcelable.Creator<Mall> CREATOR = new Parcelable.Creator<Mall>() {
        @Override
        public Mall createFromParcel(Parcel source) {
            return new Mall(source);
        }

        @Override
        public Mall[] newArray(int size) {
            return new Mall[size];
        }
    };
}
