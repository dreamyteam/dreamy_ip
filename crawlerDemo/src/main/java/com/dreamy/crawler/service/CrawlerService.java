package com.dreamy.crawler.service;

import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.domain.ipcool.NewsMedia;
import com.dreamy.enums.OperationEnums;
import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.mogodb.beans.qidian.QiDianFan;
import com.dreamy.mogodb.beans.tieba.TieBa;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/6.
 */
public interface CrawlerService {


    public void operation(String operation, String key, BookInfo bookInfo, String title, Integer bookId, String url, String isbn, Integer type);

    public void operationBook(String operation, String key, BookInfo bookInfo, Integer bookId, String url, String isbn, Integer type);

    public void check(String key, int bookId, Integer ipType);


    public void operationNetBook(String operation, String key, NetBookInfo bookInfo, Integer bookId, Integer type);


    public void saveKeyWordHistory(KeyWord keyWord);

    public void saveNewsMediaHistory(NewsMedia newsMedia);

    public void saveBookIndexDataHistory(BookIndexData bookIndexData);


    public void saveNetBookDataHistory(NetBookInfo netBookInfo, Integer type);

    public void saveTieBaHistory(TieBa tieBa);


    public void push(String isbn, Integer bookId, String url);

    /**
     * 计算起点性别比例
     *
     * @param qiDianFan
     */
    public void calculateQiDianSex(QiDianFan qiDianFan);


}
