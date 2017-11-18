package com.example.champ.project.Menu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.champ.project.Database.DatabaseHelper;
import com.example.champ.project.Models.PetService;
import com.example.champ.project.Utils.SortAgent;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceMenu {

    public static final String SORT_BY_NAME = "NAME";
    public static final String SORT_BY_DISTANCE = "DIST";
    public static final String SORT_BY_LIKES = "LIKES";
    public static final String SORT_BY_PRICE_RATE = "PRICE";

    private static final String TAG = ServiceMenu.class.getSimpleName();

    private static SQLiteDatabase db;
    private static DatabaseHelper mHelper;

    private static ServiceMenu mInstance;
    private static Context context;

    private static HashMap<Integer, PetService> list = new HashMap<>();

    public ServiceMenu(Context context) {
        this.context = context;
        loadFromDatabase();
    }

    public static ServiceMenu getInstance(Context context) {
        if (mInstance == null) mInstance = new ServiceMenu(context);
        return mInstance;
    }

    public static ArrayList<PetService> filter(ArrayList<PetService> source, String s) {
        if (s.length() == 0) return null;
        s = s.toLowerCase();
        ArrayList<PetService> out = new ArrayList<>();
        for (PetService p : source) {
            String name = p.getName().toLowerCase();
            if (name.contains(s)) out.add(p);
        }
        return out;
    }

    public PetService getById(int id) {
        return list.get(id);
    }

    public ArrayList<PetService> getHospitalList(String orderBy) {
        ArrayList<PetService> out = new ArrayList<>();
        for (int key : list.keySet()) {
            PetService p = list.get(key);
            if (p.isHospital()) out.add(p);
        }
        return sort(out, orderBy);
    }

    public ArrayList<PetService> getServiceList(String orderBy) {
        ArrayList<PetService> out = new ArrayList<>();
        for (int key : list.keySet()) {
            PetService p = list.get(key);
            if (!p.isHospital()) out.add(p);
        }
        return sort(out, orderBy);
    }

    public ArrayList<PetService> sort(ArrayList<PetService> out, String orderBy) {
        //Log.d("sort", "sort by "+orderBy);
        switch (orderBy) {
            case SORT_BY_NAME:
                return SortAgent.sortServiceByName(out);
            case SORT_BY_PRICE_RATE:
                return SortAgent.sortServiceByPriceRate(out);
            case SORT_BY_LIKES:
                return SortAgent.sortServiceByLikes(out);
            case SORT_BY_DISTANCE:
                return SortAgent.sortServiceByDistance(out);
            default:
                return out;
        }
    }

    /*
        public ArrayList<PetService> filterAnimal(String animal){
        }
    */
    private void loadFromDatabase() {
        mHelper = new DatabaseHelper(context);
        db = mHelper.getWritableDatabase();
        query();
    }

    private void query() {
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME_SERVICE, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            PetService p = cursorToObject(cursor);
            list.put(p.getId(), p);
        }
        cursor.close();
    }

    private PetService cursorToObject(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
        String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
        String pic = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PICTURE));
        String tel = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TEL));
        int priceRate = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_PRICE_RATE));
        int likes = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_LIKE));
        String dayOpen = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DAY_OPEN));
        String timeOpen = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TIME_OPEN));
        int hOpen = -1;
        int mOpen = -1;
        if (!timeOpen.equalsIgnoreCase("nope")) {
            hOpen = Integer.parseInt(timeOpen.substring(0, 2));
            mOpen = Integer.parseInt(timeOpen.substring(2));
        }
        String timeClose = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TIME_CLOSE));
        int hClose = -1;
        int mClose = -1;
        if (!timeOpen.equalsIgnoreCase("nope")) {
            hClose = Integer.parseInt(timeOpen.substring(0, 2));
            mClose = Integer.parseInt(timeOpen.substring(2));
        }
        String desc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DESC));
        double lat = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COL_LATITUDE));
        double lon = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COL_LONGITUDE));
        boolean isHospital = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_IS_HOSPITAL)) == 1;
        //String availableFor = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AVAILABLE_FOR));
        return new PetService(id, name, pic, tel, priceRate, likes, dayOpen, hOpen, mOpen, hClose, mClose, desc, lat, lon, isHospital);
    }

    @Override
    public String toString() {
        String s = "";
        for (int key : list.keySet()) {
            s += list.get(key) + "\n";
        }
        if (s.length() > 0) return s;
        return "error can't get data";
    }

}
