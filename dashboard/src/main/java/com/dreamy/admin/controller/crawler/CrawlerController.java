package com.dreamy.admin.controller.crawler;

import com.dreamy.admin.controller.DashboardController;
import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.IpBook;
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
 * Created by wangyongxing on 16/4/11.
 */
@Controller
@RequestMapping(value = "/crawler")
public class CrawlerController extends DashboardController {
    @Resource
    private IpBookService ipBookService;
    @Resource
    private BookCrawlerInfoService bookCrawlerInfoService;


    /**
     * 角色列表
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String role(HttpServletRequest request, ModelMap model, Page page) {
        IpBook ipBook=new IpBook();
        List<IpBook> list = ipBookService.getIpBookList(ipBook,page);
        model.put("list", list);
        return "/crawler/ipbook";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(@RequestParam(value = "id", required = false) Integer id, ModelMap model) {
        if(id>0)
        {
            IpBook ipBook=ipBookService.getById(id);
            BookCrawlerInfo bookCrawlerInfo=new BookCrawlerInfo().bookId(ipBook.getId());
            List<BookCrawlerInfo> list=bookCrawlerInfoService.getBy(bookCrawlerInfo);
            model.put("book",ipBook);
            model.put("list",list);
        }

        return "/craw/ipbook-view";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(IpBook ipBook,BookCrawlerInfo bookCrawlerInfo) {
        if(ipBook.getId()>0)
        {
            ipBookService.save(ipBook);
            bookCrawlerInfo.setBookId(ipBook.getId());
            bookCrawlerInfoService.save(bookCrawlerInfo);
        }
        else{
            ipBookService.update(ipBook);
            bookCrawlerInfo.setBookId(ipBook.getId());
            bookCrawlerInfoService.update(bookCrawlerInfo);
        }
        return redirect("/crawler.html");
    }


    }
