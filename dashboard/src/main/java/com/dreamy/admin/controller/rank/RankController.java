package com.dreamy.admin.controller.rank;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.IpBookStatusEnums;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/28
 * Time: 下午7:16
 */
@Controller
@RequestMapping("/rank")
public class RankController {
    @Resource
    private IpBookService ipBookService;

    @Autowired
    private BookScoreService bookScoreService;

    @Autowired
    private BookViewService bookViewService;

    @RequestMapping("/math")
    public String math(IpBook ipBook, ModelMap model, Page page,
                       @RequestParam(value = "arg[]", required = false) List<String> args) {
        ipBook.setType(1);
        page.setPageSize(500);
        List<IpBook> list = ipBookService.getIpBookList(ipBook, page);

        Map<Integer, String> map = new HashMap<>();
        if (CollectionUtils.isNotEmpty(args) && args.size() == 8) {
            Map<Integer, Map<String, Double>> options = new HashMap<>();
            Map<String, Double> map1 = new HashMap<>();
            map1.put("marketPercent", 0.5);
            map1.put("coefficient", 0.1);
            map1.put("argA", Double.parseDouble(args.get(0)));
            map1.put("argB", Double.parseDouble(args.get(1)));

            Map<String, Double> map2 = new HashMap<>();
            map2.put("marketPercent", 0.24);
            map2.put("coefficient", 0.01851851852);
            map2.put("argA", Double.parseDouble(args.get(2)));
            map2.put("argB", Double.parseDouble(args.get(3)));

            Map<String, Double> map3 = new HashMap<>();
            map3.put("marketPercent", 0.16);
            map3.put("coefficient", 0.1315789474);
            map3.put("argA", Double.parseDouble(args.get(4)));
            map3.put("argB", Double.parseDouble(args.get(5)));

            Map<String, Double> map4 = new HashMap<>();
            map4.put("marketPercent", 0.1);
            map4.put("coefficient", 1.4285714286);
            map4.put("argA", Double.parseDouble(args.get(6)));
            map4.put("argB", Double.parseDouble(args.get(7)));

            options.put(CrawlerSourceEnums.douban.getType(), map1);
            options.put(CrawlerSourceEnums.dangdang.getType(), map2);
            options.put(CrawlerSourceEnums.jd.getType(), map3);
            options.put(CrawlerSourceEnums.amazon.getType(), map4);


            try {
                for (IpBook book : list) {
                    Integer bookId = book.getId();
                    BookView bookView = bookViewService.getByBookId(bookId);
                    if (bookView.getId() != null) {
                        if (bookView.getHotIndex() == 0) {
                            String score = bookScoreService.getBookHotIndexByBookId(book.getId());
                            map.put(book.getId(), score);
                            bookView.hotIndex(Integer.parseInt(score));
                            bookViewService.update(bookView);
                        }
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        model.put("scoreMap", map);
        model.put("list", list);
        model.put("page", page);
        model.put("statuses", IpBookStatusEnums.values());

        return "/rank/math";
    }
}
