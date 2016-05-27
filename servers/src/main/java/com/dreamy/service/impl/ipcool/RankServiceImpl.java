package com.dreamy.service.impl.ipcool;

import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.iface.ipcool.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/26
 * Time: 下午6:31
 */
@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private BookCrawlerInfoService bookCrawlerInfoService;

    @Autowired
    private IpBookService ipBookService;

    @Override
    public Map<String, String> getCommonParamsByBookIdAndAction(Integer bookId, String action) {
        Map<String, String> commonParams = new HashMap<>();


        IpBook ipBook = ipBookService.getById(bookId);

        if (ipBook != null) {
            commonParams.put("bookId", "" + bookId);
            commonParams.put("key", "book:update:" + bookId);
            commonParams.put("isbn", ipBook.getCode());
            commonParams.put("operation", action);
        }

        return commonParams;
    }
}
