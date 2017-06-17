package com.article.binhu.articlereader.service;


import com.article.binhu.articlereader.model.RequestResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by binhu on 16.06.17.
 */

public interface ArticleService {

    @GET("articlesearch.json")
    Observable<RequestResponse> loadArticles(@Header("api-key") String apiKey);
}
