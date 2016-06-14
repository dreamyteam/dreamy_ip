package com.dreamy.crawler.service;

import com.dreamy.domain.ipcool.*;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.IpTypeEnums;
import com.dreamy.enums.OperationEnums;
import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.mogodb.beans.OverviewJson;
import com.dreamy.mogodb.beans.history.*;
import com.dreamy.mogodb.beans.tieba.TieBa;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.*;
import com.dreamy.service.iface.mongo.*;
import com.dreamy.service.mq.QueueService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.StringUtils;
import com.dreamy.utils.TimeUtils;
import com.dreamy.utils.asynchronous.AsynchronousService;
import com.dreamy.utils.asynchronous.ObjectCallable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ganlv on 11/9/14.
 */
@Service
public class CrawlerServiceImpl implements CrawlerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerServiceImpl.class);
    @Autowired
    private QueueService queueService;
    @Autowired
    private RedisClientService redisClientService;


    @Value("${queue_crawler_over}")
    private String queueName;


    @Autowired
    private IpBookService ipBookService;

    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    BookCrawlerInfoService bookCrawlerInfoService;
    @Autowired
    BookScoreService bookScoreService;
    @Autowired
    NetBookInfoService netBookInfoService;

    @Autowired
    private BookTagsService bookTagsService;
    @Autowired
    private BookViewService bookViewService;

    @Resource
    KeyWordHistoryService keyWordHistoryService;

    @Resource
    NewsMediaHistoryService newsMediaHistoryService;
    @Resource
    BookIndexDataHistoryService bookIndexDataHistoryService;
    @Resource
    NetBookDataHistoryService netBookDataHistoryService;

    @Autowired
    TieBaHistoryService tieBaHistoryService;

    @Autowired
    BookScoreHistoryService bookScoreHistoryService;


    @Override
    public void operation(String operation, String key, final BookInfo bookInfo, String title, Integer bookId, String url, String isbn, final Integer type) {
        try {
            if (bookInfo != null) {
                if (operation.equals(OperationEnums.crawler.getCode())) {
                    IpBook ipBook = new IpBook();
                    ipBook.setType(1);
                    ipBook.setStatus(1);
                    ipBook.setTitle(title);
                    ipBook.name(title);
                    ipBook.setCode(bookInfo.getISBN());
                    ipBookService.save(ipBook);
                    bookId = ipBook.getId();
                    BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo();
                    bookCrawlerInfo.status(1);
                    bookCrawlerInfo.bookId(ipBook.getId());
                    bookCrawlerInfo.setSource(type);
                    if (StringUtils.isEmpty(url)) {
                        bookCrawlerInfo.url(bookInfo.getUrl());
                    } else {
                        bookCrawlerInfo.url(url);
                    }
                    bookCrawlerInfoService.save(bookCrawlerInfo);
                    bookInfo.setId(bookInfo.getISBN() + "_" + type);
                } else {
                    bookInfo.setId(isbn + "_" + type);
                }
                score(bookInfo, type, bookId);
                bookInfo.setSource(type);
                bookInfo.setIpId(bookId);
                bookInfoService.updateInser(bookInfo);
            }

        } catch (Exception e) {
            LOGGER.error("Operation is error bookid is " + bookId, e);
        } finally {
            check(key, bookId, IpTypeEnums.chuban.getType());
        }

    }


    public void operationBook(String operation, String key, BookInfo bookInfo, Integer bookId, String url, String isbn, Integer type) {
        try {
            if (bookInfo != null) {
                if (operation.equals(OperationEnums.crawler.getCode())) {
                    BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo();
                    bookCrawlerInfo.status(1);
                    bookCrawlerInfo.bookId(bookId);
                    bookCrawlerInfo.setSource(type);
                    if (StringUtils.isEmpty(url)) {
                        bookCrawlerInfo.url(bookInfo.getUrl());
                    } else {
                        bookCrawlerInfo.url(url);
                    }
                    bookCrawlerInfoService.save(bookCrawlerInfo);
                    bookInfo.setId(bookInfo.getISBN() + "_" + type);
                } else {
                    bookInfo.setId(isbn + "_" + type);
                }
                score(bookInfo, type, bookId);
                bookInfo.setSource(type);
                bookInfo.setIpId(bookId);
                bookInfoService.updateInser(bookInfo);
            }
        } catch (Exception e) {
            LOGGER.error("operationBook is error bookid is " + bookId, e);
        } finally {
            check(key, bookId, IpTypeEnums.chuban.getType());
        }

    }

    @Override
    public void check(String key, int bookId, Integer ipType) {
        long num = redisClientService.incrBy(key, -1L);
        if (num < 1) {
            redisClientService.del(key);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("bookId", bookId);
            queueService.push(queueName, map);
        }
    }


    @Override
    public void operationNetBook(String operation, String key, NetBookInfo bookInfo, Integer bookId, Integer type) {
        try {
            if (bookInfo != null) {
                netBookInfoService.updateInser(bookInfo);
                saveNetBookDataHistory(bookInfo, type);
                if (operation.equals(OperationEnums.crawler.getCode())) {
                    createTag(bookId, bookInfo.getLabel());
                }
            }
        } catch (Exception e) {
            LOGGER.error("operationNetBook is error bookId is " + bookId, e);
        } finally {
            check(key, bookId,IpTypeEnums.net.getType());
        }
    }

    @Override
    public void saveKeyWordHistory(final KeyWord keyWord) {
        AsynchronousService.submit(new ObjectCallable() {
            @Override
            public Object run() throws Exception {
                KeyWordHistory history = new KeyWordHistory();
                history.setBookId(keyWord.getBookId());
                history.setSource(keyWord.getSource());
                history.setNum(keyWord.getIndexNum());
                history.setCreateDate(TimeUtils.toString(null, new Date()));
                history.setId(keyWord.getBookId() + "-" + keyWord.getSource() + "-" + TimeUtils.toString(null, new Date()));
                keyWordHistoryService.updateInser(history);
                return null;
            }
        });
    }

    @Override
    public void saveNewsMediaHistory(final NewsMedia newsMedia) {
        AsynchronousService.submit(new ObjectCallable() {
            @Override
            public Object run() throws Exception {
                NewsMediaHistory history = new NewsMediaHistory();
                history.setSource(newsMedia.getSource());
                history.setNum(newsMedia.getNum());
                history.setCreateDate(TimeUtils.toString(null, new Date()));
                history.setId(newsMedia.getBookId() + "-" + newsMedia.getSource() + "-" + TimeUtils.toString(null, new Date()));
                newsMediaHistoryService.updateInser(history);
                return null;
            }
        });
    }

    @Override
    public void saveBookIndexDataHistory(final BookIndexData bookIndexData) {
        AsynchronousService.submit(new ObjectCallable() {
            @Override
            public Object run() throws Exception {
                BookIndexDataHistory history = new BookIndexDataHistory();
                history.setSource(bookIndexData.getSource());
                history.setFemale(bookIndexData.getFemale());
                history.setMale(bookIndexData.getMale());
                history.setBookId(bookIndexData.getBookId());
                history.setCreateDate(TimeUtils.toString(null, new Date()));
                if (bookIndexData.getOverviewJson() != null) {
                    OverviewJson overviewJson = bookIndexData.getOverviewJson();
                    history.setMonthIndex(Integer.valueOf(overviewJson.getMonthIndex()));
                    history.setWeekIndex(Integer.valueOf(overviewJson.getWeekIndex()));
                    history.setMonthYearRatio(overviewJson.getMonthYearRatio());
                    history.setMonthChainRatio(overviewJson.getMonthChainRatio());
                    history.setWeekChainRatio(overviewJson.getWeekChainRatio());
                    history.setWeekYearRatio(overviewJson.getWeekYearRatio());
                }
                if (bookIndexData.getMedia() != null) {
                    String str[] = bookIndexData.getMedia();
                    int length = str.length;
                    String mediaNum = str[length - 1];
                    history.setMediaNum(Integer.valueOf(mediaNum));


                }
                if (bookIndexData.getIndex() != null) {
                    String index[] = bookIndexData.getIndex();
                    int length = index.length;
                    String searchNum = index[length - 1];
                    history.setSearchNum(Integer.valueOf(searchNum));


                }
                history.setId(bookIndexData.getBookId() + "-" + bookIndexData.getSource() + "-" + TimeUtils.toString(null, new Date()));
                bookIndexDataHistoryService.updateInser(history);
                return null;
            }
        });
    }

    @Override
    public void saveNetBookDataHistory(final NetBookInfo netBookInfo, final Integer type) {
        AsynchronousService.submit(new ObjectCallable() {
            @Override
            public Object run() throws Exception {
                NetBookDataHistory history = new NetBookDataHistory();
                history.setType(type);
                history.setBookId(netBookInfo.getBookId());
                history.setClickNum(netBookInfo.getClickNum());
                history.setCommentNum(netBookInfo.getCommentNum());
                history.setRecommendNum(netBookInfo.getRecommendNum());
                history.setMonthSort(netBookInfo.getMonthSort());
                history.setScore(netBookInfo.getScore());
                history.setCreateDate(TimeUtils.toString(null, new Date()));
                history.setId(netBookInfo.getBookId() + "-" + type + "-" + TimeUtils.toString(null, new Date()));
                netBookDataHistoryService.updateInser(history);
                return null;
            }
        });
    }

    @Override
    public void saveTieBaHistory(final TieBa tieBa) {
        AsynchronousService.submit(new ObjectCallable() {
            @Override
            public Object run() throws Exception {
                TieBaHistory tieBaHistory = new TieBaHistory();
                tieBaHistory.setPopularitySort(tieBa.getPopularitySort());
                tieBaHistory.setFollowNum(tieBa.getFollowNum());
                tieBaHistory.setPostNum(tieBa.getPostNum());
                tieBaHistory.setDate(TimeUtils.toString(null, new Date()));
                tieBaHistory.setBookId(tieBa.getBookId());
                tieBaHistory.setId(tieBa.getBookId() + "-" + TimeUtils.toString(null, new Date()));
                tieBaHistoryService.updateInser(tieBaHistory);
                return null;
            }
        });
    }


    private void createTag(final Integer bookId, final String tags) {

        AsynchronousService.submit(new ObjectCallable() {
            @Override
            public Object run() throws Exception {
                BookView oldBookView = bookViewService.getByBookId(bookId);
                NetBookInfo bookInfo = netBookInfoService.getById(bookId);
                if (oldBookView == null && bookInfo != null) {
                    BookView bookView = new BookView();
                    bookView.setName(bookInfo.getTitle());
                    bookView.setImageUrl(bookInfo.getImage());
                    bookView.introduction(bookInfo.getInfo());
                    bookView.setAuthor(bookInfo.getAuthor());
                    bookView.setType(2);
                    bookView.setStatus(0);
                    bookView.bookId(bookId);
                    bookViewService.save(bookView);
                    if (StringUtils.isNotEmpty(tags)) {
                        String arr[] = tags.split(",");
                        int size = arr.length;
                        int tagId = 0;
                        for (int i = 0; i < size; i++) {
                            List<BookTags> bookTagsList = bookTagsService.getByName(arr[i]);

                            if (CollectionUtils.isEmpty(bookTagsList)) {
                                BookTags bookTags = new BookTags();
                                bookTags.name(arr[i]);
                                bookTagsService.save(bookTags);
                                tagId = bookTags.getId();
                            } else {
                                tagId = bookTagsList.get(0).getId();
                            }
                            BookTagsMap bookTagsMap = new BookTagsMap();
                            bookTagsMap.bookId(bookId).tagId(tagId);
                            bookTagsService.saveTagMap(bookTagsMap);
                        }
                    }
                }
                return null;
            }
        });

    }

    private void score(final BookInfo bookInfo, final Integer type, final Integer bookId) {
        AsynchronousService.submit(new ObjectCallable() {
            @Override
            public Object run() throws Exception {
                BookScore bookScore = new BookScore();
                bookScore.source(type);
                bookScore.status(0);
                bookScore.bookId(bookId);
                bookScore.commentNum(bookInfo.getCommentNum() != null ? bookInfo.getCommentNum() : 0);
                bookScore.saleSort(bookInfo.getSaleSort() != null ? bookInfo.getSaleSort() : 0);
                if (type == CrawlerSourceEnums.amazon.getType()) {
                    bookScore.score(bookInfo.getScore() != null ? bookInfo.getScore() * 20.0 : 0.0);
                } else if (type == CrawlerSourceEnums.jd.getType()) {
                    bookScore.score(bookInfo.getScore() != null ? bookInfo.getScore() : 0.0);
                } else if (type == CrawlerSourceEnums.dangdang.getType()) {
                    bookScore.score(bookInfo.getScore() != null ? bookInfo.getScore() : 0.0);
                } else if (type == CrawlerSourceEnums.douban.getType()) {
                    bookScore.score(bookInfo.getScore() != null ? bookInfo.getScore() * 10.0 : 0.0);
                }
                bookScoreService.saveUpdate(bookScore);
                BookScoreHistory history = new BookScoreHistory();
                history.setBookId(bookId);
                history.setSource(type);
                history.setCommentNum(bookScore.getCommentNum());
                history.setSaleSort(bookScore.getSaleSort());
                history.setScore(bookScore.getScore());
                bookScoreHistoryService.updateInser(history);
                return null;
            }
        });
    }
}
