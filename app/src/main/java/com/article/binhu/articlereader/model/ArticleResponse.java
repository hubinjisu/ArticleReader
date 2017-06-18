package com.article.binhu.articlereader.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by binhu on 16.06.17.
 */

public class ArticleResponse implements Parcelable {

    private Meta meta;

    private List<Article> docs;

    public List<Article> getDocs() {
        return docs;
    }

    public void setDocs(List<Article> docs) {
        this.docs = docs;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.meta, flags);
        dest.writeTypedList(this.docs);
    }

    public ArticleResponse() {
    }

    protected ArticleResponse(Parcel in) {
        this.meta = in.readParcelable(Meta.class.getClassLoader());
        this.docs = in.createTypedArrayList(Article.CREATOR);
    }

    public static final Parcelable.Creator<ArticleResponse> CREATOR =
            new Parcelable.Creator<ArticleResponse>() {
                @Override
                public ArticleResponse createFromParcel(Parcel source) {
                    return new ArticleResponse(source);
                }

                @Override
                public ArticleResponse[] newArray(int size) {
                    return new ArticleResponse[size];
                }
            };
}
