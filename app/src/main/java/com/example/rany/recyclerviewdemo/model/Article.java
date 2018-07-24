package com.example.rany.recyclerviewdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {

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
    public Article(String title, String description, int viewCount, String image) {
        this.title = title;
        this.description = description;
        this.viewCount = viewCount;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", viewCount=" + viewCount +
                ", image='" + image + '\'' +
                '}';
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeInt(this.viewCount);
        dest.writeString(this.image);
    }

    protected Article(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.viewCount = in.readInt();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
