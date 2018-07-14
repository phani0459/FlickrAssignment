package com.flickrassignment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PhotoItem implements Serializable{

    @SerializedName("title")
    private String title;
    @SerializedName("link")
    private String link;
    @SerializedName("date_taken")
    private String date_taken;
    @SerializedName("description")
    private String description;
    @SerializedName("published")
    private String published;
    @SerializedName("author")
    private String author;
    @SerializedName("author_id")
    private String author_id;
    @SerializedName("tags")
    private String tags;
    @SerializedName("media")
    private Media media;

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

    public String getDate_taken() {
        return date_taken;
    }

    public void setDate_taken(String date_taken) {
        this.date_taken = date_taken;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public class Media implements Serializable{
        @SerializedName("m")
        private String thumbnailImage;

        public String getThumbnailImage() {
            return thumbnailImage;
        }

        public void setThumbnailImage(String thumbnailImage) {
            this.thumbnailImage = thumbnailImage;
        }
    }

}
