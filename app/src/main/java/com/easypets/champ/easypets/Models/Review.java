package com.easypets.champ.easypets.Models;

import android.support.annotation.Nullable;

import java.util.ArrayList;

public class Review {

    private String reviewerUsername;
    private String reviewerUserPicPath;
    private String reviewText;
    private String reviewPicPath;
    private ArrayList<Reply> replies;

    public Review(String reviewerUsername, String reviewerUserPicPath, String reviewText, @Nullable String reviewPicPath, ArrayList<Reply> replies) {
        this.reviewerUsername = reviewerUsername;
        this.reviewerUserPicPath = reviewerUserPicPath;
        this.reviewText = reviewText;
        this.reviewPicPath = reviewPicPath;
        this.replies = replies;
    }

    public Review(String reviewerUsername, String reviewerUserPicPath, String reviewText, @Nullable String reviewPicPath) {
        this.reviewerUsername = reviewerUsername;
        this.reviewerUserPicPath = reviewerUserPicPath;
        this.reviewText = reviewText;
        this.reviewPicPath = reviewPicPath;
        replies = new ArrayList<>();
    }

    public String getReviewerUsername() {
        return reviewerUsername;
    }

    public void setReviewerUsername(String reviewerUsername) {
        this.reviewerUsername = reviewerUsername;
    }

    public String getReviewerUserPicPath() {
        return reviewerUserPicPath;
    }

    public void setReviewerUserPicPath(String reviewerUserPicPath) {
        this.reviewerUserPicPath = reviewerUserPicPath;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getReviewPicPath() {
        return reviewPicPath;
    }

    public void setReviewPicPath(String reviewPicPath) {
        this.reviewPicPath = reviewPicPath;
    }

    public ArrayList<Reply> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<Reply> replies) {
        this.replies = replies;
    }

    public int getRepliesQuantity() {
        return replies.size();
    }

    @Override
    public String toString() {
        return getReviewerUsername() + " reviews "+getReviewText();
    }
}
