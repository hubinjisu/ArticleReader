package com.article.binhu.articlereader.base;

public interface IBasePresenter<T extends IBaseView> {
    void start();
    void release();
}
