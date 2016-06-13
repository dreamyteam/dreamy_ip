package com.dreamy.ipcool.controllers.search;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.IpTypeEnums;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.service.iface.CommonService;
import com.dreamy.service.iface.ipcool.BookRankService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.ipcool.SearchService;
import com.dreamy.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/26
 * Time: 下午7:32
 */
@Controller
@RequestMapping("/search")
public class SearchController extends IpcoolController {
    @Resource
    private BookViewService bookViewService;

    @Autowired
    private BookRankService bookRankService;

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "")
    public String result(@RequestParam(value = "content", required = false, defaultValue = "") String content, Page page, ModelMap model) {
        BookView bookView = new BookView().name(content);
//        List<BookView> list = bookViewService.getList(bookView, page, "composite_index desc");
        String searchRes = searchService.getBookViewByName(content, page);
//        if (CollectionUtils.isNotEmpty(list)) {
//            List<Integer> bookIds = new LinkedList<Integer>();
//            for (BookView view : list) {
//                bookIds.add(view.getBookId());
//            }
//            Map<Integer, Integer> rankMap = bookRankService.getCompositeRankMapByBookIds(bookIds);
//            if (rankMap != null) {
//                model.put("rankMap", rankMap);
//            }
//
//        } else {
//            list = null;
//        }

//        model.put("typeEnums", IpTypeEnums.values());
//        model.put("list", list);
//        model.put("page", page);
//        model.put("content", content);

        return "/search/result";
    }


}
