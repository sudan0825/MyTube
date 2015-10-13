package com.example.dansu.mytube;

import com.google.api.client.util.DateTime;

import java.util.Date;

/**
 * Created by dansu on 10/9/15.
 */
public class VideoItem {

    private String title;
    private DateTime publishDate;
    private String thumbnailURL;
    private String id;
    private int numOfView;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {

        return description;
    }

    private String description;

    public void setNumOfView(int numOfReview) {
        this.numOfView = numOfReview;
    }

    public int getNumOfView() {
        return numOfView;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(DateTime pub_date) {
        this.publishDate = pub_date;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnail) {
        this.thumbnailURL = thumbnail;
    }

}

