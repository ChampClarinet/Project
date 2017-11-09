package com.example.champ.project.Models;

import java.io.Serializable;

public class Store implements Serializable{

    private final int id;
    private String name;
    private String picturePath;
    private int priceRate;
    private int likes;
    private double latitude;
    private double longtitude;

    public Store(int id, String name, String picturePath, int priceRate, int likes, double latitude, double longtitude) {
        this.id = id;
        this.name = name;
        this.picturePath = picturePath;
        this.priceRate = priceRate;
        this.likes = likes;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public int getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(int priceRate) {
        this.priceRate = priceRate;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

}
