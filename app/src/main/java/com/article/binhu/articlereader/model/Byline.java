package com.article.binhu.articlereader.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by binhu on 16.06.17.
 */

public class Byline implements Parcelable {
    //    private String person;
    private String original;
    private String organization;

//    public String getPerson() {
//        return person;
//    }
//
//    public void setPerson(String person) {
//        this.person = person;
//    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.original);
        dest.writeString(this.organization);
    }

    public Byline() {
    }

    protected Byline(Parcel in) {
        this.original = in.readString();
        this.organization = in.readString();
    }

    public static final Parcelable.Creator<Byline> CREATOR = new Parcelable.Creator<Byline>() {
        @Override
        public Byline createFromParcel(Parcel source) {
            return new Byline(source);
        }

        @Override
        public Byline[] newArray(int size) {
            return new Byline[size];
        }
    };
}
