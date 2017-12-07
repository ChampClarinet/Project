package com.easypets.champ.easypets.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.easypets.champ.easypets.Models.Review;
import com.easypets.champ.easypets.R;
import com.easypets.champ.easypets.Utils.Utils;

import java.util.ArrayList;

public class ServiceReviewRecyclerAdapter extends RecyclerView.Adapter<ServiceReviewRecyclerAdapter.GenericHolder>{

    private ArrayList<Review> reviews;
    private Context context;

    public ServiceReviewRecyclerAdapter(ArrayList<Review> reviews, Context context) {
        this.reviews = reviews;
        this.context = context;
    }

    @Override
    public GenericHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_review, parent, false);
        ViewHolder holder = new ViewHolder(v, context);
        return holder;
    }

    @Override
    public void onBindViewHolder(GenericHolder holder, int position) {
        Review review = reviews.get(position);
        holder.setViewData(review);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public abstract static class GenericHolder extends RecyclerView.ViewHolder {

        public GenericHolder(View itemView) {
            super(itemView);
        }

        abstract public void setViewData(Review review);

    }

    public static class ViewHolder extends GenericHolder {

        //bind cardview's widgets
        private Context context;
        private ImageView userPicImageView;
        private TextView userNameTextView;
        private TextView reviewTextView;
        private ImageView reviewImageView;
        private TextView repliesTextView;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            this.userPicImageView = itemView.findViewById(R.id.review_user_pic);
            this.userNameTextView = itemView.findViewById(R.id.review_user_name);
            this.reviewTextView = itemView.findViewById(R.id.review_text);
            this.reviewImageView = itemView.findViewById(R.id.review_picture);
            this.repliesTextView = itemView.findViewById(R.id.review_reply);
        }

        @Override
        public void setViewData(final Review review) {
            //userPicImageView.setImageDrawable(Utils.getDrawableFromAssets(context, review.getReviewerUserPicPath()));
            userNameTextView.setText(review.getReviewerUsername());
            reviewTextView.setText(review.getReviewText());
            if(review.getReviewPicPath() != null){
                reviewImageView.setImageDrawable(Utils.getDrawableFromAssets(context, review.getReviewPicPath()));
            }else reviewImageView.setVisibility(View.GONE);
            repliesTextView.setText(context.getString(R.string.view_replies, review.getRepliesQuantity()));
        }

    }

}
