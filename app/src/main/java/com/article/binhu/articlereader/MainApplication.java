package com.article.binhu.articlereader;

import android.app.Application;

import com.article.binhu.articlereader.di.component.DaggerServiceComponent;
import com.article.binhu.articlereader.di.component.ServiceComponent;
import com.article.binhu.articlereader.di.module.ArticleServiceModule;

/**
 * Created by binhu on 16.06.17.
 */

public class MainApplication extends Application {
    private static ServiceComponent serviceComponent;

    public MainApplication() {
        // Initialize the dependencies
        serviceComponent = DaggerServiceComponent.builder()
                .articleServiceModule(new ArticleServiceModule(this, "https://api.nytimes.com/svc/search/v2/"))
                .build();
    }

    /**
     * Provide the interface to get the dependencies
     * @return
     */
    public static ServiceComponent getServiceComponent() {
        return serviceComponent;
    }
}
