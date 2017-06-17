package com.article.binhu.articlereader.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by binhu on 16.06.17.
 */

public class RequestResponse {
    private String status;
    private String copyright;
    private ArticleResponse response;

    public ArticleResponse getResponse() {
        return response;
    }

    public void setResponse(ArticleResponse response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

}
