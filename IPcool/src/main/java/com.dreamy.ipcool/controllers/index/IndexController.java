package com.dreamy.ipcool.controllers.index;

import com.dreamy.beans.InterfaceBean;
import com.dreamy.domain.ipcool.BookIndexHistory;
import com.dreamy.domain.ipcool.BookRank;
import com.dreamy.domain.ipcool.BookRankHistory;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.*;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.mogodb.beans.Comments;
import com.dreamy.service.iface.ipcool.*;
import com.dreamy.service.iface.mongo.CommentService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/26
 * Time: 下午4:30
 */
@Controller
@RequestMapping(value = {"/index"})
public class IndexController extends IpcoolController {

    @Resource
    private BookViewService bookViewService;
    @Resource
    private BookIndexHistoryService bookIndexHistoryService;
    @Resource
    private CommentService commentService;
    @Resource
    private BookRankService bookRankService;
    @Autowired
    private BookTagsService bookTagsService;
    @Autowired
    private BookRankHistoryService bookRankHistoryService;


    /**
     * 综合指数更多
     *
     * @return
     */
    @RequestMapping("/comprehensive")
    public String comprehensive() {
        return "/index/comprehensive";
    }

    /**
     * 开发意向
     *
     * @return
     */
    @RequestMapping("/potential")
    public String potential(@RequestParam(value = "ip", required = true) Integer ipId, ModelMap model) {
        BookView bookView = bookViewService.getById(ipId);
        if (bookView == null || bookView.getId() == null) {
            return null;
        }

        Integer bookId = bookView.getBookId();

        model.put("tagList", bookTagsService.getTagMapByBookId(bookId));
        model.put("view", bookView);

        return "/index/potential";
    }

    /**
     * 热度指数更多
     *
     * @return
     */
    @RequestMapping("/heat")
    public String heat(@RequestParam(value = "ip", required = true) Integer ipId, ModelMap model) {
        BookView bookView = bookViewService.getById(ipId);
        model.put("view", bookView);
        return "/index/heat";
    }

    /**
     * 简介
     *
     * @return
     */
    @RequestMapping("/introduction")
    public String introduction(@RequestParam(value = "ip", required = true) Integer ipId, ModelMap model) {

        BookView bookView = bookViewService.getById(ipId);
        model.put("view", bookView);
        return "/index/introduction";
    }

    /**
     * 详情页
     *
     * @param ipId
     * @return
     */
    @RequestMapping("/detail")
    public String detail(@RequestParam(value = "ip", required = true) Integer ipId, ModelMap model) {
        BookView bookView = bookViewService.getById(ipId);
        if (bookView == null || bookView.getId() == null) {
            return null;
        }

        Integer bookId = bookView.getBookId();

        Comments comments = commentService.getById(ipId);
        BookIndexHistory bookIndexHistory = bookIndexHistoryService.getMaxByBookId(bookId);
        BookRank bookRank = new BookRank().bookId(bookId);
        List<BookRank> list = bookRankService.getList(bookRank, null);

        if (CollectionUtils.isNotEmpty(list)) {
            for (BookRank rank : list) {
                Integer rankIndex = rank.getRank();
                if (rank.getType().equals(BookIndexTypeEnums.composite.getType())) {

                    model.put("crank", rankIndex);
                    model.put("crankLevel", bookRankService.getRankClassByPosition(rankIndex, bookViewService.getToutleCount()));
                    model.put("bookLevels", BookLevelEnums.values());
                    model.put("rankPositions", bookRankService.getRankPositionAndDetailByBookIdAndType(rankIndex, BookIndexTypeEnums.composite.getType()));
                }
                if (rank.getType().equals(BookIndexTypeEnums.develop.getType())) {
                    model.put("drank", rankIndex);//开发潜力指数排名
                }
                if (rank.getType().equals(BookIndexTypeEnums.propagate.getType())) {
                    model.put("prank", rankIndex);//传播指数排名
                }
                if (rank.getType().equals(BookIndexTypeEnums.hot.getType())) {
                    model.put("hrank", rankIndex);//热度指数排名
                }
            }
        }

        if (comments != null) {
            model.put("comments", comments.getComments());
        }

        model.put("tagList", bookTagsService.getTagMapByBookId(bookId));
        model.put("rankEnums", BookIndexTypeEnums.values());
        model.put("typeEnums", BookTypeEnums.values());
        model.put("trendEnums", BookRankTrendEnums.values());
        model.put("history", bookIndexHistory);
        model.put("view", bookView);

        return "/index/detail";
    }

    @RequestMapping("/persona")
    public String personal(@RequestParam(value = "ip", required = true) Integer ipId, ModelMap model) {
        BookView bookView = bookViewService.getById(ipId);
        model.put("view", bookView);
        return "/index/persona";
    }

    /**
     * 传播能力指数更多
     *
     * @return
     */
    @RequestMapping("/propagation")
    public String propagation(@RequestParam(value = "ip", required = true) Integer ipId, ModelMap model) {
        BookView bookView = bookViewService.getById(ipId);
        model.put("view", bookView);
        return "/index/propagation";
    }

    @RequestMapping("/user/reviews")
    public String userReviews() {
        return "/index/user_reviews";
    }

    @RequestMapping("/historyTrend")
    @ResponseBody
    public void trendHistory(HttpServletResponse response, @RequestParam(value = "bookId") Integer bookId, @RequestParam(value = "type", defaultValue = "") Integer type, @RequestParam(value = "callback") String callback) {
        InterfaceBean bean = new InterfaceBean().success();
        BookRank bookRank = bookRankService.getByBookIdAndType(bookId, type);
        if (bookRank.getId() == null) {
            bean.failure(1, "book id not exist");
        } else {
            Map<String, String> result = new HashMap<String, String>();
            BookRankHistory bookRankHistory = bookRankHistoryService.getTopHistoryByBookIdAndType(bookId, type);

            result.put("current_index_value", bookRank.getRankIndex().toString());
            result.put("current_index_trend", bookRankService.getRankTrendByBookIdAndTypeAndIndex(bookId, type, bookRank.getRankIndex()).toString());
            result.put("history_top_value", bookRankHistory.getRankIndex().toString());
            result.put("history_top_date", TimeUtils.toString("yyyy-MM-dd", bookRankHistory.getCreatedAt()));

            bean.setData(result);
        }

        interfaceReturn(response, JsonUtils.toString(bean), callback);
    }

}
