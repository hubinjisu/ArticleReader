package com.article.binhu.articlereader.ui.articleviewer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.article.binhu.articlereader.R;

public class ArticleViewerFragment extends Fragment {
    private static final String TAG = "ArticleViewerFragment";

    private WebView articleViewer;

    private String webUrl;

    public ArticleViewerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_article_viewer, container, false);
        articleViewer = (WebView)view.findViewById(R.id.article_viewer);
        Bundle bundle = getArguments();
        if (bundle != null) {
            webUrl = bundle.getString("web_url");
            articleViewer.loadUrl(webUrl);
            getActivity().getSupportFragmentManager().popBackStack();
        }
        return view;
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
