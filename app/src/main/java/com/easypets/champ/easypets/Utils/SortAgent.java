package com.easypets.champ.easypets.Utils;

import android.support.annotation.NonNull;

import com.easypets.champ.easypets.Models.PetService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class SortAgent {

    private static final Comparator<PetService> name = new Comparator<PetService>() {
        @Override
        public int compare(PetService o1, PetService o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };
    private static final Comparator<PetService> priceRate = new Comparator<PetService>() {
        @Override
        public int compare(PetService o1, PetService o2) {
            return o1.getPriceRate() - o2.getPriceRate();
        }
    };
    private static final Comparator<PetService> likes = new Comparator<PetService>() {
        @Override
        public int compare(PetService o1, PetService o2) {
            return o2.getLikes() - o1.getLikes();
        }
    };
    private static Comparator<PetService> distance = new Comparator<PetService>() {
        @Override
        public int compare(PetService o1, PetService o2) {
            Float d1 = o1.getDistance();
            Float d2 = o2.getDistance();
            return d1.compareTo(d2);
        }
    };

    public static ArrayList<PetService> sortServiceByName(HashMap<Integer, PetService> hash) {
        ArrayList<PetService> list = toArrayList(hash);
        return sortServiceByName(list);
    }

    public static ArrayList<PetService> sortServiceByName(ArrayList<PetService> list) {
        Collections.sort(list, name);
        return list;
    }

    public static ArrayList<PetService> sortServiceByDistance(HashMap<Integer, PetService> hash) {
        ArrayList<PetService> list = toArrayList(hash);
        return sortServiceByDistance(list);
    }

    public static ArrayList<PetService> sortServiceByDistance(ArrayList<PetService> list) {
        Collections.sort(list, name);
        Collections.sort(list, distance);
        return list;
    }

    public static ArrayList<PetService> sortServiceByPriceRate(HashMap<Integer, PetService> hash) {
        ArrayList<PetService> list = toArrayList(hash);
        return sortServiceByPriceRate(list);
    }

    public static ArrayList<PetService> sortServiceByPriceRate(ArrayList<PetService> list) {
        Collections.sort(list, name);
        Collections.sort(list, priceRate);
        return list;
    }

    public static ArrayList<PetService> sortServiceByLikes(HashMap<Integer, PetService> hash) {
        ArrayList<PetService> list = toArrayList(hash);
        return sortServiceByLikes(list);
    }

    public static ArrayList<PetService> sortServiceByLikes(ArrayList<PetService> list) {
        Collections.sort(list, name);
        Collections.sort(list, likes);
        return list;
    }

    @NonNull
    private static ArrayList<PetService> toArrayList(HashMap<Integer, PetService> hash) {
        Collection<PetService> x = hash.values();
        return new ArrayList<>(x);
    }

}