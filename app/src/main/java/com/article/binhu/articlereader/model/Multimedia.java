package com.article.binhu.articlereader.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by binhu on 16.06.17.
 */

public class Multimedia implements Parcelable {
    private String type;
    private int width;
    private int height;
    private String url;
    private String subtype;
    private Legacy legacy;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public Legacy getLegacy() {
        return legacy;
    }

    public void setLegacy(Legacy legacy) {
        this.legacy = legacy;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeString(this.url);
        dest.writeString(this.subtype);
        dest.writeParcelable(this.legacy, flags);
    }

    public Multimedia() {
    }

    protected Multimedia(Parcel in) {
        this.type = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
        this.url = in.readString();
        this.subtype = in.readString();
        this.legacy = in.readParcelable(Legacy.class.getClassLoader());
    }

    public static final Parcelable.Creator<Multimedia> CREATOR =
            new Parcelable.Creator<Multimedia>() {
                @Override
                public Multimedia createFromParcel(Parcel source) {
                    return new Multimedia(source);
                }

                @Override
                public Multimedia[] newArray(int size) {
                    return new Multimedia[size];
                }
            };
}
