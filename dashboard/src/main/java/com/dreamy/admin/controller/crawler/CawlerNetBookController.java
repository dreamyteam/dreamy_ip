package com.dreamy.admin.controller.crawler;

import com.dreamy.admin.beans.BookCrawlerModel;
import com.dreamy.admin.controller.DashboardController;
import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.IpBookStatusEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.IpBookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wangyongxing on 16/4/19.
 */
@Controller
@RequestMapping(value = "/crawler/netbook")
public class CawlerNetBookController extends DashboardController {

    @Resource
    private IpBookService ipBookService;
    @Resource
    private BookCrawlerInfoService bookCrawlerInfoService;

    /**
     * @return
     */
    @RequestMapping("")
    public String role(IpBook ipBook, ModelMap model, Page page) {
        ipBook.setType(2);
        List<IpBook> list = ipBookService.getIpBookList(ipBook, page);

        model.put("list", list);
        model.put("page", page);
        model.put("statuses", IpBookStatusEnums.values());
        return "/crawler/netbook/ipbook";
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String insert(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id, ModelMap model) {

        return "/crawler/netbook/ipbook_create";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam(value = "id", required = true) Integer id, ModelMap model) {
        IpBook ipBook = ipBookService.getById(id);
        BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo().bookId(ipBook.getId());
        List<BookCrawlerInfo> list = bookCrawlerInfoService.getByRecord(bookCrawlerInfo);
        model.put("book", ipBook);
        model.put("list", list);
        return "/crawler/netbook/ipbook_edit";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String update(IpBook ipBook, BookCrawlerModel infos) {
        List<BookCrawlerInfo> list = infos.getInfos();
        ipBook.status(IpBookStatusEnums.waitting.getStatus());
        if (ipBook.getId() != null && ipBook.getId() > 0) {
            ipBookService.updateRecordAndCrawlerInfo(ipBook, list);
        } else {
            ipBook.type(2);
            ipBookService.saveRecordAndCrawlerInfo(ipBook, list);
        }
        return redirect("/crawler/netbook.html");
    }
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id, ModelMap model) {

        IpBook ipBook = ipBookService.getById(id);
        BookCrawlerInfo entity = new BookCrawlerInfo().bookId(ipBook.getId());
        List<BookCrawlerInfo> list = bookCrawlerInfoService.getByRecord(entity);
        model.put("book", ipBook);
        model.put("currentSource", request.getParameter("source"));
        model.put("sources", CrawlerSourceEnums.values());
        return "/crawler/ipbook_view";
    }

}
