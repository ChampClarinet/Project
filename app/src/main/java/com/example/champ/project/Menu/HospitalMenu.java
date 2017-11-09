package com.example.champ.project.Menu;

import android.content.Context;

import com.example.champ.project.Models.Store;

import java.util.ArrayList;
import java.util.HashMap;

public class HospitalMenu {

    private static final String TAG = HospitalMenu.class.getSimpleName();

    private static HospitalMenu instance;
    private static ArrayList<Store> hospitalList = new ArrayList<>();
    private static HashMap<Integer, Store> hospitalHash = new HashMap<>();

    private Context context;

    public static HospitalMenu getInstance(Context context) {
        if (instance == null) instance = new HospitalMenu(context);
        return instance;
    }

    private HospitalMenu(Context context) {
        this.context = context;
        loadFromDatabase();
    }

    private void loadFromDatabase() {
        hospitalList.clear();
        hospitalList.add(new Store(1, "ABC Hospital", null, 2, 20, 12.7960376, 99.980891));
        hospitalList.add(new Store(2, "Narita Hospital", null, 3, 159, 12.7960376, 99.980891));
        hospitalList.add(new Store(3, "Bangkok Hospital", null, 1, 246, 13.7243647, 100.53877));
        hospitalList.add(new Store(4, "Blah Blah Blah Hospital", null, 2, 177, 13.7061402, 100.6221974));
        for (Store s : hospitalList) hospitalHash.put(s.getId(), s);
    }

    public Store findHospitalById(int id) {
        return hospitalHash.get(id);
    }

    public ArrayList<Store> getHospitalList() {
        return hospitalList;
    }

    public ArrayList<Store> getFilteredHospitalList(String query) {
        query = query.toLowerCase();
        ArrayList<Store> out = new ArrayList<>();
        for (Store s : hospitalList) {
            String name = s.getName().toLowerCase();
            if (name.contains(query)) out.add(s);
        }
        return out;
    }

}
