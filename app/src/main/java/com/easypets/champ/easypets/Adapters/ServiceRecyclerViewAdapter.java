package com.easypets.champ.easypets.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.easypets.champ.easypets.Models.PetService;
import com.easypets.champ.easypets.R;
import com.easypets.champ.easypets.StoreActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceRecyclerViewAdapter extends RecyclerView.Adapter<ServiceRecyclerViewAdapter.GenericHolder> {

    private ArrayList<PetService> dataSet;
    private HashMap<String, PetService> dataMap;
    private Context context;

    public ServiceRecyclerViewAdapter(ArrayList<PetService> dataSet, Context context) {
        this.dataSet = dataSet;
        this.dataMap = new HashMap<>();
        this.context = context;
    }

    @Override
    public GenericHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_list_service, parent, false);
        return new ViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(GenericHolder holder, int position) {
        PetService petService = dataSet.get(position);
        holder.setViewData(petService);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public ArrayList<PetService> getData() {
        return dataSet;
    }

    public abstract static class GenericHolder extends RecyclerView.ViewHolder {

        public GenericHolder(View itemView) {
            super(itemView);
        }

        abstract public void setViewData(PetService petService);

    }

    public static class ViewHolder extends GenericHolder {

        //bind cardview's widgets
        private CardView cardView;
        private TextView name;
        private TextView likes;
        private ImageView photo;
        private TextView rate;
        private TextView distance;
        private Context context;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            cardView = itemView.findViewById(R.id.cv);
            name = itemView.findViewById(R.id.cv_name);
            likes = itemView.findViewById(R.id.cv_likes);
            rate = itemView.findViewById(R.id.cv_rate);
            photo = itemView.findViewById(R.id.cv_photo);
            distance = itemView.findViewById(R.id.cv_distance);
        }

        private void openStoreActivity(PetService service) throws Exception {
            Intent i = new Intent(context, StoreActivity.class);
            i.putExtra(context.getString(R.string.model_name_services), service);
            context.startActivity(i);
        }

        @Override
        public void setViewData(final PetService petService) {
            String s = Integer.toString(petService.getLikes());
            this.name.setText(petService.getName());
            this.likes.setText(s);
            Glide.with(context).load(petService.getLogoPath()).override(60, 60).into(this.photo);
            String rateText = context.getString(R.string.priceRate) + " ";
            for (int i = 0; i < petService.getPriceRate(); ++i) {
                rateText += context.getString(R.string.rate_symbol);
            }
            rate.setText(rateText);
            String serviceDistance = String.valueOf(petService.getDistanceInMetres()) + " m";
            if (petService.getDistance() > 1000)
                serviceDistance = String.valueOf(petService.getDistanceInKm()) + " km";
            distance.setText(serviceDistance);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        openStoreActivity(petService);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    private String printList() {
        String out = "";
        for (PetService s : dataSet) out += s.getName() + ", ";
        return out;
    }

}
