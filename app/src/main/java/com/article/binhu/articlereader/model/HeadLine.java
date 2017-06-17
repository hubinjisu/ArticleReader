package com.article.binhu.articlereader.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by binhu on 16.06.17.
 */

public class HeadLine implements Parcelable {
    private String main;

    private String print_headline;

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getPrint_headline() {
        return print_headline;
    }

    public void setPrint_headline(String print_headline) {
        this.print_headline = print_headline;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.main);
        dest.writeString(this.print_headline);
    }

    public HeadLine() {
    }

    protected HeadLine(Parcel in) {
        this.main = in.readString();
        this.print_headline = in.readString();
    }

    public static final Parcelable.Creator<HeadLine> CREATOR = new Parcelable.Creator<HeadLine>() {
        @Override
        public HeadLine createFromParcel(Parcel source) {
            return new HeadLine(source);
        }

        @Override
        public HeadLine[] newArray(int size) {
            return new HeadLine[size];
        }
    };
}
