package com.example.champ.project.MenuOld;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.champ.project.Models.PetService;
import com.example.champ.project.Utils.SortAgent;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceMenu2 {

    private static final String TAG = ServiceMenu2.class.getSimpleName();
    public static final String SORT_BY_NAME = "NAME";
    public static final String SORT_BY_LOCATION = "LOCATION";
    public static final String SORT_BY_LIKES = "LIKES";
    public static final String SORT_BY_PRICE_RATE = "PRICE";

    private static ServiceMenu2 instance;
    private static ArrayList<PetService> listById = new ArrayList<>();
    private static ArrayList<PetService> listByName = new ArrayList<>();
    private static ArrayList<PetService> listByPriceRate = new ArrayList<>();
    private static ArrayList<PetService> listByLikes = new ArrayList<>();
    private static ArrayList<PetService> listByLocation = new ArrayList<>();
    private static HashMap<Integer, PetService> serviceHash = new HashMap<>();

    private Context context;

    public static ServiceMenu2 getInstance(Context context) {
        if (instance == null) instance = new ServiceMenu2(context);
        //instance.loadFromDatabase();
        return instance;
    }

    private ServiceMenu2(Context context) {
        this.context = context;
    }
/*
    public void loadFromDatabase() {
        listById.clear();
        listById.add(new PetService(1, "ABC Pet Shop", null, 3, 42, 13.7124214, 100.52708480000001));
        listById.add(new PetService(2, "RotFai Dog", null, 1, 347, 13.7124214, 100.52708480000001));
        listById.add(new PetService(3, "Navamin Cat", null, 2, 214, 13.8263031, 100.67894260000003));
        listById.add(new PetService(4, "Blah Blah Blah Bird", null, 1, 578, 13.7574965, 100.4438738));
        for (PetService s : listById) serviceHash.put(s.getId(), s);
        listByName = SortAgent.sortServiceByName(serviceHash);
        listByPriceRate = SortAgent.sortServiceByPriceRate(serviceHash);
        listByLikes = SortAgent.sortServiceByLikes(serviceHash);
        listByLocation = SortAgent.sortServiceByDistance(serviceHash);
    }
*/
    public PetService findServiceById(int id) {
        return serviceHash.get(id);
    }

    public ArrayList<PetService> getServiceList(String sortBy) {
        switch (sortBy){
            case SORT_BY_NAME: return listByName;
            case SORT_BY_LIKES: return listByLikes;
            case SORT_BY_LOCATION: return listByLocation;
            case SORT_BY_PRICE_RATE: return listByPriceRate;
            default: return listById;
        }
    }

    public ArrayList<PetService> getFilteredServiceList(@Nullable String query) {
        query = query.toLowerCase();
        ArrayList<PetService> out = new ArrayList<>();
        for (PetService s : listById) {
            String name = s.getName().toLowerCase();
            if (name.contains(query)) out.add(s);
        }
        String s = "";
        for (PetService petService : out) {
            s += petService.getName() + "\n";
        }
        Log.d("list", s);
        return out;
    }

    public void checkList() {
        System.out.println("by Name");
        for (PetService s : listByName) {
            System.out.println(s);
        }
        System.out.println();
        System.out.println("by Price rate");
        for (PetService s : listByPriceRate) {
            System.out.println(s);
        }
        System.out.println();
        System.out.println("by Likes");
        for (PetService s : listByLikes) {
            System.out.println(s);
        }
    }

    @Override
    public String toString() {
        return ServiceMenu2.class.getSimpleName() + ": PetService quantity " + getServiceList(null).size();
    }


}
