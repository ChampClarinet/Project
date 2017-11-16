package com.example.champ.project.Utils;

import android.support.annotation.NonNull;

import com.example.champ.project.Models.Store;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class SortAgent {

    private static final Comparator<Store> name = new Comparator<Store>() {
        @Override
        public int compare(Store o1, Store o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };
    private static final Comparator<Store> priceRate = new Comparator<Store>() {
        @Override
        public int compare(Store o1, Store o2) {
            return o1.getPriceRate() - o2.getPriceRate();
        }
    };
    private static final Comparator<Store> likes = new Comparator<Store>() {
        @Override
        public int compare(Store o1, Store o2) {
            return o1.getLikes() - o2.getLikes();
        }
    };
    /*private static Comparator<Store> location = new Comparator<Store>() {
        @Override
        public int compare(Store o1, Store o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };*/

    public static ArrayList<Store> sortServiceByName(HashMap<Integer, Store> hash) {
        ArrayList<Store> list = toArrayList(hash);
        Collections.sort(list, name);
        return list;
    }

    /*
    public static ArrayList<Store> sortServiceByDistance(HashMap<Integer, Store> hash) {
        ArrayList list = toArrayList(hash);
        list.sort(location);
        return list;
    }
     */
    public static ArrayList<Store> sortServiceByPriceRate(HashMap<Integer, Store> hash) {
        ArrayList<Store> list = toArrayList(hash);
        Collections.sort(list, name);
        Collections.sort(list, priceRate);
        return list;
    }

    public static ArrayList<Store> sortServiceByLikes(HashMap<Integer, Store> hash) {
        ArrayList<Store> list = toArrayList(hash);
        Collections.sort(list, name);
        Collections.sort(list, likes);
        return list;
    }

    @NonNull
    private static ArrayList<Store> toArrayList(HashMap<Integer, Store> hash) {
        Collection<Store> x = hash.values();
        return new ArrayList<>(x);
    }

}