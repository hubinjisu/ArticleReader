package com.article.binhu.articlereader.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by binhu on 17.06.17.
 */

public class Legacy implements Parcelable {
    private String xlarge;
    private int xlargewidth;
    private int xlargeheight;

    private String wide;
    private int widewidth;
    private int wideheight;

    private String thumbnail;
    private int thumbnailwidth;
    private int thumbnailheight;

    public String getXlarge() {
        return xlarge;
    }

    public void setXlarge(String xlarge) {
        this.xlarge = xlarge;
    }

    public int getXlargewidth() {
        return xlargewidth;
    }

    public void setXlargewidth(int xlargewidth) {
        this.xlargewidth = xlargewidth;
    }

    public int getXlargeheight() {
        return xlargeheight;
    }

    public void setXlargeheight(int xlargeheight) {
        this.xlargeheight = xlargeheight;
    }

    public String getWide() {
        return wide;
    }

    public void setWide(String wide) {
        this.wide = wide;
    }

    public int getWidewidth() {
        return widewidth;
    }

    public void setWidewidth(int widewidth) {
        this.widewidth = widewidth;
    }

    public int getWideheight() {
        return wideheight;
    }

    public void setWideheight(int wideheight) {
        this.wideheight = wideheight;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getThumbnailwidth() {
        return thumbnailwidth;
    }

    public void setThumbnailwidth(int thumbnailwidth) {
        this.thumbnailwidth = thumbnailwidth;
    }

    public int getThumbnailheight() {
        return thumbnailheight;
    }

    public void setThumbnailheight(int thumbnailheight) {
        this.thumbnailheight = thumbnailheight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.xlarge);
        dest.writeInt(this.xlargewidth);
        dest.writeInt(this.xlargeheight);
        dest.writeString(this.wide);
        dest.writeInt(this.widewidth);
        dest.writeInt(this.wideheight);
        dest.writeString(this.thumbnail);
        dest.writeInt(this.thumbnailwidth);
        dest.writeInt(this.thumbnailheight);
    }

    public Legacy() {
    }

    protected Legacy(Parcel in) {
        this.xlarge = in.readString();
        this.xlargewidth = in.readInt();
        this.xlargeheight = in.readInt();
        this.wide = in.readString();
        this.widewidth = in.readInt();
        this.wideheight = in.readInt();
        this.thumbnail = in.readString();
        this.thumbnailwidth = in.readInt();
        this.thumbnailheight = in.readInt();
    }

    public static final Parcelable.Creator<Legacy> CREATOR = new Parcelable.Creator<Legacy>() {
        @Override
        public Legacy createFromParcel(Parcel source) {
            return new Legacy(source);
        }

        @Override
        public Legacy[] newArray(int size) {
            return new Legacy[size];
        }
    };
}
