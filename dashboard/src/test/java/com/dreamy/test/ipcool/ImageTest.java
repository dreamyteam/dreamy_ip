package com.dreamy.test.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.test.BaseJunitTest;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.filesystem.impl.OSSFileSystem;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangyongxing
 * Date: 16/6/22
 * Time: 上午11:48
 * To change this template use File | Settings | File Templates.
 */
public class ImageTest extends BaseJunitTest {

    @Autowired
    BookViewService bookViewService;
    @Autowired
    OSSFileSystem ossFileSystem;

    @Test
    public void upload() throws Exception {
        BookView entity = new BookView().type(2);
        int current = 1;
        String path = "";
        while (true) {
            Page page = new Page();
            page.setPageSize(200);
            page.setCurrentPage(current);
            List<BookView> list = bookViewService.getList(entity, page, " id asc");

            for (BookView bookView : list) {
                InputStream iamgeStream = HttpUtils.getInputStream(bookView.getImageUrl());
                path = "/ipbook/" + bookView.getBookId() + ".jpg";
                System.out.println("ipcool.oss-cn-hangzhou.aliyuncs.com" + "/" + path);
                ossFileSystem.saveFile(iamgeStream, path);
                BookView nea=new BookView().id(bookView.getId()).remark("ipcool.oss-cn-hangzhou.aliyuncs.com"+ path);
                bookViewService.update(nea);

            }
            if(!page.isHasNextPage()){
                break;
            }
            current++;

        }
    }

}
