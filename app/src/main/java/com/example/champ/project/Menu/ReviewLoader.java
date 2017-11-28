package com.example.champ.project.Menu;

import com.example.champ.project.Models.Reply;
import com.example.champ.project.Models.Review;

import java.util.ArrayList;

public class ReviewLoader {

    private static final String TAG = ReviewLoader.class.getSimpleName();

    private static ReviewLoader instance;

    private ArrayList<Review> reviews;

    public static ReviewLoader getInstance(){
        if(instance == null) instance = new ReviewLoader();
        instance.load();
        return instance;
    }

    public ReviewLoader() {
        reviews = new ArrayList<>();
    }

    private void load() {
        //this is mock
        Review review = new Review("Anonymous", null, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet lorem non arcu tincidunt laoreet.", null);
        reviews.add(review);
        ArrayList<Reply> replies = new ArrayList<>();
        replies.add(new Reply("System", null));
        review = new Review("Champ", null, "Awesome man\ni luv it!", null, replies);
        reviews.add(review);
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

}
