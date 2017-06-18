package com.article.binhu.articlereader.ui.articles;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.article.binhu.articlereader.R;
import com.article.binhu.articlereader.model.Article;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Data Adapter for the article list
 * Created by binhu on 16.06.17.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> implements View.OnClickListener {

    private static final String TAG = "ArticleAdapter";
    private List<Article> articles;
    private Context context;
    private OnItemClickListener clickListener = null;

    public ArticleAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.view_article_item, parent, false);
        convertView.setOnClickListener(this);
        return new ArticleViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        if (articles == null || articles.size() <= position) {
            Log.i(TAG, "onBindViewHolder: null");
            return;
        }
        final Article item = articles.get(position);
        if (item == null) {
            return;
        }
        Log.i(TAG, "onBindViewHolder:" + item.getHeadline().getMain());

        try {
            holder.itemView.setTag(position);
            if (item.getHeadline() != null) {
                holder.articleHeadline.setText(item.getHeadline().getMain());
            }
            holder.articleSnippet.setText(item.getSnippet());
            holder.articleOriginal.setText(item.getSource());
            holder.articlePubDate.setText(item.getPub_date());
            if (item.getMultimedia() != null && item.getMultimedia().size() > 0) {
                holder.articleImg.setVisibility(View.VISIBLE);
                String hostUrl =
                        item.getWeb_url().substring(0, item.getWeb_url().indexOf("com") + 4);
                String imgUrl = hostUrl + item.getMultimedia().get(0).getUrl();
                Log.i(TAG, "onBindViewHolder: imgUrl:" + imgUrl);
                Glide.with(context).load(imgUrl).into(holder.articleImg);
            }
            else {
                holder.articleImg.setVisibility(View.GONE);
            }

        }
        catch (Exception e) {
        }
    }

    @Override
    public int getItemCount() {
        if (articles != null) {
            return articles.size();
        }
        else {
            return 0;
        }
    }

    public void resetData(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if (clickListener != null) {
            clickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        ImageView articleImg;
        TextView articleHeadline;
        TextView articleSnippet;
        TextView articlePubDate;
        TextView articleOriginal;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            articleImg = (ImageView) itemView.findViewById(R.id.article_img);
            articleHeadline = (TextView) itemView.findViewById(R.id.article_headline);
            articleSnippet = (TextView) itemView.findViewById(R.id.article_snippet);
            articlePubDate = (TextView) itemView.findViewById(R.id.article_pub_date);
            articleOriginal = (TextView) itemView.findViewById(R.id.article_original);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
