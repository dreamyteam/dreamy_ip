package com.dreamy.service.impl.ipcool;

import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.IpBook;
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
    private IpBookService ipBookService;

    @Override
    public Map<String, String> getCommonParamsByBookIdAndAction(BookView bookView, String action) {
        Map<String, String> commonParams = new HashMap<>();
        IpBook ipBook = ipBookService.getById(bookView.getBookId());
        if (ipBook != null) {
            commonParams.put("bookId", "" + bookView.getBookId());
            commonParams.put("key", "book:update:" + bookView.getBookId());
            commonParams.put("isbn", ipBook.getCode());
            commonParams.put("operation", action);
            commonParams.put("tieba_keyword", ipBook.getTiebaKeyword());
            commonParams.put("index_keyword", ipBook.getIndexKeyword());
//            commonParams.put("news_keyword", ipBook.getNewsKeyword());
//            commonParams.put("search_keyword", ipBook.getSearchKeyword());
            commonParams.put("news_keyword", "《" + bookView.getName() + "》 " + bookView.getAuthor());
            commonParams.put("search_keyword", "《" + bookView.getName() + "》 " + bookView.getAuthor());

        }


        return commonParams;
    }
}
