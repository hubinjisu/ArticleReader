package com.article.binhu.articlereader.ui.articles;

import android.util.Log;

import com.article.binhu.articlereader.model.Article;
import com.article.binhu.articlereader.model.RequestResponse;
import com.article.binhu.articlereader.service.ArticleService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Presenter for the article list fragment
 * Created by binhu on 16.06.17.
 */
public class ArticlesPresenter implements ArticlesContract.IArticlesPresenter {

    private static final String TAG = "ArticlesPresenter";
    private static final String API_ID = "d7d008858ee64d3fa5f8d8b6a839b435";
    private ArticlesContract.IArticlesView articlesView;
    private ArticleService articleService;
    // record the newest id of the first current data
    String newestId = "";

    @Inject
    public ArticlesPresenter(ArticlesContract.IArticlesView articlesView, ArticleService articleService) {
        this.articlesView = articlesView;
        this.articleService = articleService;
    }

    @Override
    public void loadArticles() {
        Log.i(TAG, "loadArticles: ");
        articlesView.startProgress();
        articleService.loadArticles(API_ID)
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
                        int flagIndex = 0;
                        List<Article> results = articleResponse.getDocs();
                        // check if there are new data or not
                        for (flagIndex = 0; flagIndex < results.size(); flagIndex++) {
                            String newId = results.get(flagIndex).get_id();
                            // compare with the first old data id, and get the last index of the new data
                            if (newestId.equals(newId)) {
                                break;
                            }
                        }

                        // The first item matched with id flag, means no new data
                        if (flagIndex == 0) {
                            articlesView.onLoadArticlesNoUpdate();
                        }
                        else {
                            newestId = results.get(0).get_id();
                            results.subList(0, flagIndex - 1);
                            articlesView.onLoadArticlesSuccessful(results);
                        }
                        Log.i(TAG, "article: getResponse: newestId: " + newestId);
                    }
                    else {
                        articlesView.onLoadArticlesFailed();
                    }
                });
    }
}
