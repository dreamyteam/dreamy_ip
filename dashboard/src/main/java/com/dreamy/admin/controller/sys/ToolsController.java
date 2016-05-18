package com.dreamy.admin.controller.sys;

import com.dreamy.admin.controller.DashboardController;
import com.dreamy.admin.service.SinaLoginService;
import com.dreamy.admin.tasks.*;
import com.dreamy.admin.tasks.index.*;
import com.dreamy.admin.tasks.rank.BookRankCreateTask;
import com.dreamy.domain.sys.SysOption;
import com.dreamy.service.iface.sys.SysOptionService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by wangyongxing on 16/5/11.
 */
@Controller
public class ToolsController extends DashboardController {

    @Autowired
    private SinaLoginService sinaLoginService;

    @Autowired
    KeyWorkTask keyWorkTask;

    @Autowired
    SoIndexTask soIndexTask;
    @Autowired
    IpBookCrawlerTask ipBookCrawlerTask;

    @Autowired
    NewsMediaTask newsMediaTask;
    @Autowired
    BookScoreTask bookScoreTask;

    @Autowired
    CompositeIndexexCreateTask compositeIndexexCreateTask;
    @Autowired
    DevelopIndexesCreateTask developIndexesCreateTask;
    @Autowired
    HotIndexesCreateTask hotIndexesCreateTask;
    @Autowired
    PropagationIndexesCreareTask propagationIndexesCreareTask;

    @Autowired
    ReputationIndexesCreateTask reputationIndexesCreateTask;

    @Autowired
    BookIndexHistoryTask bookIndexHistoryTask;
    @Autowired
    BookRankCreateTask bookRankCreateTask;

    @Resource
    private SysOptionService sysOptionService;
    @Autowired
    QueueService queueService;


    /**
     * 新浪登录
     *
     * @return
     */
    @RequestMapping("/tools/sina")
    public String sinaLogin() {
        sinaLoginService.init();
        return "/sys/call_list";
    }

    @RequestMapping("/system/call")
    public String call() {

        return "/sys/call_list";
    }


    /**
     * 爬取豆瓣
     *
     * @return
     */
    @RequestMapping(value = "/crawler/douban")
    public String crawlingDouban() {
        SysOption option = sysOptionService.getByCode("002");
        if (option != null) {
            if (StringUtils.isNotEmpty(option.getCodeValue())) {
                String arr[] = option.getCodeValue().split(",");
                int length = arr.length;
                for (int i = 0; i < length; i++) {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("title", arr[i]);
                    queueService.push("douban_book_tag_develop", map);
                }
            }
        }
        return redirect("/system/call.html");
    }


    /**
     * 全部爬取
     *
     * @return
     */
    @RequestMapping(value = "/crawler/all")
    public String crawlingAll() {
        ipBookCrawlerTask.crawler();
        return redirect("/system/call.html");
    }

    /**
     * 关键词搜索爬取
     *
     * @return
     */
    @RequestMapping(value = "/crawler/keyword")
    public String crawlingKeyWord() {
        keyWorkTask.crawler();
        return redirect("/system/call.html");
    }

    /**
     * weibo关键词搜索爬取
     *
     * @return
     */
    @RequestMapping(value = "/crawler/keyword_weibo")
    public String crawlingKeyWordWeiBo() {
        keyWorkTask.crawlerWeiBo();
        return redirect("/system/call.html");
    }

    /**
     * weixin关键词搜索爬取
     *
     * @return
     */
    @RequestMapping(value = "/crawler/keyword_weixin")
    public String crawlingKeyWordWeiXin() {
        keyWorkTask.crawlerWeiXin();
        return redirect("/system/call.html");
    }

    /**
     * 360指数爬取
     *
     * @return
     */
    @RequestMapping(value = "/crawler/soindex")
    public String soindex() {
        soIndexTask.crawler();
        return redirect("/system/call.html");
    }

    /**
     * news.sougou 五大新闻媒体数量爬取
     *
     * @return
     */
    @RequestMapping(value = "/crawler/newsMedia")
    public String newsSogou() {
        newsMediaTask.crawler();
        return redirect("/system/call.html");
    }

    /**
     * news.sougou 积分 排名抽取
     *
     * @return
     */
    @RequestMapping(value = "/crawler/score")
    public String score() {
        bookScoreTask.crawler();
        return redirect("/system/call.html");
    }


    /**
     * 计算指数
     *
     * @return
     */
    @RequestMapping(value = "/index/hotIndex")
    public String hotIndex() {
        hotIndexesCreateTask.run();
        developIndexesCreateTask.run();
        propagationIndexesCreareTask.run();
        reputationIndexesCreateTask.run();
        compositeIndexexCreateTask.run();
        bookIndexHistoryTask.copy();
        return redirect("/system/call.html");
    }


    /**
     * 计算排名
     *
     * @return
     */
    @RequestMapping(value = "/index/rank")
    public String rank() {
        bookRankCreateTask.run();
        return redirect("/system/call.html");
    }
}
