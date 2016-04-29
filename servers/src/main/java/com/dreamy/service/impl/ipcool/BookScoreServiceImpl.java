package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.BookScoreDao;
import com.dreamy.domain.ipcool.BookScore;
import com.dreamy.domain.ipcool.BookScoreConditions;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.utils.BeanUtils;
import com.dreamy.utils.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/28.
 */
@Service
public class BookScoreServiceImpl implements BookScoreService {
    @Resource
    private BookScoreDao bookScoreDao;

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
    public String getBookHotIndexByBookAndOptions(Integer bookId, Map<Integer, Map<String, Double>> options) {
        List<BookScore> bookScores = getByBookId(bookId);
        Double hotScore = 0.0;
        String hotScoreStr = "--";
        if (CollectionUtils.isNotEmpty(bookScores)) {
            for (BookScore bookScore : bookScores) {
                Integer commentNum = bookScore.getCommentNum();
                Double score = bookScore.getScore();
                Double coefficient = options.get(bookScore.getSource()).get("coefficient");
                Double marketPercent = options.get(bookScore.getSource()).get("marketPercent");
                Double argA = options.get(bookScore.getSource()).get("argA");
                Double argB = options.get(bookScore.getSource()).get("argB");
                Integer saleScore = bookScore.getSaleSort();

                if (saleScore != null && saleScore > 0) {
                    hotScore += marketPercent * commentNum * score * (argA / coefficient + argB * coefficient / saleScore);
                    hotScoreStr += marketPercent+"*"+  commentNum +"*"+ score +"*"+ (argA +"/"+ coefficient +"+"+ argB +"*"+ coefficient +"/"+ saleScore);
                } else {
                    hotScore += marketPercent * commentNum * score * (argA / coefficient);
                    hotScoreStr += marketPercent+"*"+ commentNum +"*"+ score +"*"+ (argA +"/"+ coefficient);
                }

            }
        }

        return "" + hotScore.intValue();
    }

    @Override
    public List<BookScore> getByBookId(Integer bookId) {
        BookScoreConditions conditions = new BookScoreConditions();
        conditions.createCriteria().andBookIdEqualTo(bookId);

        return bookScoreDao.selectByExample(conditions);
    }
}
