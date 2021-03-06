package com.koddev.chatapp.Model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String id;
    private String username;
    private String imageURL;
    private String status;
    private String search;
    private String backgroundImageURL;
    protected List<Background> backgroundList = new ArrayList<Background>();

    public User(String id, String username, String imageURL, String status, String search, String backgroundImageURL) {
        this.id = id;
        this.username = username;
        this.imageURL = imageURL;
        this.status = status;
        this.search = search;
        this.backgroundImageURL = backgroundImageURL;
        Background defBackground = new Background("default");
        backgroundList.add(defBackground);
    }

    public User() {

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getBackgroundImageURL()
    { return backgroundImageURL; }

    public void setBackgroundImageURL(String backgroundImageURL) { this.backgroundImageURL = backgroundImageURL; }
}
