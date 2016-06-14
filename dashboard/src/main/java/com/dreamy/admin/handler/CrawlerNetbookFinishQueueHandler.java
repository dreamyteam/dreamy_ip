package com.dreamy.admin.handler;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.domain.ipcool.BookIndexHistory;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.BookRankEnums;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.IpTypeEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.mogodb.beans.history.TieBaHistory;
import com.dreamy.mogodb.beans.tieba.TieBa;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.BookIndexHistoryService;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.service.iface.mongo.NetBookInfoService;
import com.dreamy.service.iface.mongo.TieBaHistoryService;
import com.dreamy.service.iface.mongo.TieBaService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/27
 * Time: 上午10:48
 */
@Component
public class CrawlerNetbookFinishQueueHandler extends AbstractQueueHandler {
    private static final Logger Log = LoggerFactory.getLogger(CrawlerNetbookFinishQueueHandler.class);

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private BookViewService bookViewService;

    @Autowired
    private BookScoreService bookScoreService;

    @Autowired
    private RedisClientService redisClientService;


    @Autowired
    private BookIndexHistoryService bookIndexHistoryService;

    @Autowired
    private NetBookInfoService netBookInfoService;

    @Autowired
    private TieBaService tieBaService;

    @Autowired
    private TieBaHistoryService tieBaHistoryService;


    @Override
    public void consume(JSONObject jsonObject) {
        String bookIdStr = jsonObject.getString("bookId");
        Log.info("starting book over : " + bookIdStr);
        if (StringUtils.isNotEmpty(bookIdStr)) {
            Integer bookId = Integer.parseInt(bookIdStr);
            try {
                update(bookId);
            } catch (Exception e) {
                Log.error("update rank failed :" + bookIdStr, e);
            }
        }

    }

    public void update(Integer bookId) {
        BookView bookView = bookViewService.getByBookId(bookId);
        updateNet(bookId, bookView);
    }


    /**
     * 网络文学更新
     *
     * @param bookId
     * @param bookView
     */
    private void updateNet(Integer bookId, BookView bookView) {
        List<BookInfo> bookInfoList = bookInfoService.getListByIpId(bookId);
        if (CollectionUtils.isNotEmpty(bookInfoList)) {

            //计算指数
//            Integer hotIndex = getNewHotIndex(bookView);
//            Integer propagationIndex = getNewPropogationIndex(bookView);
//            Integer activeIndex = getNewActiveIndex(bookView);
//
//
//            bookView.hotIndex(hotIndex);
//            bookView.propagateIndex(propagationIndex);
//            bookView.activityIndex(activeIndex);
//
//            Integer developIndex = getNewDevelopIndex(bookView);
//            bookView.developIndex(developIndex);
//
//            Integer compositeIndex = getNewCompositeIndex(bookView);
//            bookView.compositeIndex(compositeIndex);
//
//            //更新指数
//            bookViewService.update(bookView);
//            updateHistoryIndex(bookView);

            //指数写入到redis用于排名
//            updateRank(bookView);

        }
    }


    /**
     * 获取新的火热指数
     *
     * @param bookView
     */
    private Integer getNewHotIndex(BookView bookView) {
        Integer hotIndex = bookView.getHotIndex();

        NetBookInfo netBookInfo = netBookInfoService.getById(bookView.getBookId());
        if (netBookInfo != null) {
            Double percent = CrawlerSourceEnums.qidian.getPercent();
            Integer totalClick = netBookInfo.getClickNum();
            Integer totalRecommendNum = netBookInfo.getRecommendNum();
            Integer ticketNum = netBookInfo.getTicketNum();

            Double searchIndex = bookScoreService.getSearchIndexByBookId(bookView.getBookId());
            Double temp = percent * (((Math.log10(totalClick) * Math.log10(totalRecommendNum)) + ticketNum)) * Math.log10(searchIndex);
            hotIndex = temp.intValue();
        }

        return hotIndex;
    }

    /**
     * 获取新的传播指数
     *
     * @param bookView
     */
    private Integer getNewPropogationIndex(BookView bookView) {
        try {
            String propagateIndex = bookScoreService.getPropagateIndexByBookId(bookView.getBookId());
            Integer index = Integer.parseInt(propagateIndex);
            if (index > 0) {
                return index;
            }
        } catch (Exception e) {
            Log.error("update  propatation index failed :" + bookView.getId(), e);
        }
        return bookView.getPropagateIndex();
    }

    /**
     * 活跃指数
     *
     * @param bookView
     * @return
     */
    private Integer getNewActiveIndex(BookView bookView) {
        TieBa tieBa = tieBaService.getById(bookView.getBookId());
        TieBaHistory tieBaHistory = tieBaHistoryService.getLatestHistoryByBookId(bookView.getBookId());

        Integer temp = (1 + tieBa.getPostNum() / tieBa.getFollowNum() - tieBaHistory.getPostNum() / tieBaHistory.getFollowNum());
        return (tieBa.getFollowNum() + tieBa.getFollowNum() / tieBa.getPopularitySort()) * temp * temp;
    }

    /**
     * 获取新的潜力指数
     *
     * @param bookView
     */
    private Integer getNewDevelopIndex(BookView bookView) {
        Integer index = bookView.getDevelopIndex();
        Integer hotIndex = bookView.getHotIndex();
        Integer propagationIndex = bookView.getPropagateIndex();



        return index;
    }


    /**
     * 获取新的综合指数
     *
     * @param bookView
     */
    private Integer getNewCompositeIndex(BookView bookView) {
        try {
            Integer hotIndex = bookView.getHotIndex();
            Integer propagationIndex = bookView.getPropagateIndex();
            Integer reputationIndex = bookView.getReputationIndex();
            Integer developIndex = bookView.getDevelopIndex();

            Double compositeIndex = 0.1 * (3 * (hotIndex + propagationIndex) + 2 * (reputationIndex + developIndex));

            Integer index = compositeIndex.intValue();
            if (index > 0) {
                return index;
            }
        } catch (Exception e) {
            Log.error("update composite index failed :" + bookView.getId(), e);
        }

        return bookView.getCompositeIndex();
    }

    /**
     * @param bookView
     */
    private void updateHistoryIndex(BookView bookView) {
        bookIndexHistoryService.delByBookIdAndDate(bookView.getBookId(), new Date());
        BookIndexHistory history = new BookIndexHistory();
        history.setHotIndex(bookView.getHotIndex());
        history.setActivityIndex(bookView.getActivityIndex());
        history.setCompositeIndex(bookView.getCompositeIndex());
        history.setPropagateIndex(bookView.getPropagateIndex());
        history.setDevelopIndex(bookView.getDevelopIndex());
        history.setBookId(bookView.getBookId());
        history.setStatus(1);
        bookIndexHistoryService.save(history);
    }

    public void updateRank(BookView bookView) {
        redisClientService.zadd(BookRankEnums.composite.getCacheKey(), bookView.getCompositeIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.develop.getCacheKey(), bookView.getDevelopIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.propagation.getCacheKey(), bookView.getPropagateIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.hot.getCacheKey(), bookView.getHotIndex(), bookView.getBookId().toString());
    }

}
