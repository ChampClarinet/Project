package com.example.champ.project.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.champ.project.Menu.HospitalMenu;
import com.example.champ.project.Menu.ServiceMenu;
import com.example.champ.project.Models.Store;
import com.example.champ.project.R;
import com.example.champ.project.StoreActivity;

import java.util.ArrayList;

public class ServiceRecyclerViewAdapter extends RecyclerView.Adapter<ServiceRecyclerViewAdapter.GenericHolder> {

    private ArrayList<Store> cardList;
    private Context context;
    private String serviceType;
    private SearchView searchView;

    public ServiceRecyclerViewAdapter(ArrayList<Store> cardList, Context context, String serviceType, SearchView searchView) {
        this.cardList = cardList;
        this.context = context;
        this.serviceType = serviceType;
        this.searchView = searchView;
    }

    @Override
    public GenericHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
        ViewHolder holder = new ViewHolder(v, context, serviceType);
        return holder;
    }

    @Override
    public void onBindViewHolder(GenericHolder holder, int position) {
        Store store = cardList.get(position);
        holder.setViewData(store);
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public abstract static class GenericHolder extends RecyclerView.ViewHolder {

        public GenericHolder(View itemView) {
            super(itemView);
        }

        abstract public void setViewData(Store store);

    }

    public static class ViewHolder extends GenericHolder {

        //bind cardview's widgets
        private CardView cardView;
        private TextView name;
        private TextView likes;
        private ImageView photo;
        private ImageView rate2;
        private ImageView rate3;
        private Context context;

        private String serviceType;

        public ViewHolder(View itemView, Context context, String serviceType) {
            super(itemView);
            this.context = context;
            this.serviceType = serviceType;
            cardView = itemView.findViewById(R.id.cv);
            name = itemView.findViewById(R.id.cv_name);
            likes = itemView.findViewById(R.id.cv_likes);
            photo = itemView.findViewById(R.id.cv_photo);
            rate2 = itemView.findViewById(R.id.cv_money2);
            rate3 = itemView.findViewById(R.id.cv_money3);

        }

        private void openStoreActivity(int id) throws Exception {
            Store store;
            if (serviceType.equals(context.getString(R.string.model_name_hospital)))
                store = HospitalMenu.getInstance(context).findHospitalById(id);
            else if (serviceType.equals(context.getString(R.string.model_name_services)))
                store = ServiceMenu.getInstance(context).findServiceById(id);
            else throw new Exception(context.getString(R.string.error_invalid_service_type));
            Intent i = new Intent(context, StoreActivity.class);
            i.putExtra(context.getString(R.string.model_name_services), store);
            context.startActivity(i);
        }

        @Override
        public void setViewData(final Store store) {
            String s = Integer.toString(store.getLikes());
            this.name.setText(store.getName());
            this.likes.setText(s);
            //photo.setImageDrawable(Utils.getDrawableFromAssets(context, store.getPicturePath()));
            photo.setImageResource(R.drawable.ic_launcher);
            int priceRate = store.getPriceRate();
            if (priceRate == 2 || priceRate == 3) rate2.setVisibility(View.VISIBLE);
            if (priceRate == 3) rate3.setVisibility(View.VISIBLE);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        openStoreActivity(store.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public void updateList(ArrayList<Store> newList) {
        if (newList != null) {
            cardList = newList;
            notifyDataSetChanged();
        }
        Log.d("Recycler", printList());
    }

    private String printList() {
        String out = "";
        for (Store s : cardList) out += s.getName() + ", ";
        return out;
    }

}
