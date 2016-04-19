package com.dreamy.admin.controller.crawler;

import com.dreamy.admin.beans.BookCrawlerModel;
import com.dreamy.admin.beans.Constants;
import com.dreamy.admin.controller.DashboardController;
import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.utils.ConstUtil;
import com.dreamy.utils.QueueRoutingKey;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    private  QueueService queueService;


    /**
     *
     *
     * @return
     */
    @RequestMapping("")
    public String role(IpBook ipBook, HttpServletRequest request, ModelMap model, Page page) {
        List<IpBook> list = ipBookService.getIpBookList(ipBook, page);
        model.put("list", list);
        model.put("page",page);
        return "/crawler/ipbook";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(@RequestParam(value = "id", required = true) Integer id, ModelMap model) {

        IpBook ipBook = ipBookService.getById(id);
        BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo().bookId(ipBook.getId());
        List<BookCrawlerInfo> list = bookCrawlerInfoService.getBy(bookCrawlerInfo);
        model.put("book", ipBook);

        for (BookCrawlerInfo info : list) {
            model.put("url" + info.getSource(), info.getUrl());
        }
        return "/crawler/ipbook-view";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insert(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id, ModelMap model) {

        return "/crawler/ipbook-insert";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam(value = "id", required = true) Integer id, ModelMap model) {

        IpBook ipBook = ipBookService.getById(id);
        BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo().bookId(ipBook.getId());
        List<BookCrawlerInfo> list = bookCrawlerInfoService.getBy(bookCrawlerInfo);
        model.put("book", ipBook);
        model.put("list", list);

        return "/crawler/ipbook-edit";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String update(IpBook ipBook, BookCrawlerModel infos) {
        List<BookCrawlerInfo> list = infos.getInfos();
        if (ipBook.getId() != null && ipBook.getId() > 0) {
            ipBookService.update(ipBook,list);
        } else {
            ipBook.type(1);
            ipBook.status(1);
            ipBookService.save(ipBook, list);


        }
        return redirect("/crawler.html");
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String del(@RequestParam(value = "ids", required = true) List<Integer> ids) {
        ipBookService.del(ids);
        return redirect("/crawler.html");
    }
    @RequestMapping(value = "/crawling")
    public String crawling(@RequestParam(value = "id", required = true)Integer id)
    {
        IpBook ipBook = ipBookService.getById(id);
        BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo().bookId(ipBook.getId());
        List<BookCrawlerInfo> list = bookCrawlerInfoService.getBy(bookCrawlerInfo);

        for(BookCrawlerInfo info:list)
        {
            Map<String,Object> map=new HashMap<>();
            map.put("type",info.getSource());
            map.put("url",info.getUrl());
            map.put("ipId",info.getBookId());
            queueService.push(QueueRoutingKey.CRAWLER_EVENT,map);
            if(info.getSource().equals(CrawlerSourceEnums.douban.getType())) {
                queueService.push(QueueRoutingKey.CRAWLER_COMMENT, map);
            }
        }
        return redirect("/crawler.html");

    }


}
