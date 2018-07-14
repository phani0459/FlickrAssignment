package com.flickrassignment.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Photo {

    @SerializedName("title")
    private String title;
    @SerializedName("link")
    private String link;
    @SerializedName("description")
    private String description;
    @SerializedName("modified")
    private String modified;
    @SerializedName("generator")
    private String generator;
    @SerializedName("items")
    private ArrayList<PhotoItem> items;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public ArrayList<PhotoItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<PhotoItem> items) {
        this.items = items;
    }
}