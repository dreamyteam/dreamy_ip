package com.dreamy.admin.controller.crawler;

import com.dreamy.admin.beans.BookCrawlerModel;
import com.dreamy.admin.controller.DashboardController;
import com.dreamy.admin.tasks.IpBookCrawlerTask;
import com.dreamy.admin.tasks.KeyWorkTask;
import com.dreamy.admin.tasks.SoIndexTask;
import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.CrawlerTaskStatusEnums;
import com.dreamy.enums.IpBookStatusEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.iface.mongo.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wangyongxing on 16/4/11.
 */
@Controller
@RequestMapping(value = "/crawler")
public class CrawlerController extends DashboardController {
    @Resource
    private IpBookService ipBookService;
    @Resource
    private BookCrawlerInfoService bookCrawlerInfoService;

    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    KeyWorkTask keyWorkTask;

    @Autowired
    SoIndexTask soIndexTask;
    @Autowired
    IpBookCrawlerTask ipBookCrawlerTask;


    /**
     * @return
     */
    @RequestMapping("")
    public String role(IpBook ipBook, ModelMap model, Page page) {
        ipBook.setType(1);
        List<IpBook> list = ipBookService.getIpBookList(ipBook, page);

        model.put("list", list);
        model.put("page", page);
        model.put("statuses", IpBookStatusEnums.values());
        return "/crawler/ipbook";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id, ModelMap model) {

        IpBook ipBook = ipBookService.getById(id);
        BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo().bookId(ipBook.getId());
        List<BookCrawlerInfo> list = bookCrawlerInfoService.getByRecord(bookCrawlerInfo);
        model.put("book", ipBook);

        List<BookInfo> bookInfos = new LinkedList<>();
        for (BookCrawlerInfo info : list) {
            model.put("url" + info.getSource(), info.getUrl().trim());
            BookInfo bookInfo = (BookInfo) bookInfoService.getById(info.getId());
            if (bookInfo != null) {
                bookInfos.add(bookInfo);
            }
        }

        model.put("crawlerInfos", bookInfos);
        model.put("currentSource", request.getParameter("source"));
        model.put("sources", CrawlerSourceEnums.values());
        return "/crawler/ipbook_view";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String insert(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id, ModelMap model) {

        return "/crawler/ipbook_create";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam(value = "id", required = true) Integer id, ModelMap model) {
        IpBook ipBook = ipBookService.getById(id);
        BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo().bookId(ipBook.getId());
        List<BookCrawlerInfo> list = bookCrawlerInfoService.getByRecord(bookCrawlerInfo);
        model.put("book", ipBook);
        model.put("list", list);
        return "/crawler/ipbook_edit";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String update(IpBook ipBook, BookCrawlerModel infos) {
        List<BookCrawlerInfo> list = infos.getInfos();
        ipBook.status(IpBookStatusEnums.waitting.getStatus());

        if (ipBook.getId() != null && ipBook.getId() > 0) {
            ipBookService.updateRecordAndCrawlerInfo(ipBook, list);
        } else {
            ipBook.type(1);
            ipBookService.saveRecordAndCrawlerInfo(ipBook, list);
        }
        return redirect("/crawler.html");
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String del(@RequestParam(value = "ids", required = true) List<Integer> ids) {
        ipBookService.delByIds(ids);
        return redirect("/crawler.html");
    }

    @RequestMapping(value = "/crawling")
    public String crawling(@RequestParam(value = "id", required = true) Integer id) {
        IpBook ipBook = ipBookService.getById(id);
        BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo().bookId(ipBook.getId());
        List<BookCrawlerInfo> list = bookCrawlerInfoService.getByRecord(bookCrawlerInfo);

        for (BookCrawlerInfo info : list) {
            if (info.getStatus().equals(CrawlerTaskStatusEnums.failed.getStatus())) {
                ipBookService.doCrawler(info);
            }
        }

        return redirect("/crawler.html");
    }

    @RequestMapping(value = "/crawling/batch")
    public String crawlingBatch(@RequestParam(value = "id[]", required = true) List<Integer> ids) {
        if (ids.size() > 0) {
            for (Integer id : ids) {
                IpBook ipBook = ipBookService.getById(id);
                BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo().bookId(ipBook.getId());
                List<BookCrawlerInfo> list = bookCrawlerInfoService.getByRecord(bookCrawlerInfo);

                for (BookCrawlerInfo info : list) {
                    ipBookService.doCrawler(info);
                }
            }
        }

        return redirect("/crawler.html");
    }

    @RequestMapping(value = "/del/batch")
    public String delBatch(@RequestParam(value = "id[]", required = true) List<Integer> ids) {
        if (ids.size() > 0) {
            ipBookService.delByIds(ids);
        }

        return redirect("/crawler.html");
    }

    @RequestMapping(value = "/all")
    public String crawlingAll() {
        ipBookCrawlerTask.crawler();
        return redirect("/crawler.html");
    }


    @RequestMapping(value = "/keyword")
    public String crawlingKeyWord() {
        keyWorkTask.crawler();
        return redirect("/crawler.html");
    }

    @RequestMapping(value = "/soindex")
    public String soindex() {
        soIndexTask.crawler();
        return redirect("/crawler.html");
    }
}
