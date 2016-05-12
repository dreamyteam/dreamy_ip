package com.dreamy.handler.sougou;

import com.dreamy.domain.ipcool.NewsMedia;
import com.dreamy.service.iface.ipcool.NewsMediaService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.PatternUtils;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/5/6.
 */
@Component
public class NewsSougouHandler {
    private static final Logger log = LoggerFactory.getLogger(NewsSougouHandler.class);

    private static final Map<Integer, String> CRAWL_SOURCES = new LinkedHashMap<Integer, String>();

    static {
        CRAWL_SOURCES.put(1, "搜获");
        CRAWL_SOURCES.put(2, "腾讯");
        CRAWL_SOURCES.put(3, "新浪");
        CRAWL_SOURCES.put(4, "凤凰");
        CRAWL_SOURCES.put(5, "网易");

    }
    @Resource
     NewsMediaService newsMediaService;



    public void crawler(String word, int bookId) {
        OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), NewsSougou.class);
        word=word.replace(" ","");
        String url = "http://news.sogou.com/news?query=" + word;
        NewsSougou sougou = ooSpider.<NewsSougou>get(url);
        if(sougou!=null) {
            List<String> list = sougou.getUrls();
            ooSpider.close();
            if (CollectionUtils.isNotEmpty(list)) {
                int size = list.size();
                newsMediaService.delByBookId(bookId);
                for (int i = 0; i < size; i++) {
                    get(list.get(i), i, bookId);
                }
            }
        }

    }


    public void get(String url, int source, int bookId) {
        if (StringUtils.isNotEmpty(url)) {
            OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), NewsSougou.class);
            NewsSougou sougou = ooSpider.<NewsSougou>get(url);
            if (sougou != null) {
                NewsMedia newsMedia=new NewsMedia();
                newsMedia.type(1);
                newsMedia.source(source+1);
                newsMedia.bookId(bookId);
                newsMedia.num(Integer.valueOf(PatternUtils.getNum(sougou.getNum())));
                newsMediaService.save(newsMedia);
            }
            ooSpider.close();
        }
    }


}
