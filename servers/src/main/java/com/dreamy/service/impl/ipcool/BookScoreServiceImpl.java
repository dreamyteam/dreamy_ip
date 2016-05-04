package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.BookScoreDao;
import com.dreamy.domain.ipcool.BookScore;
import com.dreamy.domain.ipcool.BookScoreConditions;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.IndexSourceEnums;
import com.dreamy.enums.KeyWordEnums;
import com.dreamy.mogodb.beans.Book;
import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.service.iface.ipcool.BookIndexDataService;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.service.iface.ipcool.KeyWordService;
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

    @Override
    public void save(BookScore bookScore) {
        bookScoreDao.save(bookScore);
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
    public String getBookHotIndexByBookId(Integer bookId) {
        List<BookScore> bookScores = getByBookId(bookId);
        Double hotScore = 0.0;
        if (CollectionUtils.isNotEmpty(bookScores)) {
            Map<Integer, Double> percentMap = getPercentMap(bookScores);
            for (BookScore bookScore : bookScores) {
                Integer commentNum = bookScore.getCommentNum();
                Double marketPercent = percentMap.get(bookScore.getSource());
                hotScore += marketPercent * commentNum;
            }

            hotScore = hotScore / 3.2 + getSearchIndexByBookId(bookId) * 4;
        }

        return "" + hotScore.intValue();
    }

    @Override
    public List<BookScore> getByBookId(Integer bookId) {
        BookScoreConditions conditions = new BookScoreConditions();
        conditions.createCriteria().andBookIdEqualTo(bookId);

        return bookScoreDao.selectByExample(conditions);
    }

    @Override
    public String getPropagateIndexByBookId(Integer bookId) {
        Double propagateIndex = 0.0;

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
                propagateIndex += map.get(word.getSource()) * word.getIndexNum();
            }

        }
        return "" + propagateIndex.intValue();
    }

    @Override
    public String getDevelopIndexByRecord(BookView bookView) {
        Double developScore = 0.0;
        Integer hotIndex = bookView.getHotIndex();
        Integer propagationIndex = bookView.getPropagateIndex();

        List<BookIndexData> bookIndexDatas = bookIndexDataService.getByBookId(bookView.getBookId());
        if (CollectionUtils.isNotEmpty(bookIndexDatas)) {
            int i = 0;
            for (BookIndexData bookIndexData : bookIndexDatas) {
                String[] ages = bookIndexData.getAge();
                if (ArrayUtils.isNotEmpty(ages)) {
                    developScore += 15 * Double.parseDouble(ages[0]) + 23 * Double.parseDouble(ages[1]) + 28 * Double.parseDouble(ages[1]) + 16 * Double.parseDouble(ages[0]) + 8 * Double.parseDouble(ages[0]);
                    i++;
                }
            }

            developScore = 0.01 * (hotIndex + propagationIndex) * developScore / i;
        }


        return "" + (developScore.intValue());
    }

    @Override
    public String getReputationIndexByBookId(Integer bookId) {
        List<BookScore> bookScores = getByBookId(bookId);
        Double reputationScore = 0.0;
        if (CollectionUtils.isNotEmpty(bookScores)) {
            Map<Integer, Double> percentMap = getPercentMap(bookScores);
            for (BookScore bookScore : bookScores) {
                Double score = bookScore.getScore();
                Double marketPercent = percentMap.get(bookScore.getSource());
                reputationScore += marketPercent * score;
            }

            reputationScore = reputationScore * 123.45;
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
        Double res = 0.0;
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
