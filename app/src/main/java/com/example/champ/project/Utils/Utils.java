package com.example.champ.project.Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.champ.project.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

    public static void setLanguage(Context context) {
        setLanguage(context, context.getString(R.string.default_language_code));
    }

    public static void setLanguage(Context context, String language) {
        Configuration config = new Configuration();
        config.setLocale(new Locale(language));
        context.getResources().updateConfiguration(config, null);
    }

}
