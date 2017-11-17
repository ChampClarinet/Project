package com.example.champ.project.Menu;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.champ.project.Models.Store;
import com.example.champ.project.Utils.SortAgent;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceMenu {

    private static final String TAG = ServiceMenu.class.getSimpleName();
    public static final String SORT_BY_NAME = "NAME";
    public static final String SORT_BY_LOCATION = "LOCATION";
    public static final String SORT_BY_LIKES = "LIKES";
    public static final String SORT_BY_PRICE_RATE = "PRICE";

    private static ServiceMenu instance;
    private static ArrayList<Store> listById = new ArrayList<>();
    private static ArrayList<Store> listByName = new ArrayList<>();
    private static ArrayList<Store> listByPriceRate = new ArrayList<>();
    private static ArrayList<Store> listByLikes = new ArrayList<>();
    private static ArrayList<Store> listByLocation = new ArrayList<>();
    private static HashMap<Integer, Store> serviceHash = new HashMap<>();

    private Context context;

    public static ServiceMenu getInstance(Context context) {
        if (instance == null) instance = new ServiceMenu(context);
        instance.loadFromDatabase();
        return instance;
    }

    private ServiceMenu(Context context) {
        this.context = context;
    }

    public void loadFromDatabase() {
        listById.clear();
        listById.add(new Store(1, "ABC Pet Shop", null, 3, 42, 13.7124214, 100.52708480000001));
        listById.add(new Store(2, "RotFai Dog", null, 1, 347, 13.7124214, 100.52708480000001));
        listById.add(new Store(3, "Navamin Cat", null, 2, 214, 13.8263031, 100.67894260000003));
        listById.add(new Store(4, "Blah Blah Blah Bird", null, 1, 578, 13.7574965, 100.4438738));
        for (Store s : listById) serviceHash.put(s.getId(), s);
        listByName = SortAgent.sortServiceByName(serviceHash);
        listByPriceRate = SortAgent.sortServiceByPriceRate(serviceHash);
        listByLikes = SortAgent.sortServiceByLikes(serviceHash);
        listByLocation = SortAgent.sortServiceByDistance(serviceHash);
    }

    public Store findServiceById(int id) {
        return serviceHash.get(id);
    }

    public ArrayList<Store> getServiceList(String sortBy) {
        switch (sortBy){
            case SORT_BY_NAME: return listByName;
            case SORT_BY_LIKES: return listByLikes;
            case SORT_BY_LOCATION: return listByLocation;
            case SORT_BY_PRICE_RATE: return listByPriceRate;
            default: return listById;
        }
    }

    public ArrayList<Store> getFilteredServiceList(@Nullable String query) {
        query = query.toLowerCase();
        ArrayList<Store> out = new ArrayList<>();
        for (Store s : listById) {
            String name = s.getName().toLowerCase();
            if (name.contains(query)) out.add(s);
        }
        String s = "";
        for (Store store : out) {
            s += store.getName() + "\n";
        }
        Log.d("list", s);
        return out;
    }

    public void checkList() {
        System.out.println("by Name");
        for (Store s : listByName) {
            System.out.println(s);
        }
        System.out.println();
        System.out.println("by Price rate");
        for (Store s : listByPriceRate) {
            System.out.println(s);
        }
        System.out.println();
        System.out.println("by Likes");
        for (Store s : listByLikes) {
            System.out.println(s);
        }
    }

    @Override
    public String toString() {
        return ServiceMenu.class.getSimpleName() + ": Service quantity " + getServiceList(null).size();
    }


}
