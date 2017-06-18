package com.article.binhu.articlereader.di.module;

import android.app.Application;

import com.article.binhu.articlereader.MainApplication;
import com.article.binhu.articlereader.service.ArticleService;
import com.article.binhu.articlereader.ui.articles.ArticlesFragment;
import com.article.binhu.articlereader.ui.articles.ArticlesPresenter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ArticleServiceModule {
    Application application;
    String baseUrl;

    public ArticleServiceModule(MainApplication application, String baseUrl) {
        this.application = application;
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Cache provideHttpCache() {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    ArticleService provideArticleService(Retrofit retrofit) {
        return retrofit.create(ArticleService.class);
    }

    @Provides
    @Singleton
    ArticlesFragment provideArticleFragment() {
        return new ArticlesFragment();
    }

    @Provides
    @Singleton
    ArticlesPresenter provideArticlesPresenter(ArticlesFragment articlesView, ArticleService articleService) {
        return new ArticlesPresenter(articlesView, articleService);
    }
}
