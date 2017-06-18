package com.article.binhu.articlereader.di.component;


import com.article.binhu.articlereader.di.module.ArticleServiceModule;
import com.article.binhu.articlereader.ui.HomeActivity;
import com.article.binhu.articlereader.ui.articles.ArticlesFragment;
import com.article.binhu.articlereader.ui.articles.ArticlesPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ArticleServiceModule.class})
public interface ServiceComponent {
    void inject(ArticlesPresenter articlesPresenter);

    void inject(HomeActivity homeActivity);

    void inject(ArticlesFragment articlesFragment);
}
