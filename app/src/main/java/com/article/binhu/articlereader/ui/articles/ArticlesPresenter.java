package com.article.binhu.articlereader.ui.articles;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.article.binhu.articlereader.MainApplication;
import com.article.binhu.articlereader.model.Article;
import com.article.binhu.articlereader.model.ArticleResponse;
import com.article.binhu.articlereader.model.RequestResponse;
import com.article.binhu.articlereader.service.ArticleService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by binhu on 16.06.17.
 */

public class ArticlesPresenter implements ArticlesContract.IArticlesPresenter {

    private static final String TAG = "ArticlesPresenter";
    private ArticlesContract.IArticlesView articlesView;

    @Inject
    ArticleService articleService;

    public ArticlesPresenter(ArticlesContract.IArticlesView articlesView) {
        this.articlesView = articlesView;
        ((MainApplication) ((Fragment) articlesView).getActivity().getApplication()).getServiceComponent().inject(this);

    }

    @Override
    public void start() {

    }

    @Override
    public void release() {

    }

    @Override
    public void loadArticles() {
        Log.i(TAG, "loadArticles: ");
        articleService.loadArticles("d7d008858ee64d3fa5f8d8b6a839b435")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<RequestResponse, ArticleResponse>() {
                    @Override
                    public ArticleResponse apply(RequestResponse requestResponse) throws Exception {
                        Log.i(TAG, "article: status: " + requestResponse.getStatus());
                        Log.i(TAG, "article: copyright: " + requestResponse.getCopyright());
                        return requestResponse.getResponse();
                    }
                })
                .subscribe(new Consumer<ArticleResponse>() {
                    @Override
                    public void accept(ArticleResponse articleResponse) throws Exception {
                        Log.i(TAG, "accept: ");
                        if (articleResponse != null) {
                            Log.i(TAG, "article: getResponse: ");

                            for (Article article : articleResponse.getDocs()) {
                                Log.i(TAG, "call: article:" + article.getWeb_url());
                            }
                            articlesView.onLoadArticlesSuccessful(articleResponse.getDocs());
                        }
                    }
                });
    }
}
