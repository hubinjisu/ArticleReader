package com.article.binhu.articlereader.ui.articles;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.article.binhu.articlereader.R;
import com.article.binhu.articlereader.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binhu on 16.06.17.
 */

public class ArticlesFragment extends Fragment implements ArticlesContract.IArticlesView{

    private static final String TAG = "ArticlesFragment";
    private ArticlesContract.IArticlesPresenter articlesPresenter;
    private RecyclerView articleListView;
    private ArticleAdapter articleAdapter;
    private View view;
    private List<Article> articles;

    public ArticlesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        view = inflater.inflate(R.layout.fragment_article_list, container, false);
        articlesPresenter = new ArticlesPresenter(this);
        articleListView = (RecyclerView) view.findViewById(R.id.article_list);
        articles = new ArrayList<Article>();
        articleAdapter = new ArticleAdapter(getActivity(), articles);
        articleListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        articleListView.setAdapter(articleAdapter);
        articleListView.addItemDecoration(new VerticalItemDecoration(R.dimen.interval_space));
        articlesPresenter.loadArticles();
        return view;
    }

    @Override
    public void startProgress() {

    }

    @Override
    public void stopProgress() {

    }

    @Override
    public void onLoadArticlesSuccessful(List<Article> articles) {
        Log.i(TAG, "onLoadArticlesSuccessful: ");
//        this.articles = articles;
//        articleAdapter.notifyDataSetChanged();
        articleAdapter.resetData(articles);
    }

    @Override
    public void onLoadArticlesFailed() {

    }

    public class VerticalItemDecoration extends RecyclerView.ItemDecoration {
        private final int mVerticalSpaceHeight;

        public VerticalItemDecoration(int mVerticalSpaceHeight) {
            this.mVerticalSpaceHeight = mVerticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.top = mVerticalSpaceHeight;
        }
    }
}
