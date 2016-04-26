package com.dreamy.crawler;

import com.dreamy.handler.so.SoHandler;
import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.mogodb.dao.BookIndexDataDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;

/**
 * Created by wangyongxing on 16/4/26.
 */
public class SoIndexTest extends BaseJunitTest {
    @Autowired
    private SoHandler soHandler;
    @Autowired
    BookIndexDataDao bookIndexDataDao;

    @Test
    public void so() throws UnsupportedEncodingException {
        BookIndexData bookIndexData = soHandler.getByUrl("黄金时代", "全国");
        bookIndexData.setId(152);
        bookIndexData.setSource(2);
        bookIndexDataDao.save(bookIndexData);

    }
}
