package com.article.binhu.articlereader.ui.articles;

import android.util.Log;

import com.article.binhu.articlereader.model.Article;
import com.article.binhu.articlereader.model.RequestResponse;
import com.article.binhu.articlereader.service.ArticleService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by binhu on 16.06.17.
 */

public class ArticlesPresenter implements ArticlesContract.IArticlesPresenter {

    private static final String TAG = "ArticlesPresenter";
    private ArticlesContract.IArticlesView articlesView;
    private ArticleService articleService;

    @Inject
    public ArticlesPresenter(ArticlesContract.IArticlesView articlesView, ArticleService articleService) {
        this.articlesView = articlesView;
        this.articleService = articleService;
    }

    @Override
    public void loadArticles() {
        Log.i(TAG, "loadArticles: ");
        articlesView.startProgress();
        articleService.loadArticles("d7d008858ee64d3fa5f8d8b6a839b435")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(RequestResponse::getResponse)
                .doOnError(throwable -> {
                    articlesView.stopProgress();
                    articlesView.onLoadArticlesFailed();
                })
                .subscribe(articleResponse -> {
                    Log.i(TAG, "accept: ");
                    articlesView.stopProgress();
                    if (articleResponse != null) {
                        Log.i(TAG, "article: getResponse: ");
                        for (Article article : articleResponse.getDocs()) {
                            Log.i(TAG, "call: article:" + article.getWeb_url());
                        }
                        articlesView.onLoadArticlesSuccessful(articleResponse.getDocs());
                    } else {
                        articlesView.onLoadArticlesFailed();
                    }
                });
    }
}
