package com.dreamy.ipcool.controllers.search;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookRank;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.BookIndexTypeEnums;
import com.dreamy.enums.IpTypeEnums;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.service.iface.ipcool.BookRankService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.ipcool.SearchService;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
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
    public String result(Page page, ModelMap model,
                         @RequestParam(value = "content", required = false, defaultValue = "") String content,
                         @RequestParam(value = "type", required = false) String typesStr
    ) {

        List<Integer> types = null;
        if (StringUtils.isNotEmpty(typesStr)) {
            types = JsonUtils.toList(Integer.class, typesStr);
        }


        List<BookView> bookViewList = new LinkedList<BookView>();
        Map<Integer, Integer> rankMap = new HashMap<Integer, Integer>();
        List<Integer> bookIds = new LinkedList<Integer>();

        if (StringUtils.isNotEmpty(content)) {
            bookIds = searchService.getBookIdsFromSolrByNameAndType(content, page, types);
            rankMap = bookRankService.getCompositeRankMapByBookIds(bookIds);
        } else {
            List<BookRank> bookRankList = bookRankService.getBookRankByOrderAndType("rank asc", BookIndexTypeEnums.composite.getType(), page);
            page.setTotalNum(bookViewService.getTotalCountByType(IpTypeEnums.chuban.getType()));

            for (BookRank bookRank : bookRankList) {
                bookIds.add(bookRank.getBookId());
                rankMap.put(bookRank.getBookId(), bookRank.getRank());
            }

            model.put("showChubanOnly", 1);
        }

        Map<Integer, BookView> bookViewMap = bookViewService.getListMapByBookIds(bookIds);

        for (Integer bookId : bookIds) {
            if (bookViewMap.containsKey(bookId)) {
                bookViewList.add(bookViewMap.get(bookId));
            }
        }

        model.put("typestr", typesStr);
        model.put("types", types);
        model.put("list", bookViewList);
        model.put("rankMap", rankMap);
        model.put("typeEnums", IpTypeEnums.values());
        model.put("page", page);
        model.put("content", content);

        return "/search/result";
    }
}
