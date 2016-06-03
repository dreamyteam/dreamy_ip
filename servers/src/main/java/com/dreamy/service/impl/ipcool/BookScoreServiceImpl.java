package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.BookScoreDao;
import com.dreamy.domain.ipcool.*;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.IndexSourceEnums;
import com.dreamy.enums.KeyWordEnums;
import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.service.iface.ipcool.KeyWordService;
import com.dreamy.service.iface.ipcool.PeopleChartService;
import com.dreamy.service.iface.mongo.BookIndexDataService;
import com.dreamy.utils.ArrayUtils;
import com.dreamy.utils.BeanUtils;
import com.dreamy.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/28.
 */
@Service
public class BookScoreServiceImpl implements BookScoreService {
    @Resource
    private BookScoreDao bookScoreDao;

    @Autowired
    private BookIndexDataService bookIndexDataService;

    @Autowired
    private KeyWordService keyWordService;

    @Autowired
    private PeopleChartService peopleChartService;

    @Override
    public void saveUpdate(BookScore bookScore) {

        BookScoreConditions conditions = new BookScoreConditions();
        conditions.createCriteria().andBookIdEqualTo(bookScore.getBookId()).andSourceEqualTo(bookScore.getSource());
        List<BookScore> list = bookScoreDao.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(list)) {
            BookScore old = list.get(0);
            if (bookScore.getCommentNum() > 0) {
                old.commentNum(bookScore.getCommentNum());
            }
            if (bookScore.getScore() > 0) {
                old.score(bookScore.getScore());
            }
            if (bookScore.getSaleSort() > 0) {
                old.saleSort(bookScore.getSaleSort());
            }
            bookScoreDao.update(old);
        } else {
            bookScoreDao.save(bookScore);
        }


    }

    @Override
    public List<BookScore> getList(BookScore bookScore, Page page) {
        Map<String, Object> params = BeanUtils.toQueryMap(bookScore);
        BookScoreConditions conditions = new BookScoreConditions();
        conditions.createCriteria().addByMap(params);
        if (page != null) {
            page.setTotalNum(bookScoreDao.countByExample(conditions));
            conditions.setPage(page);
        }
        return bookScoreDao.selectByExample(conditions);
    }


    @Override
    public List<BookScore> getByBookId(Integer bookId) {
        BookScoreConditions conditions = new BookScoreConditions();
        conditions.createCriteria().andBookIdEqualTo(bookId);

        return bookScoreDao.selectByExample(conditions);
    }


    @Override
    public String getBookHotIndexByBookId(Integer bookId) {
        List<BookScore> bookScores = getByBookId(bookId);
        Double hotScore = 10.0;
        if (CollectionUtils.isNotEmpty(bookScores)) {
            Map<Integer, Double> percentMap = getPercentMap(bookScores);
            for (BookScore bookScore : bookScores) {
                Integer commentNum = bookScore.getCommentNum();
                Double marketPercent = percentMap.get(bookScore.getSource());
                hotScore += marketPercent * commentNum;
            }

            hotScore = Math.log10(hotScore + (hotScore * getSearchIndexByBookId(bookId) / 300)) * 1000;
        }

        return "" + hotScore.intValue();
    }

    @Override
    public String getPropagateIndexByBookId(Integer bookId) {
        Double propagateIndex = 10.0;

        KeyWord keyWord = new KeyWord();
        keyWord.bookId(bookId);
        Page page = new Page();
        page.setPageSize(10);

        Map<Integer, Double> map = new HashMap<>();
        for (KeyWordEnums enums : KeyWordEnums.values()) {
            map.put(enums.getType(), enums.getPercent());
        }

        List<KeyWord> keyWords = keyWordService.getList(keyWord, page);
        if (CollectionUtils.isNotEmpty(keyWords)) {
            for (KeyWord word : keyWords) {
                if (!keyWord.getSource().equals(KeyWordEnums.weibo.getType())) {
                    propagateIndex += map.get(word.getSource()) * word.getIndexNum();
                }
            }

            propagateIndex = Math.log10(propagateIndex) * 1000;
        }
        return "" + propagateIndex.intValue();
    }

    @Override
    public String getDevelopIndexByRecord(BookView bookView) {
        Double developScore = 10.0;
        Integer hotIndex = bookView.getHotIndex();
        Integer propagationIndex = bookView.getPropagateIndex();

        developScore += (hotIndex + propagationIndex) * 0.5;

        List<PeopleChart> peopleChartList = peopleChartService.getListByBookId(bookView.getBookId());
        if (CollectionUtils.isNotEmpty(peopleChartList)) {
            int i = 0;
            Double sexScore = 0.0;
            for (PeopleChart peopleChart : peopleChartList) {
                sexScore += 15 * peopleChart.getAgeFirst() + 23 * peopleChart.getAgeScond() + 28 * peopleChart.getAgeThird() + 16 * peopleChart.getAgeFourth() + 8 * peopleChart.getAgeFifth();
                i++;
            }

            developScore *= sexScore / i;
        }

        developScore = Math.log10(developScore) * 1000;
        return "" + (developScore.intValue());
    }


    @Override
    public String getReputationIndexByBookId(Integer bookId) {
        List<BookScore> bookScores = getByBookId(bookId);
        Double reputationScore = 10.0;
        if (CollectionUtils.isNotEmpty(bookScores)) {
            Map<Integer, Double> percentMap = getPercentMap(bookScores);
            for (BookScore bookScore : bookScores) {
                Double score = bookScore.getScore();
                Double marketPercent = percentMap.get(bookScore.getSource());
                reputationScore += marketPercent * score;
            }

            reputationScore = Math.log10(reputationScore * 123.45) * 1000;
        }

        return "" + reputationScore.intValue();
    }

    public Map<Integer, Double> getPercentMap(List<BookScore> bookScores) {
        Map<Integer, Double> percentMap = new HashMap<>();
        Map<Integer, Double> map = new HashMap<>();

        for (CrawlerSourceEnums sourceEnums : CrawlerSourceEnums.values()) {
            map.put(sourceEnums.getType(), sourceEnums.getPercent());
        }

        if (bookScores.size() != 4) {
            Double totalPercent = 0.0;
            for (BookScore bookScore : bookScores) {
                totalPercent += map.get(bookScore.getSource());
            }

            for (BookScore bookScore : bookScores) {
                percentMap.put(bookScore.getSource(), map.get(bookScore.getSource()) / totalPercent);
            }
        } else {
            percentMap = map;
        }

        return percentMap;
    }


    public Double getSearchIndexByBookId(Integer bookId) {
        Double res = 10.0;
        List<BookIndexData> bookIndexDatas = bookIndexDataService.getByBookId(bookId);
        if (CollectionUtils.isNotEmpty(bookIndexDatas)) {
            //@todo 目前只取360
            for (BookIndexData bookIndexData : bookIndexDatas) {
                if (bookIndexData.getSource().equals(IndexSourceEnums.s360.getType())) {
                    if (bookIndexData.getOverviewJson() != null) {
                        String monthIndex = bookIndexData.getOverviewJson().getMonthIndex();
                        if (!monthIndex.equals("-")) {
                            res = Double.parseDouble(monthIndex);
                        }
                    }
                }
            }
        }

        return res;
    }
}
