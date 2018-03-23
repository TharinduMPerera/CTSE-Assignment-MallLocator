package com.tharinduapps.malllocator.models;

/**
 * Created by tharindu on 3/23/18.
 */

public class Mall {
    private int id;
    private String name;
    private String city;
    private String telephone;
    private float rate;
    private double lat;
    private double lon;

    public Mall() {
    }

    public Mall(int id, String name, String city, String telephone, float rate, double lat, double lon) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.telephone = telephone;
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
}
