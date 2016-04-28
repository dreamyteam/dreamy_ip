package com.dreamy.ipcool.controllers.index;

import com.dreamy.domain.ipcool.BookIndexHistory;
import com.dreamy.domain.ipcool.BookRank;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.mogodb.beans.Comment;
import com.dreamy.mogodb.beans.Comments;
import com.dreamy.service.iface.ipcool.BookIndexHistoryService;
import com.dreamy.service.iface.ipcool.BookRankService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.mongo.CommentService;
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
        Comments comments=commentService.getById(ipId);
        BookIndexHistory bookIndexHistory = bookIndexHistoryService.getMaxByBookId(bookView.getBookId());
        BookRank bookRank=new BookRank().bookId(bookView.getBookId());
        List<BookRank> list=bookRankService.getList(bookRank,null);
        for(BookRank rank:list){
            if(rank.getType()==1){
                model.put("crank",rank.getRank());// 综合指数排名
            }
            if(rank.getType()==2){
                model.put("drank",rank.getRank());//开发潜力指数排名
            }
            if(rank.getType()==3){
                model.put("prank",rank.getRank());//传播指数排名
            }
            if(rank.getType()==4){
                model.put("hrank",rank.getRank());//热度指数排名
            }
            if(rank.getType()==5){
                model.put("arank",rank.getRank());//活跃指数排名
            }
        }
        model.put("history", bookIndexHistory);
        model.put("view", bookView);
        if(comments!=null)
        {
            model.put("comments", comments.getComments());
        }
        return "/index/sum";
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
