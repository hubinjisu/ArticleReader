package com.article.binhu.articlereader.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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

    private TextView textView;
    private ImageView imageView;

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
//        textView = (TextView)findViewById(R.id.test_service);
//        imageView = (ImageView)findViewById(R.id.test_img);
//        Picasso.with(this).load("https://www.nytimes.com/images/2017/06/18/sports/18LYME-GOLF/18LYME-GOLF-articleLarge.jpg").into(imageView);
//        Picasso.with(this).load(R.mipmap.ic_launcher_round).into(imageView);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG, "onClick: ");
//                articleService.loadArticles("d7d008858ee64d3fa5f8d8b6a839b435")
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .map(new Function<RequestResponse, ArticleResponse>() {
//                            @Override
//                            public ArticleResponse apply(RequestResponse requestResponse) throws Exception {
//                                Log.i(TAG, "article: status: " + requestResponse.getStatus());
//                                Log.i(TAG, "article: copyright: " + requestResponse.getCopyright());
//                                return requestResponse.getResponse();
//                            }
//                        })
//                        .subscribe(new Consumer<ArticleResponse>() {
//                            @Override
//                            public void accept(ArticleResponse articleResponse) throws Exception {
//                                Log.i(TAG, "accept: ");
//                                if (articleResponse != null) {
//                                    Log.i(TAG, "article: getResponse: ");
//
//                                    for (Article article : articleResponse.getDocs()) {
//                                        Log.i(TAG, "call: article:" + article.getWeb_url());
//                                    }
//                                }
//                            }
//                        });
//            }
//        });


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.i(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
