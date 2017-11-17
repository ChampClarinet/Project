package com.example.champ.project.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.champ.project.Menu.ServiceMenu;
import com.example.champ.project.Models.PetService;
import com.example.champ.project.R;
import com.example.champ.project.StoreActivity;

import java.util.ArrayList;

public class ServiceRecyclerViewAdapter extends RecyclerView.Adapter<ServiceRecyclerViewAdapter.GenericHolder> {

    private ArrayList<PetService> dataSet;
    private Context context;

    public ServiceRecyclerViewAdapter(ArrayList<PetService> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public GenericHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
        ViewHolder holder = new ViewHolder(v, context);
        return holder;
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

    public ArrayList<PetService> getData(){
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
        private ImageView rate2;
        private ImageView rate3;
        private Context context;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            cardView = itemView.findViewById(R.id.cv);
            name = itemView.findViewById(R.id.cv_name);
            likes = itemView.findViewById(R.id.cv_likes);
            photo = itemView.findViewById(R.id.cv_photo);
            rate2 = itemView.findViewById(R.id.cv_money2);
            rate3 = itemView.findViewById(R.id.cv_money3);

        }

        private void openStoreActivity(int id) throws Exception {
            ServiceMenu menu = ServiceMenu.getInstance(context);
            PetService petService = menu.getById(id);
            Intent i = new Intent(context, StoreActivity.class);
            i.putExtra(context.getString(R.string.model_name_services), petService);
            context.startActivity(i);
        }

        @Override
        public void setViewData(final PetService petService) {
            String s = Integer.toString(petService.getLikes());
            this.name.setText(petService.getName());
            this.likes.setText(s);
            //photo.setImageDrawable(Utils.getDrawableFromAssets(context, petService.getPicturePath()));
            photo.setImageResource(R.drawable.ic_launcher);
            int priceRate = petService.getPriceRate();
            if (priceRate == 2 || priceRate == 3) rate2.setVisibility(View.VISIBLE);
            if (priceRate == 3) rate3.setVisibility(View.VISIBLE);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        openStoreActivity(petService.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public void updateList(ArrayList<PetService> newList) {
        if (newList != null) {
            dataSet = newList;
            notifyDataSetChanged();
        }
        Log.d("Recycler", printList());
    }

    private String printList() {
        String out = "";
        for (PetService s : dataSet) out += s.getName() + ", ";
        return out;
    }

}
