package com.dreamy.admin.controller.sys;

import com.dreamy.admin.controller.DashboardController;
import com.dreamy.admin.service.SinaLoginService;
import com.dreamy.admin.tasks.*;
import com.dreamy.admin.tasks.index.*;
import com.dreamy.admin.tasks.rank.BookRankCreateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * 新浪登录
     * @return
     */
    @RequestMapping("/tools/sina")
    public String sinaLogin(){
        sinaLoginService.init();
        return "null";
    }

    @RequestMapping("/system/call")
    public String call(){
        return "/sys/call_list";
    }

    /**
     *  全部爬取
     * @return
     */
    @RequestMapping(value = "/crawler/all")
    public String crawlingAll() {
        ipBookCrawlerTask.crawler();
        return redirect("/system/call.html");
    }

    /**
     *  关键词搜索爬取
     * @return
     */
    @RequestMapping(value = "/crawler/keyword")
    public String crawlingKeyWord() {
        keyWorkTask.crawler();
        return redirect("/system/call.html");
    }

    /**
     *
     * weixin关键词搜索爬取
     * @return
     */
    @RequestMapping(value = "/crawler/keyword_weixin")
    public String crawlingKeyWordWeiXin() {
        keyWorkTask.crawlerWeiXin();
        return redirect("/system/call.html");
    }

    /**
     *  360指数爬取
     * @return
     */
    @RequestMapping(value = "/crawler/soindex")
    public String soindex() {
        soIndexTask.crawler();
        return redirect("/system/call.html");
    }

    /**
     * news.sougou 五大新闻媒体数量爬取
     * @return
     */
    @RequestMapping(value = "/crawler/newsMedia")
    public String newsSogou() {
        newsMediaTask.crawler();
        return redirect("/system/call.html");
    }

    /**
     * news.sougou 积分 排名抽取
     * @return
     */
    @RequestMapping(value = "/crawler/score")
    public String score() {
        bookScoreTask.crawler();
        return redirect("/system/call.html");
    }




    /**
     * 计算指数
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
     * @return
     */
    @RequestMapping(value = "/index/rank")
    public String rank() {
        bookRankCreateTask.run();
        return redirect("/system/call.html");
    }
}
