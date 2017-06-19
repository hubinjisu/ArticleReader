package com.article.binhu.articlereader.ui.articles;

import android.util.Log;

import com.article.binhu.articlereader.model.Article;
import com.article.binhu.articlereader.model.ArticleResponse;
import com.article.binhu.articlereader.model.RequestResponse;
import com.article.binhu.articlereader.service.ArticleService;

import java.util.ArrayList;

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
                .map(ArticleResponse::getDocs)
                .map(articles -> {
                    int flagIndex = 0;
                    // check if there are new data or not
                    for (flagIndex = 0; flagIndex < articles.size(); flagIndex++) {
                        String newId = articles.get(flagIndex).get_id();
                        // compare with the first old data id, and get the last index of the new data
                        if (newestId.equals(newId)) {
                            break;
                        }
                    }

                    Log.i(TAG, "map：flagIndex:" + flagIndex);
                    // save the newest id as the flag
                    newestId = articles.get(0).get_id();
                    // Collect all the filtered data by id flag
                    articles = articles.subList(0, flagIndex);
                    // callback here to distinguish the empty list situation in onErrorReturn
                    if (articles.size() == 0) {
                        articlesView.onLoadArticlesNoUpdate();
                    }
                    return articles;
                })
                .onErrorReturn(throwable -> {
                    Log.e(TAG, "error.....");
                    articlesView.onLoadArticlesFailed();
                    return new ArrayList<Article>();
                })
                .subscribe(articles -> {
                    Log.i(TAG, "subscribe");
                    articlesView.stopProgress();
                    if (articles.size() > 0) {
                        articlesView.onLoadArticlesSuccessful(articles);
                    }
                });
    }
}
