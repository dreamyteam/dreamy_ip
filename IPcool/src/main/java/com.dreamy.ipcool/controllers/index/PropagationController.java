package com.dreamy.ipcool.controllers.index;

import com.dreamy.beans.InterfaceBean;
import com.dreamy.domain.ipcool.BookIndexHistory;
import com.dreamy.domain.ipcool.BookScore;
import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.domain.ipcool.NewsMedia;
import com.dreamy.enums.KeyWordEnums;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookIndexHistoryService;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.service.iface.ipcool.KeyWordService;
import com.dreamy.service.iface.ipcool.NewsMediaService;
import com.dreamy.service.iface.mongo.BookIndexDataService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.utils.ConstStrings;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.TimeUtils;
import com.dreamy.utils.TimerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by wangyongxing on 16/5/6.
 */
@Controller
public class PropagationController extends IpcoolController {


    private static final Map<Integer, String> SOURCES = new LinkedHashMap<Integer, String>();


    private static final Map<Integer, String> SCORE_SOURCES = new LinkedHashMap<Integer, String>();

    static {
        SOURCES.put(1, "搜获");
        SOURCES.put(2, "腾讯");
        SOURCES.put(3, "新浪");
        SOURCES.put(4, "凤凰");
        SOURCES.put(5, "网易");

        SCORE_SOURCES.put(1, "亚马逊");
        SCORE_SOURCES.put(2, "京东");
        SCORE_SOURCES.put(3, "当当");
        SCORE_SOURCES.put(4, "豆瓣");

    }

    @Resource
    private NewsMediaService newsMediaService;

    @Resource
    BookIndexHistoryService bookIndexHistoryService;
    @Resource
    KeyWordService keyWordService;
    @Resource
    BookIndexDataService bookIndexDataService;
    @Resource
    BookScoreService bookScoreService;


    /**
     * 综合指数图
     *
     * @param response
     * @param bookId
     * @param callback
     */
    @ResponseBody
    @RequestMapping("/comprehensive/history")
    public void comprehensiveHistory(HttpServletResponse response, @RequestParam(value = "ip", required = true) Integer bookId, @RequestParam(value = "callback", required = false, defaultValue = ConstStrings.EMPTY) String callback) {

        InterfaceBean bean = new InterfaceBean().success();
        BookIndexHistory entity = new BookIndexHistory();
        entity.bookId(bookId);
        entity.status(1);
        entity.createdAt(new Date());
        entity.setUpdatedAt(TimeUtils.appointed(-30));
        List<BookIndexHistory> list = bookIndexHistoryService.getList(entity, null, "created_at asc");
        List<String> date = new ArrayList<String>();
        List<Integer> data = new ArrayList<Integer>();
        for (BookIndexHistory history : list) {
            data.add(history.getCompositeIndex());
            date.add(TimeUtils.toString("yyyy-MM-dd", history.getCreatedAt()));

        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", date.toArray());
        map.put("data", data.toArray());
        bean.setData(map);
        interfaceReturn(response, JsonUtils.toString(bean), callback);


    }

    /**
     * 热度指数图
     *
     * @param response
     * @param bookId
     * @param callback
     */
    @ResponseBody
    @RequestMapping("/hotIndex/history")
    public void hotIndexHistory(HttpServletResponse response, @RequestParam(value = "ip", required = true) Integer bookId, @RequestParam(value = "callback", required = false, defaultValue = ConstStrings.EMPTY) String callback) {
        InterfaceBean bean = new InterfaceBean().success();
        BookIndexHistory entity = new BookIndexHistory();
        entity.bookId(bookId);
        entity.status(1);
        entity.createdAt(new Date());
        entity.setUpdatedAt(TimeUtils.appointed(-30));
        List<BookIndexHistory> list = bookIndexHistoryService.getList(entity, null, "created_at asc");
        List<String> date = new ArrayList<String>();
        List<Integer> data = new ArrayList<Integer>();
        for (BookIndexHistory history : list) {
            data.add(history.getHotIndex());
            date.add(TimeUtils.toString("yyyy-MM-dd", history.getCreatedAt()));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", date.toArray());
        map.put("data", data.toArray());
        bean.setData(map);
        interfaceReturn(response, JsonUtils.toString(bean), callback);

    }

    /**
     * 用户活跃度图表
     *
     * @param response
     * @param bookId
     * @param callback
     */
    @ResponseBody
    @RequestMapping("/activityIndex/history")
    public void activityIndex(HttpServletResponse response, @RequestParam(value = "ip", required = true) Integer bookId, @RequestParam(value = "callback", required = false, defaultValue = ConstStrings.EMPTY) String callback) {
        InterfaceBean bean = new InterfaceBean().success();
        BookIndexHistory entity = new BookIndexHistory();
        entity.bookId(bookId);
        entity.status(1);
        entity.createdAt(new Date());
        entity.setUpdatedAt(TimeUtils.appointed(-30));
        List<BookIndexHistory> list = bookIndexHistoryService.getList(entity, null, "created_at asc");
        List<String> date = new ArrayList<String>();
        List<Integer> data = new ArrayList<Integer>();
        for (BookIndexHistory history : list) {
            data.add(history.getActivityIndex());
            date.add(TimeUtils.toString("yyyy-MM-dd", history.getCreatedAt()));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", date.toArray());
        map.put("data", data.toArray());
        bean.setData(map);
        interfaceReturn(response, JsonUtils.toString(bean), callback);

    }

    /**
     * 传播指数图表
     *
     * @param response
     * @param bookId
     * @param callback
     */
    @ResponseBody
    @RequestMapping("/propagateIndex/history")
    public void propagationIndexHistory(HttpServletResponse response, @RequestParam(value = "ip", required = true) Integer bookId, @RequestParam(value = "callback", required = false, defaultValue = ConstStrings.EMPTY) String callback) {
        InterfaceBean bean = new InterfaceBean().success();
        BookIndexHistory entity = new BookIndexHistory();
        entity.bookId(bookId);
        entity.status(1);
        entity.createdAt(new Date());
        entity.setUpdatedAt(TimeUtils.appointed(-30));

        List<BookIndexHistory> list = bookIndexHistoryService.getList(entity, null, "created_at asc");
        List<String> date = new ArrayList<String>();
        List<Integer> data = new ArrayList<Integer>();
        for (BookIndexHistory history : list) {
            data.add(history.getPropagateIndex());
            date.add(TimeUtils.toString("yyyy-MM-dd", history.getCreatedAt()));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", date.toArray());
        map.put("data", data.toArray());
        bean.setData(map);
        interfaceReturn(response, JsonUtils.toString(bean), callback);

    }

    /**
     * 新闻平台图表
     *
     * @param response
     * @param bookId
     * @param callback
     */
    @ResponseBody
    @RequestMapping("/propagation/newMedia")
    public void newsMedia(HttpServletResponse response, @RequestParam(value = "ip", required = true) Integer bookId, @RequestParam(value = "callback", required = false, defaultValue = ConstStrings.EMPTY) String callback) {

        InterfaceBean bean = new InterfaceBean().success();
        NewsMedia entity = new NewsMedia();
        entity.bookId(bookId);
        entity.type(1);
        List<NewsMedia> list = newsMediaService.getList(entity, null);
        List<Map<String, Object>> re = new ArrayList<Map<String, Object>>();

        for (NewsMedia newsMedia : list) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("value", newsMedia.getNum());
            map.put("name", SOURCES.get(newsMedia.getSource()));
            re.add(map);
        }
        bean.setData(re);
        interfaceReturn(response, JsonUtils.toString(bean), callback);
    }

    /**
     * 社交媒体平台图表
     *
     * @param response
     * @param bookId
     * @param callback
     */
    @ResponseBody
    @RequestMapping("/propagation/socialMedia")
    public void socialMedia(HttpServletResponse response, @RequestParam(value = "ip", required = true) Integer bookId, @RequestParam(value = "callback", required = false, defaultValue = ConstStrings.EMPTY) String callback) {

        InterfaceBean bean = new InterfaceBean().success();
        List<KeyWord> list = keyWordService.getByBookId(bookId);
        List<Map<String, Object>> re = new ArrayList<Map<String, Object>>();
        for (KeyWord keyWord : list) {
            if (KeyWordEnums.weibo.getType() == keyWord.getSource()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("value", keyWord.getIndexNum());
                map.put("name", "微博");
                re.add(map);
            } else if (KeyWordEnums.weixin.getType() == keyWord.getSource()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("value", keyWord.getIndexNum());
                map.put("name", "微信");
                re.add(map);
            }
        }
        bean.setData(re);
        interfaceReturn(response, JsonUtils.toString(bean), callback);
    }

    @ResponseBody
    @RequestMapping("/user/sex")
    public void sex(HttpServletResponse response, @RequestParam(value = "ip", required = true) Integer bookId, @RequestParam(value = "callback", required = false, defaultValue = ConstStrings.EMPTY) String callback) {

        InterfaceBean bean = new InterfaceBean().success();
        BookIndexData bookIndexData = bookIndexDataService.getById(bookId);
        List<Map<String, Object>> re = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("value", bookIndexData.getFemale());
        map.put("name", "女");
        re.add(map);
        Map<String, Object> male = new HashMap<String, Object>();
        male.put("value", bookIndexData.getMale());
        male.put("name", "男");
        re.add(male);
        bean.setData(re);
        interfaceReturn(response, JsonUtils.toString(bean), callback);

    }

    @ResponseBody
    @RequestMapping("/user/age")
    public void age(HttpServletResponse response, @RequestParam(value = "ip", required = true) Integer bookId, @RequestParam(value = "callback", required = false, defaultValue = ConstStrings.EMPTY) String callback) {

        InterfaceBean bean = new InterfaceBean().success();
        BookIndexData bookIndexData = bookIndexDataService.getById(bookId);
        List<Map<String, Object>> re = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("value", bookIndexData.getAge());
        map.put("name", "平均分布");
        re.add(map);
        Map<String, Object> male = new HashMap<String, Object>();
        male.put("value", bookIndexData.getAge());
        male.put("name", "斗破苍穹");
        re.add(male);
        bean.setData(re);
        interfaceReturn(response, JsonUtils.toString(bean), callback);

    }

    @ResponseBody
    @RequestMapping("/user/score")
    public void score(HttpServletResponse response, @RequestParam(value = "ip", required = true) Integer bookId, @RequestParam(value = "callback", required = false, defaultValue = ConstStrings.EMPTY) String callback) {

        InterfaceBean bean = new InterfaceBean().success();
        List<BookScore> list = bookScoreService.getByBookId(bookId);
        List<Map<String, Object>> re = new ArrayList<Map<String, Object>>();
        for (BookScore bookScore : list) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("value", bookScore.getScore());
            map.put("name", SCORE_SOURCES.get(bookScore.getSource()));
            re.add(map);
        }
        bean.setData(re);
        interfaceReturn(response, JsonUtils.toString(bean), callback);

    }
}
