package com.article.binhu.articlereader.ui.articleviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.article.binhu.articlereader.R;

/**
 * Show the web page in a web viewer
 * Created by binhu on 17.06.17.
 */
public class ArticleViewerFragment extends Fragment {
    private static final String TAG = "ArticleViewerFragment";

    public ArticleViewerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_article_viewer, container, false);
        WebView articleViewer = (WebView) view.findViewById(R.id.article_viewer);
//        // Enable Javascript
//        WebSettings webSettings = articleViewer.getSettings();
//        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
//        articleViewer.setWebViewClient(new WebViewClient());
        Bundle bundle = getArguments();
        if (bundle != null) {
            String webUrl = bundle.getString("web_url");
            Log.i(TAG, "web url: " + webUrl);
            articleViewer.loadUrl(webUrl);
            getActivity().getSupportFragmentManager().popBackStack();
        }
        return view;
    }
}
