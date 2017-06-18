package com.article.binhu.articlereader.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.article.binhu.articlereader.MainApplication;
import com.article.binhu.articlereader.R;
import com.article.binhu.articlereader.service.ArticleService;
import com.article.binhu.articlereader.ui.articles.ArticlesFragment;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    @Inject
    ArticleService articleService;

    @Inject
    ArticlesFragment articlesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.activity_home);
        MainApplication.getServiceComponent().inject(this);
        if (savedInstanceState == null) {
            Log.i(TAG, "onCreate: load fragment");
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content_container, articlesFragment, "ArticlesFragment")
                    .addToBackStack("BackStack")
                    .commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.i(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
