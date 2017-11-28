package com.example.champ.project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    private static final String DATABASENAME = "WHAT_TO_EAT";
    private static final int DATABASEVERSION = 1;

    public static final String TABLE_NAME_SERVICE = "SERVICE";
    public static final String TABLE_NAME_LIKES = "LIKES";
    public static final String COL_ID = "_ID";
    public static final String COL_NAME = "name";
    public static final String COL_PICTURE = "picture";
    public static final String COL_TEL = "telNo";
    public static final String COL_PRICE_RATE = "priceRate";
    public static final String COL_LIKE = "like";
    public static final String COL_DAY_OPEN = "dayOpen";
    public static final String COL_TIME_OPEN = "timeOpen";
    public static final String COL_TIME_CLOSE = "timeClose";
    public static final String COL_DESC = "description";
    public static final String COL_LATITUDE = "latitude";
    public static final String COL_LONGITUDE = "longitude";
    public static final String COL_IS_HOSPITAL = "isHospital";
    public static final String COL_AVAILABLE_FOR = "availableFor";

    private static final String SQL_CREATE_TABLE_SERVICE = "CREATE TABLE " + TABLE_NAME_SERVICE + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_NAME + " TEXT," +
            COL_PICTURE + " TEXT," +
            COL_TEL + " TEXT," +
            COL_PRICE_RATE + " INTEGER," +
            COL_LIKE + " INTEGER," +
            COL_DAY_OPEN + " TEXT," +
            COL_TIME_OPEN + " TEXT," +
            COL_TIME_CLOSE + " TEXT," +
            COL_DESC + " TEXT," +
            COL_LATITUDE + " INTEGER," +
            COL_LONGITUDE + " INTEGER," +
            COL_IS_HOSPITAL + " INTEGER," +
            COL_AVAILABLE_FOR + " TEXT" +
            ")";

    private static final String SQL_CREATE_TABLE_LIKES = "CREATE TABLE " + TABLE_NAME_LIKES + "(" +
            COL_ID + " INTEGER" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_SERVICE);
        db.execSQL(SQL_CREATE_TABLE_LIKES);
        dummyData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void dummyData(SQLiteDatabase db) {
        insertToServiceTable(db, createContentValue("ABC Pet Shop", "scenery.jpg", "0819543213", 2, 27, "10000000", "1000", "2000", "ABC PET", 12.8069134, 99.98692430000006, 0, null));
        insertToServiceTable(db, createContentValue("Narita Pet", "scenery.jpg", "0945631210", 1, 907, "10000000", "0800", "1900", "いらっしゃいませ！", 35.7731357303353, 140.38718527456058, 0, null));
        insertToServiceTable(db, createContentValue("Mamba Pet", "scenery.jpg", "0841234812", 1, 218, "00011101", "0900", "1830", "Ovuvuevuevue enyetuenwuevue ugbemugbem osas", -33.9604102, 18.401253699999984, 0, null));
        insertToServiceTable(db, createContentValue("Champ Pet", "scenery.jpg", "0942145389", 3, 80, "00011111", "0700", "2100", "I am handsome", 13.7124214, 100.52708480000001, 0, null));
        insertToServiceTable(db, createContentValue("Vet Tom", "scenery.jpg", "0541234751", 2, 157, "01110110", "nope", "nope", "Hello World", 13.8142661, 100.03615059999993, 1, null));
        insertToServiceTable(db, createContentValue("Thong Lor Vet", "scenery.jpg", "0818998045", 3, 935, "00100101", "0900", "1830", "ansdasfkmga", 13.731572, 100.58134519999999, 1, null));
        insertToServiceTable(db, createContentValue("Sherlock Vet", "scenery.jpg", "0818096600", 3, 171, "10000000", "0600", "2000", "真実はいつも一つ！", 51.523767, -0.15855569999996533, 1, null));
        insertToServiceTable(db, createContentValue("Kanchanaburi Vet", "scenery.jpg", "0412574697", 1, 948, "00010110", "1030", "1800", "Vacation Time", 14.0409549, 99.50374599999998, 1, null));

        //likes
        ContentValues cv = new ContentValues();
        cv.put(COL_ID, 2);
        db.insert(TABLE_NAME_LIKES, null, cv);
        cv = new ContentValues();
        cv.put(COL_ID, 3);
        db.insert(TABLE_NAME_LIKES, null, cv);
        cv = new ContentValues();
        cv.put(COL_ID, 4);
        db.insert(TABLE_NAME_LIKES, null, cv);
        cv = new ContentValues();
        cv.put(COL_ID, 7);
        db.insert(TABLE_NAME_LIKES, null, cv);
        cv = new ContentValues();
        cv.put(COL_ID, 8);
        db.insert(TABLE_NAME_LIKES, null, cv);
    }

    private void insertToServiceTable(SQLiteDatabase db, ContentValues contentValue) {
        Log.d("Database", "inserting " + contentValue.getAsString(COL_NAME));
        long a = db.insert(TABLE_NAME_SERVICE, null, contentValue);
        Log.d("Database", "success = " + a);
    }

    private ContentValues createContentValue(String name, String picurePath, String telNo, int priceRate, int likes, String dayOpen, String timeOpen, String timeClose, String description, double latitude, double logitude, int isServiceHospital, String availableFor) {
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, name);
        cv.put(COL_PICTURE, picurePath);
        cv.put(COL_TEL, telNo);
        cv.put(COL_PRICE_RATE, priceRate);
        cv.put(COL_LIKE, likes);
        cv.put(COL_DAY_OPEN, dayOpen);
        cv.put(COL_TIME_OPEN, timeOpen);
        cv.put(COL_TIME_CLOSE, timeClose);
        cv.put(COL_DESC, description);
        cv.put(COL_LATITUDE, latitude);
        cv.put(COL_LONGITUDE, logitude);
        cv.put(COL_IS_HOSPITAL, isServiceHospital);
        //cv.put(COL_AVAILABLE_FOR, availableFor);
        return cv;
    }


}
