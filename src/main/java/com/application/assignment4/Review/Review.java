package com.application.assignment4.Review;

import com.application.assignment4.Application;
import com.application.assignment4.Item.Item;

public class Review extends Application {
    private Item item;
    private String rating;
    private String description;

    public Review(Item item, String rating, String description) {
        this.item = item;
        this.rating = rating;
        this.description = description;
    }

    // Getters

    public Item getItem() {
        return item;
    }

    public String getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    // Setters

    public void setItem(Item item) {
        this.item = item;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void printDetails() {
        System.out.println("\tItem Name : " + this.getItem().getName());
        System.out.println("\tRating : " + this.getRating());
        System.out.println("\tDescription : " + this.getDescription());
    }
}
