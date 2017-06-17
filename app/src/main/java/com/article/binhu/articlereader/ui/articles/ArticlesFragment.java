package com.article.binhu.articlereader.ui.articles;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.article.binhu.articlereader.R;
import com.article.binhu.articlereader.model.Article;
import com.article.binhu.articlereader.ui.articleviewer.ArticleViewerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binhu on 16.06.17.
 */

public class ArticlesFragment extends Fragment implements ArticlesContract.IArticlesView{

    private static final String TAG = "ArticlesFragment";
    private ArticlesContract.IArticlesPresenter articlesPresenter;
    private RecyclerView articleListView;
    private ProgressBar progressBar;
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
        progressBar = (ProgressBar)view.findViewById(R.id.loading_progress);
        articles = new ArrayList<Article>();
        articleAdapter = new ArticleAdapter(getActivity(), articles);
        articleListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        articleListView.setAdapter(articleAdapter);

        articleAdapter.setOnItemClickListener(new ArticleAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view , int position){
                String webUrl = articles.get(position).getWeb_url();
                Log.d(TAG, "onItemClick: " + webUrl);
                Bundle bundle = new Bundle();
                bundle.putString("web_url", webUrl);
                ArticleViewerFragment fragment = new ArticleViewerFragment();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.content_container, fragment, "ArticleViewerFragment")
                        .addToBackStack("BackStack")
                        .commit();
            }
        });
//        articleListView.addItemDecoration(new VerticalItemDecoration(R.dimen.interval_space));
        articlesPresenter.loadArticles();
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        Log.i(TAG, "onStop");
        super.onStop();
    }

    @Override
    public void onStart() {
        Log.i(TAG, "onStart");
        super.onStart();
    }

    @Override
    public void startProgress() {
        showProgress(true);
    }

    @Override
    public void stopProgress() {
        showProgress(false);
    }

    @Override
    public void onLoadArticlesSuccessful(List<Article> articles) {
        Log.i(TAG, "onLoadArticlesSuccessful: ");
        this.articles.addAll(0, articles);
        Toast.makeText(getActivity(), R.string.load_successful, Toast.LENGTH_LONG).show();
        articleAdapter.resetData(this.articles);
    }

    @Override
    public void onLoadArticlesFailed() {
        Toast.makeText(getActivity(), R.string.load_failed, Toast.LENGTH_LONG).show();
    }

    private void showProgress(final boolean show)
    {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
        articleListView.setVisibility(show ? View.GONE : View.VISIBLE);
        articleListView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                articleListView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        progressBar.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
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
