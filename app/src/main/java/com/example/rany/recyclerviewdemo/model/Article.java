package com.example.rany.recyclerviewdemo.model;

public class Article {
    private int id;
    private String title;
    private String description;
    private int viewCount;
    private String image;

    public Article(int id, String title, String description, int viewCount, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.viewCount = viewCount;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
