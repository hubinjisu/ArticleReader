package com.article.binhu.articlereader.ui.articles;

import com.article.binhu.articlereader.model.Article;
import com.article.binhu.articlereader.model.ArticleResponse;
import com.article.binhu.articlereader.model.RequestResponse;
import com.article.binhu.articlereader.service.ArticleService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.shadows.ShadowLog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for the ArticlesPresenter
 * Created by hubin on 2017/6/18.
 */
public class ArticlesPresenterTest {
    @Mock
    ArticleService articleService;
    @Mock
    ArticlesContract.IArticlesView articlesView;
    @Captor
    private ArgumentCaptor<List<Article>> captor;

    ArticlesPresenter articlesPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ShadowLog.stream = System.out;
        // For testing to transform the async to sync
        asyncToSync();
        articlesPresenter = new ArticlesPresenter(articlesView, articleService);
    }

    @Test
    public void testLoadArticlesSuccessful() throws Exception {
        // init the test data
        Article article = new Article();
        article.set_id("123");
        article.setWeb_url("test url");
        List<Article> articles = new ArrayList<>();
        articles.add(article);
        ArticleResponse articleResponse = new ArticleResponse();
        articleResponse.setDocs(articles);
        RequestResponse requestResponse = new RequestResponse();
        requestResponse.setResponse(articleResponse);

        // Mock service callback
        when(articleService.loadArticles(anyString())).thenReturn(Observable.just(requestResponse));
        articlesPresenter.loadArticles();

        // check the logic
        verify(articlesView).startProgress();
        verify(articleService).loadArticles(anyString());
        verify(articlesView).stopProgress();
        verify(articlesView).onLoadArticlesSuccessful(captor.capture());

        List<Article> captorArticles = captor.getValue();

        // check the returned data
        Assert.assertEquals(captorArticles.size(), 1);
        Assert.assertEquals(captorArticles.get(0).get_id(), "123");
    }

    @Test
    public void testLoadArticlesNoNewData() throws Exception {
        // init the test data
        Article article = new Article();
        article.set_id("123");
        article.setWeb_url("test url");
        List<Article> articles = new ArrayList<>();
        articles.add(article);
        ArticleResponse articleResponse = new ArticleResponse();
        articleResponse.setDocs(articles);
        RequestResponse requestResponse = new RequestResponse();
        requestResponse.setResponse(articleResponse);
        articlesPresenter.newestId = "123";

        // Mock service callback
        when(articleService.loadArticles(anyString())).thenReturn(Observable.just(requestResponse));
        articlesPresenter.loadArticles();

        // check the logic
        verify(articlesView).startProgress();
        verify(articleService).loadArticles(anyString());
        verify(articlesView).stopProgress();
        verify(articlesView).onLoadArticlesNoUpdate();
    }

    @Test
    public void testLoadArticlesFailed() throws Exception {
        // Mock service callback
        when(articleService.loadArticles(anyString())).thenReturn(Observable.error(new Throwable()));
        articlesPresenter.loadArticles();

        // check the logic
        verify(articlesView).startProgress();
        verify(articleService).loadArticles(anyString());
        verify(articlesView).stopProgress();
        verify(articlesView).onLoadArticlesFailed();
    }

    /**
     * Transform the asynchronous rxjava processing to synchronous
     */
    private void asyncToSync() {
        RxJavaPlugins.reset();
        RxAndroidPlugins.reset();

        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }
}