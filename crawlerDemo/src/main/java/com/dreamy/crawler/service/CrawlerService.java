package com.dreamy.crawler.service;

import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.domain.ipcool.NewsMedia;
import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.beans.NetBookInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/6.
 */
public interface CrawlerService {


    public void pushAll(String isbn, String url, Integer bookId);


    public void Operation(String operation, String key, BookInfo bookInfo,String title,Integer bookId,String url,String isbn,Integer type);

    public void operationBook(String operation,String key,BookInfo bookInfo,Integer bookId,String url,String isbn,Integer type);

    public void check(String key, int bookId);


    public void operationNetBook(String operation,String key, NetBookInfo bookInfo, Integer bookId);


    public void saveKeyWordHistory(KeyWord keyWord);

    public void saveNewsMediaHistory(NewsMedia newsMedia);

    public void saveBookIndexDataHistory(BookIndexData bookIndexData);


}
