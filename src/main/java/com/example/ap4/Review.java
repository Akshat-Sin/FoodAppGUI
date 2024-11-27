package com.example.ap4;

import java.util.Date;

public class Review {
    private String reviewText;
    private int rating; // Rating out of 5
    private Date reviewDate;

    public Review(String reviewText, int rating) {
        this.reviewText = reviewText;
        this.rating = rating;
        this.reviewDate = new Date(); // Automatically set to the current date
    }


    public String getReviewText() {
        return reviewText;
    }

    public int getRating() {
        return rating;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    @Override
    public String toString() {
        return rating + " stars: " + reviewText + " [" + reviewDate + "]";
    }
}

