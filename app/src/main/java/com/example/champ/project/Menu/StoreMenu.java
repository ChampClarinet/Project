package com.example.champ.project.Menu;

import android.content.Context;

import com.example.champ.project.Models.Store;

import java.util.ArrayList;

public abstract class StoreMenu {

    protected Context context;

    protected StoreMenu(Context context) {
        this.context = context;
    }

    public abstract Store findById(int id);

    public abstract ArrayList<Store> getList();

}
