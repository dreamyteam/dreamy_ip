package com.dreamy.ipcool.controllers.rank;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookRank;
import com.dreamy.enums.BookIndexTypeEnums;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.service.iface.ipcool.BookRankService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/26
 * Time: 下午7:06
 */
@RequestMapping("/rank")
@Controller
public class RankController extends IpcoolController {

    @Autowired
    BookRankService bookRankService;

    @RequestMapping("/list")
    public String list(ModelMap model) {
        String order = "rank_index desc";
        BookRank entity = new BookRank();
        entity.type(BookIndexTypeEnums.composite.getType());

        List<BookRank> clist = bookRankService.getList(entity, new Page(10, 1), order);
        entity = new BookRank();
        entity.type(BookIndexTypeEnums.develop.getType());
        List<BookRank> dlist = bookRankService.getList(entity, new Page(10, 1), order);
        entity.type(BookIndexTypeEnums.propagate.getType());
        List<BookRank> plist = bookRankService.getList(entity, new Page(10, 1), order);
        entity.type(BookIndexTypeEnums.hot.getType());
        List<BookRank> hlist = bookRankService.getList(entity, new Page(10, 1), order);

        model.put("clist", clist);
        model.put("dlist", dlist);
        model.put("plist", plist);
        model.put("hlist", hlist);
        if (CollectionUtils.isNotEmpty(clist)) {
            model.put("date", TimeUtils.toString("yyyy-MM-dd", clist.get(0).getUpdatedAt()));
        }
        return "/rank/list";
    }

    @RequestMapping("/detail")
    public String detail() {
        return "/rank/detail";
    }
}
