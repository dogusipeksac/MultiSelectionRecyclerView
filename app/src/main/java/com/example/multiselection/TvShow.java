package com.example.multiselection;


public class TvShow {
    private String name,createdBy,story;
    private String image;
    private boolean isSelected=false;
    private float rating;

    public TvShow(String name, String createdBy, String story, String image ,float rating) {
        this.name = name;
        this.createdBy = createdBy;
        this.story = story;
        this.image = image;
        this.rating = rating;
    }

    public TvShow() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
