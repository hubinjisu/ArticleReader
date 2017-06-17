package com.article.binhu.articlereader.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by binhu on 16.06.17.
 */

public class ArticleResponse {
    private List<Article> docs;

    public List<Article> getDocs() {
        return docs;
    }

    public void setDocs(List<Article> docs) {
        this.docs = docs;
    }
}
