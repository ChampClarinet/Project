package com.example.champ.project.Menu;

import android.content.Context;
import android.util.Log;

import com.example.champ.project.Models.Store;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceMenu {

    private static final String TAG = ServiceMenu.class.getSimpleName();

    private static ServiceMenu instance;
    private static ArrayList<Store> serviceList = new ArrayList<>();
    private static HashMap<Integer, Store> serviceHash = new HashMap<>();

    private Context context;

    public static ServiceMenu getInstance(Context context) {
        if (instance == null) instance = new ServiceMenu(context);
        return instance;
    }

    private ServiceMenu(Context context) {
        this.context = context;
        loadFromDatabase();
    }

    public void loadFromDatabase() {
        serviceList.clear();
        serviceList.add(new Store(1, "ABC Pet Shop", null, 3, 42, 13.7061402, 100.6221974));
        serviceList.add(new Store(2, "RotFai Dog", null, 1, 347, 13.7061402, 100.6221974));
        serviceList.add(new Store(3, "Navamin Cat", null, 2, 214, 13.8287249, 100.5567241));
        serviceList.add(new Store(4, "Blah Blah Blah Bird", null, 1, 578, 13.7867163, 100.4444575));
        for (Store s : serviceList) serviceHash.put(s.getId(), s);
    }

    public Store findServiceById(int id) {
        return serviceHash.get(id);
    }

    public ArrayList<Store> getServiceList() {
        return serviceList;
    }

    public ArrayList<Store> getFilteredServiceList(String query) {
        query = query.toLowerCase();
        ArrayList<Store> out = new ArrayList<>();
        for (Store s : serviceList) {
            String name = s.getName().toLowerCase();
            if (name.contains(query)) out.add(s);
        }
        return out;
    }

}
