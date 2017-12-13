package com.easypets.champ.easypets.Models;

import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.easypets.champ.easypets.Utils.Utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PetService implements Serializable, Comparable<PetService> {

    public static final String FIELD_SERVICE_ID = "serviceId";
    public static final String FIELD_KEY = "key";
    public static final String FIELD_SERVICE_NAME = "serviceName";
    public static final String FIELD_BANNER = "banner";
    public static final String FIELD_LOGO = "logo";
    public static final String FIELD_TEL = "tel";
    public static final String FIELD_PRICE_RATE = "priceRate";
    public static final String FIELD_LIKE = "likes";
    public static final String FIELD_OPEN_DAYS = "openDays";
    public static final String FIELD_OPEN_TIME = "openTime";
    public static final String FIELD_CLOSE_TIME = "closeTime";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_LATITUDE = "latitude";
    public static final String FIELD_LONGITUDE = "longitude";
    public static final String FIELD_TYPE = "type";

    private String idString;
    private int id;
    private String name;
    private String picturePath;
    private String logoPath;
    private String telNo;
    private int priceRate;
    private int likes;
    private String dayOpenString;
    private boolean[] dayOpen; //true at [0] for everyday open, 1 for Sunday, 2 for Tuesday etc.
    private String timeOpen;
    private String timeClose;
    private String description;
    private double latitude;
    private double longitude;
    private boolean isHospital;

    public PetService(){}

    //Firebase Constructor
    public PetService(String key, int serviceId, String serviceName, String banner, String logo, String tel,
                      int priceRate, String likes, String openDays, String openTime,
                      String closeTime, String description, double latitude, double longitude,
                      boolean isHospital) {
        this.idString = key;
        this.id = serviceId;
        this.name = serviceName;
        this.picturePath = banner;
        this.logoPath = logo;
        this.telNo = tel;
        this.priceRate = priceRate;
        this.likes = Integer.parseInt(likes);
        dayOpenString = openDays;
        setDayOpen(openDays);
        setTimeOpen(openTime);
        setTimeClose(closeTime);
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isHospital = isHospital;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put(FIELD_SERVICE_ID, id); result.put(FIELD_KEY, idString); result.put(FIELD_SERVICE_NAME, name);
        result.put(FIELD_BANNER, picturePath); result.put(FIELD_TEL, telNo); result.put(FIELD_PRICE_RATE, priceRate);
        result.put(FIELD_LIKE, likes); result.put(FIELD_OPEN_DAYS, dayOpenString); result.put(FIELD_OPEN_TIME, timeOpen);
        result.put(FIELD_CLOSE_TIME, timeClose); result.put(FIELD_DESCRIPTION, description); result.put(FIELD_LATITUDE, latitude);
        result.put(FIELD_LONGITUDE, longitude); result.put(FIELD_TYPE, isHospital);
        return result;
    }


    public PetService(int id, String name, String picturePath, String telNo, int priceRate, int likes, String dayOpen, String timeOpen, String timeClose, String description, double latitude, double longitude, boolean isHospital) {
        this.id = id;
        this.name = name;
        this.picturePath = picturePath;
        this.telNo = telNo;
        this.priceRate = priceRate;
        this.likes = likes;
        setDayOpen(dayOpen);
        this.timeOpen = timeOpen;
        this.timeClose = timeClose;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isHospital = isHospital;
    }

    @NonNull
    public Float getDistance() {
        Location location = getLocation();
        Location here = Utils.getCurrentLocation();
        Log.d("Distance of " + name, location.distanceTo(here) + "");
        return location.distanceTo(here);
    }

    @NonNull
    public Float getDistanceInKm() {
        float d = (float) (((int) (getDistance() / 10)) / 100.0);
        return d;
    }

    @NonNull
    public Float getDistanceInMetres() {
        float d = (float) (((int) (getDistance() * 100)) / 100.0);
        return d;
    }

    public Location getLocation() {
        Location location = new Location(LocationManager.GPS_PROVIDER);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        return location;
    }


    @Override
    public String toString() {
        return "#" + id + " " + name + ": likes " + likes + " : price " + getPriceRateString() + " : location " + getCoordinates() + " : " + getDistance() + " m from here";
    }

    @Override
    public int compareTo(PetService o) {
        return this.name.compareTo(o.getName());
    }

    //getters and setters

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public String getKey() {
        return idString;
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

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
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

    public void like() {
        likes++;
    }

    public void unLike() {
        likes--;
    }

    public boolean[] getDayOpen() {
        return dayOpen;
    }

    public String getDayOpenString() {
        StringBuilder s = new StringBuilder();
        for (boolean b : dayOpen) {
            if (b) s.append("1");
            else s.append("2");
        }
        return s.toString();
    }

    public void setDayOpen(String dayOpen) {
        this.dayOpen = new boolean[8];
        if (dayOpen.charAt(0) == '1') {
            this.dayOpen[0] = true;
        } else if (dayOpen.length() == 8) {
            for (int i = 0; i < dayOpen.length(); ++i) {
                this.dayOpen[i] = dayOpen.charAt(i) == '1';
            }
        }
    }

    public String getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(String timeOpen) {
        if(timeOpen.equals("nope")) timeOpen = null;
        this.timeOpen = timeOpen;
    }

    public String getTimeClose() {
        return timeClose;
    }

    public void setTimeClose(String timeClose) {
        if(timeClose.equals("nope")) timeClose = null;
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

    public boolean isHospital() {
        return isHospital;
    }

    public void setHospital(boolean hospital) {
        isHospital = hospital;
    }

}
