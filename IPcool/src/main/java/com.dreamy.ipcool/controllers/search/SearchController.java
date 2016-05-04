package com.dreamy.ipcool.controllers.search;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.BookTypeEnums;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.service.iface.ipcool.BookViewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

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

    @RequestMapping(value = "")
    public String result(@RequestParam(value = "content", required = false, defaultValue = "") String content, Page page, ModelMap model) {
        BookView bookView = new BookView().name(content);
        List<BookView> list = bookViewService.getList(bookView, page);

        model.put("typeEnums", BookTypeEnums.values());
        model.put("list", list);
        model.put("page", page);
        model.put("content", content);
        return "/search/result";
    }
}
