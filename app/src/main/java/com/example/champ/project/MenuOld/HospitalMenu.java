package com.example.champ.project.MenuOld;

import android.content.Context;
import android.support.annotation.Nullable;

import com.example.champ.project.Models.PetService;
import com.example.champ.project.Utils.SortAgent;

import java.util.ArrayList;
import java.util.HashMap;

public class HospitalMenu {

    private static final String TAG = HospitalMenu.class.getSimpleName();
    public static final String SORT_BY_NAME = "NAME";
    public static final String SORT_BY_LOCATION = "LOCATION";
    public static final String SORT_BY_LIKES = "LIKES";
    public static final String SORT_BY_PRICE_RATE = "PRICE";

    private static HospitalMenu instance;
    private static ArrayList<PetService> listById = new ArrayList<>();
    private static ArrayList<PetService> listByName = new ArrayList<>();
    private static ArrayList<PetService> listByPriceRate = new ArrayList<>();
    private static ArrayList<PetService> listByLikes = new ArrayList<>();
    private static ArrayList<PetService> listByLocation = new ArrayList<>();
    private static HashMap<Integer, PetService> hospitalHash = new HashMap<>();

    private Context context;

    public static HospitalMenu getInstance(Context context) {
        if (instance == null) instance = new HospitalMenu(context);
        return instance;
    }

    private HospitalMenu(Context context) {
        this.context = context;
        //loadFromDatabase();
    }
    /*
    private void loadFromDatabase() {
        listById.clear();
        listById.add(new PetService(1, "ABC Hospital", null, 2, 20, 12.7960376, 99.980891));
        listById.add(new PetService(2, "Narita Hospital", null, 3, 159, 12.7960376, 99.980891));
        listById.add(new PetService(3, "Bangkok Hospital", null, 1, 246, 13.7243647, 100.53877));
        listById.add(new PetService(4, "Blah Blah Blah Hospital", null, 2, 177, 13.7061402, 100.6221974));
        for (PetService s : listById) hospitalHash.put(s.getId(), s);
        listByName = SortAgent.sortServiceByName(hospitalHash);
        listByPriceRate = SortAgent.sortServiceByPriceRate(hospitalHash);
        listByLikes = SortAgent.sortServiceByLikes(hospitalHash);
        listByLocation = SortAgent.sortServiceByDistance(hospitalHash);
    }
*/
    public PetService findHospitalById(int id) {
        return hospitalHash.get(id);
    }

    public ArrayList<PetService> getHospitalList(@Nullable String sortBy) {
        switch (sortBy){
            case SORT_BY_NAME: return listByName;
            case SORT_BY_LIKES: return listByLikes;
            case SORT_BY_LOCATION: return listByLocation;
            case SORT_BY_PRICE_RATE: return listByPriceRate;
            default: return listById;
        }
    }

    public ArrayList<PetService> getFilteredHospitalList(String query) {
        query = query.toLowerCase();
        ArrayList<PetService> out = new ArrayList<>();
        for (PetService s : listById) {
            String name = s.getName().toLowerCase();
            if (name.contains(query)) out.add(s);
        }
        return out;
    }

}
