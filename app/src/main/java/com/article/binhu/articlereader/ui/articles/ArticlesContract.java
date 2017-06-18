package com.article.binhu.articlereader.ui.articles;

import com.article.binhu.articlereader.base.IBasePresenter;
import com.article.binhu.articlereader.base.IBaseView;
import com.article.binhu.articlereader.model.Article;

import java.util.List;

/**
 * Interface for the Article list presenter and viewer
 * Created by binhu on 16.06.17.
 */

public interface ArticlesContract {

    interface IArticlesPresenter extends IBasePresenter<IArticlesView> {
        void loadArticles();
    }

    interface IArticlesView extends IBaseView<IArticlesPresenter> {
        void startProgress();

        void stopProgress();

        void onLoadArticlesSuccessful(List<Article> articles);

        void onLoadArticlesFailed();

        void onLoadArticlesNoUpdate();
    }
}
