package com.article.binhu.articlereader.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hubin on 2017/6/18.
 */

public class Meta implements Parcelable {
    private int hits;
    private int time;
    private int offset;

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.hits);
        dest.writeInt(this.time);
        dest.writeInt(this.offset);
    }

    public Meta() {
    }

    protected Meta(Parcel in) {
        this.hits = in.readInt();
        this.time = in.readInt();
        this.offset = in.readInt();
    }

    public static final Parcelable.Creator<Meta> CREATOR = new Parcelable.Creator<Meta>() {
        @Override
        public Meta createFromParcel(Parcel source) {
            return new Meta(source);
        }

        @Override
        public Meta[] newArray(int size) {
            return new Meta[size];
        }
    };
}
