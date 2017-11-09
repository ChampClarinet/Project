package com.example.champ.project.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.champ.project.Models.SettingsItem;
import com.example.champ.project.R;

import java.util.ArrayList;

public class SettingListAdapter extends ArrayAdapter<SettingsItem>{

    private Context context;
    private int resId;
    private ArrayList<SettingsItem> settingsList;

    public SettingListAdapter(Context context, int resId, ArrayList<SettingsItem> settingList) {
        super(context, resId, settingList);
        this.context = context;
        this.resId = resId;
        this.settingsList = settingList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemLayout = convertView;
        if(itemLayout == null) itemLayout = View.inflate(context, resId, null);
        TextView name = itemLayout.findViewById(R.id.list_settings_text);
        ImageView icon = itemLayout.findViewById(R.id.list_settings_icon);

        SettingsItem item = settingsList.get(position);
        name.setText(item.getName());
        icon.setImageResource(item.getIconResId());

        return itemLayout;
    }
}
