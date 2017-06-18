package com.article.binhu.articlereader.ui.articles;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.article.binhu.articlereader.MainApplication;
import com.article.binhu.articlereader.R;
import com.article.binhu.articlereader.model.Article;
import com.article.binhu.articlereader.ui.articleviewer.ArticleViewerFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by binhu on 16.06.17.
 */

public class ArticlesFragment extends Fragment implements ArticlesContract.IArticlesView {

    private static final String TAG = "ArticlesFragment";

    @Inject
    ArticlesPresenter articlesPresenter;

    private RecyclerView articleListView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;
    private ArticleAdapter articleAdapter;
    private View view;
    private List<Article> articles;

    public ArticlesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        MainApplication.getServiceComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        view = inflater.inflate(R.layout.fragment_article_list, container, false);
        articleListView = (RecyclerView) view.findViewById(R.id.article_list);
        progressBar = (ProgressBar)view.findViewById(R.id.loading_progress);
        refreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.article_refresh);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: ");
        if (savedInstanceState != null) {
            articles = savedInstanceState.getParcelableArrayList("saved_data");
        }
        if (articles == null) {
            Log.i(TAG, "savedData == null");
            articles = new ArrayList<Article>();
            articlesPresenter.loadArticles();
        } else {
            Log.i(TAG, "savedData: " + articles.size());
            showProgress(false);
        }
        articleAdapter = new ArticleAdapter(getActivity(), articles);
        articleListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        articleListView.setAdapter(articleAdapter);
//        articleListView.addItemDecoration(new VerticalItemDecoration(R.dimen.interval_space));
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

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "onRefresh");
                articlesPresenter.loadArticles();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "onSaveInstanceState");
        outState.putParcelableArrayList("saved_data", (ArrayList<? extends Parcelable>) articles);
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
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadArticlesFailed() {
        Toast.makeText(getActivity(), R.string.load_failed, Toast.LENGTH_LONG).show();
        refreshLayout.setRefreshing(false);
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
