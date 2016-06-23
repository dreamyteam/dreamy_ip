package com.dreamy.admin.controller.query;

import com.dreamy.admin.controller.DashboardController;
import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.ipcool.IpBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by mac on 16/6/22.
 */
@Controller
@RequestMapping(value = "/query")
public class DataQueryController extends DashboardController {

    @Autowired
    private BookViewService bookViewService;
    @Autowired
    private IpBookService ipBookService;

    @RequestMapping(value = "/list")
    public String queryList(BookView bookView, HttpServletRequest request, ModelMap map, Page page) {
        String order = request.getParameter("order");

        List<BookView> list = bookViewService.getListByPageAndWhere(bookView, page, order);
        map.put("list", list);
        map.put("type", bookView.getType());
        map.put("name", bookView.getName());
        map.put("order", order);
        map.put("page", page);
        return "/query/list";
    }

    @RequestMapping(value = "/bookDetail")
    public String bookDetail(@RequestParam(value = "id", required = false,defaultValue ="0") Integer id, ModelMap map) {
        BookView bookView = bookViewService.getById(id);
        IpBook book = ipBookService.getById(bookView.getBookId());

        map.put("bookView", bookView);
        map.put("book", book);
        return "/query/book_detail";
    }
}
