package com.dreamy.test.crawler;

import com.dreamy.domain.ipcool.BookView;
import com.dreamy.mogodb.beans.Book;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.test.BaseJunitTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangyongxing on 16/4/26.
 */
public class IpBookTest extends BaseJunitTest{
    @Resource
    private BookInfoService bookInfoService;
    @Resource
    BookViewService bookViewService;
    @Test
    public void insert(){
       List<BookInfo> list= bookInfoService.getList(151);
        BookView view=null;
        for(BookInfo bookInfo:list)
        {
            if(bookInfo.getSource()==4){
                view=new BookView();
                view.setName(bookInfo.getTitle());
                view.setAuthor(bookInfo.getAuthor());
                view.introduction(bookInfo.getInfo());
                view.bookId(bookInfo.getIpId());
                view.imageUrl(bookInfo.getImage());
                view.status(0);
                view.type(1);
                bookViewService.save(view);

                view.compositeIndex(9999);
                view.hotIndex(5555);
                view.activityIndex(3333);
                view.propagateIndex(666);
                view.developIndex(4444);
                bookViewService.update(view);

            }

        }

    }
    @Test
    public void update(){
        BookView bookView=new BookView();
        bookView.id(1);
        bookView.bookId(110);
        bookView.compositeIndex(10000);
        bookView.hotIndex(8888);
        bookView.activityIndex(6666);
        bookView.propagateIndex(3333);
        bookView.developIndex(7777);
        bookViewService.update(bookView);
    }
}
