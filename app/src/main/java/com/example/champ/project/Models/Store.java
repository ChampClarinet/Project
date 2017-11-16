package com.example.champ.project.Models;

import java.io.Serializable;
import java.util.Calendar;

public class Store implements Serializable, Comparable<Store> {

    private final int id;
    private String name;
    private String picturePath;
    private String telNo;
    private int priceRate;
    private int likes;
    boolean[] dayOpen; //true at [0] for everyday open, 1 for Sunday, 2 for Tuesday etc.
    Calendar timeOpen;
    Calendar timeClose;
    private String description;
    private double latitude;
    private double longitude;

    public Store(int id, String name, String picturePath, int priceRate, int likes, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.picturePath = picturePath;
        this.priceRate = priceRate;
        this.likes = likes;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Store(int id, String name, String picturePath, String telNo, int priceRate, int likes, boolean[] dayOpen, int hourOpen, int minuteOpen
            , int hourClose, int minuteClose, String description, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.picturePath = picturePath;
        this.telNo = telNo;
        this.priceRate = priceRate;
        this.likes = likes;
        this.dayOpen = dayOpen.clone();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.HOUR_OF_DAY, hourOpen);
        calendar.set(Calendar.MINUTE, minuteOpen);
        this.timeOpen = calendar;
        calendar.clear();
        calendar.set(Calendar.HOUR_OF_DAY, hourClose);
        calendar.set(Calendar.MINUTE, minuteClose);
        this.timeClose = calendar;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public int getPriceRate() {
        return priceRate;
    }

    public String getPriceRateString() {
        switch (priceRate) {
            case 1:
                return "$";
            case 2:
                return "$$";
            case 3:
                return "$$$";
            default:
                return null;
        }
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

    public boolean[] getDayOpen() {
        return dayOpen;
    }

    public void setDayOpen(boolean[] dayOpen) {
        this.dayOpen = dayOpen;
    }

    public Calendar getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(Calendar timeOpen) {
        this.timeOpen = timeOpen;
    }

    public Calendar getTimeClose() {
        return timeClose;
    }

    public void setTimeClose(Calendar timeClose) {
        this.timeClose = timeClose;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCoordinates() {
        return latitude + ", " + longitude;
    }

    @Override
    public String toString() {
        return name + ": likes " + likes + " : price " + getPriceRateString() + " : location " + getCoordinates();
    }

    @Override
    public int compareTo(Store o) {
        return this.name.compareTo(o.getName());
    }

}
