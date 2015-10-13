package com.example.dansu.mytube;

import com.google.api.client.util.DateTime;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by dansu on 10/9/15.
 */
public class VideoItem {

    private String title;
    private DateTime publishDate;
    private String thumbnailURL;
    private String id;

    private BigInteger views;
    private boolean favorite;

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isFavorite() {

        return favorite;
    }

    public void setViews(BigInteger views) {
        this.views = views;
    }

    public BigInteger getViews() {

        return views;
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

