package com.dreamy.ipcool.controllers.index;

import com.dreamy.domain.ipcool.BookIndexHistory;
import com.dreamy.domain.ipcool.BookRank;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.BookRankEnums;
import com.dreamy.enums.BookTypeEnums;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.mogodb.beans.Comments;
import com.dreamy.service.iface.ipcool.BookIndexHistoryService;
import com.dreamy.service.iface.ipcool.BookRankService;
import com.dreamy.service.iface.ipcool.BookTagsService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.mongo.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

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
    public String potential() {
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
        for (BookRank rank : list) {
            if (rank.getType() == BookRankEnums.composite.getType()) {
                model.put("crank", rank.getRank());// 综合指数排名
            }
            if (rank.getType() == BookRankEnums.develop.getType()) {
                model.put("drank", rank.getRank());//开发潜力指数排名
            }
            if (rank.getType() == BookRankEnums.propagation.getType()) {
                model.put("prank", rank.getRank());//传播指数排名
            }
            if (rank.getType() == BookRankEnums.hot.getType()) {
                model.put("hrank", rank.getRank());//热度指数排名
            }
            if (rank.getType() == 5) {
                model.put("arank", rank.getRank());//活跃指数排名
            }
        }

        if (comments != null) {
            model.put("comments", comments.getComments());
        }

        model.put("tagList", bookTagsService.getTagMapByBookId(bookId));
        model.put("rankEnums", BookRankEnums.values());
        model.put("typeEnums", BookTypeEnums.values());
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


}
