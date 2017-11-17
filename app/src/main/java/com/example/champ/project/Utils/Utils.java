package com.example.champ.project.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.champ.project.Database.DatabaseHelper;
import com.example.champ.project.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Utils {

    public static Drawable getDrawableFromAssets(Context context, String pictureFileName) {
        AssetManager am = context.getAssets();
        try {
            InputStream stream = am.open(pictureFileName);
            Drawable drawable = Drawable.createFromStream(stream, null);
            return drawable;
        } catch (IOException e) {
            e.printStackTrace();
            try {
                InputStream stream = new FileInputStream(pictureFileName);
                Drawable drawable = Drawable.createFromStream(stream, null);
                return drawable;
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

        }
        return null;
    }

    public static String calendarToStringHourAndMinute(Context context, Calendar dateTime) {
        String systemLanguage = Locale.getDefault().getLanguage();
        final String JAPANESE = "ja";
        final String THAI = "th";

        String hour = Integer.toString(dateTime.get(Calendar.HOUR_OF_DAY));
        int min = dateTime.get(Calendar.MINUTE);
        String minute = Integer.toString(min);
        if (min < 10) minute = "0" + minute;
        switch (systemLanguage) {
            case JAPANESE:
                hour += context.getString(R.string.hour_suffix) +
                        minute +
                        context.getString(R.string.minutes);
                break;
            case THAI:
                hour += "." +
                        minute +
                        context.getString(R.string.hour_suffix);
                break;
            default:
                hour += ":" + minute;
                break;
        }
        return hour;
    }

    public static boolean getLikeCondition(int serviceId, Context context) {
        DatabaseHelper mHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME_LIKES, null, null, null, null, null, null);
        ArrayList<Integer> likes = new ArrayList<>();
        String s = "";
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
            likes.add(id);
            s += id + ", ";
        }
        Log.d("query", s);
        return likes.contains(serviceId);
    }

    public static void like(int serviceId, Context context) {
        DatabaseHelper mHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        if (!getLikeCondition(serviceId, context)) {
            ContentValues cv = new ContentValues();
            cv.put(DatabaseHelper.COL_ID, serviceId);
            db.insert(DatabaseHelper.TABLE_NAME_LIKES, null, cv);
        }
    }

    public static void unLike(int serviceId, Context context) {
        DatabaseHelper mHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_NAME_LIKES, DatabaseHelper.COL_ID + " = " + serviceId, null);
    }

    public static void setLanguage(Context context) {
        setLanguage(context, context.getString(R.string.default_language_code));
    }

    public static void setLanguage(Context context, String language) {
        Configuration config = new Configuration();
        config.setLocale(new Locale(language));
        context.getResources().updateConfiguration(config, null);
    }

}
