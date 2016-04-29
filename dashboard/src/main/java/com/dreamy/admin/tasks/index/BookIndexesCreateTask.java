package com.dreamy.admin.tasks.index;

import com.dreamy.domain.ipcool.BookScore;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/28
 * Time: 下午2:17
 */
@Component
public class BookIndexesCreateTask {
    @Autowired
    private BookScoreService bookScoreService;

    private void run() {

    }

//    @Scheduled(fixedDelay = 8000)
    public void getHotScore() {

        Map<Integer, Map<String, Double>> options = new HashMap<>();
        Map<String, Double> map1 = new HashMap<>();
        map1.put("marketPercent", 0.5);
        map1.put("coefficient", 0.1);
        map1.put("argA", 0.1);
        map1.put("argB", 0.1);

        Map<String, Double> map2 = new HashMap<>();
        map2.put("marketPercent", 0.24);
        map2.put("coefficient", 0.01851851852);
        map2.put("argA", 0.1);
        map2.put("argB", 0.1);

        Map<String, Double> map3 = new HashMap<>();
        map3.put("marketPercent", 0.16);
        map3.put("coefficient", 0.1315789474);
        map3.put("argA", 0.1);
        map3.put("argB", 0.1);

        Map<String, Double> map4 = new HashMap<>();
        map4.put("marketPercent", 0.1);
        map4.put("coefficient", 1.4285714286);
        map4.put("argA", 0.1);
        map4.put("argB", 0.1);


        options.put(CrawlerSourceEnums.douban.getType(), map1);
        options.put(CrawlerSourceEnums.dangdang.getType(), map2);
        options.put(CrawlerSourceEnums.jd.getType(), map3);
        options.put(CrawlerSourceEnums.amazon.getType(), map4);


        List<BookScore> bookScores = bookScoreService.getByBookId(40);
        Double hotScore = 0.0;
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
                } else {
                    hotScore += marketPercent * commentNum * score * (argA / coefficient);
                }

            }
        }
        System.err.println(hotScore.intValue());
    }


}
