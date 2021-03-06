# ArticleReader
This is a sample project to display a list of news items fetched from the New York Times article search API and allows browsing the full article in a webview. The following is the key points in the project:
1. MVP architecture
2. Dagger2 to make the dependency injection
3. Retrofit2 + Okhttp + Gson + Rxjava2 to make the HTTP request for service API
4. Glide to load the image asynchronous
5. RecycleView + CardView to display the article data from server
6. SwipeRefreshLayout to pull down list to refresh the data
7. Webview to display the web page
8. In the onSaveInstanceState() method to handle the data cache and avoid the repeated data request
9. Junit + Mokito to make unit test of the presenter
10. In unit test transform async operation to sync operation

# Unit Test Result:
![image](https://github.com/hubinjisu/images/blob/master/images/article_reader_unit_test.png)

# Screen Layout:
![image](https://github.com/hubinjisu/images/blob/master/images/article_reader.gif)
