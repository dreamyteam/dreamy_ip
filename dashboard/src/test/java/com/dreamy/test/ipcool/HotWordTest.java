package com.dreamy.test.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.IpTypeEnums;
import com.dreamy.mogodb.beans.HotWord;
import com.dreamy.mogodb.dao.HotWordDao;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.test.BaseJunitTest;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.TimeUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/6/14.
 */
public class HotWordTest extends BaseJunitTest {

    @Autowired
    private IpBookService ipBookService;

    @Autowired
    HotWordDao hotWordDao;

    @Test
    public void createChubanHotWord() {
        int current = 1;
        IpBook entity = new IpBook();
        entity.setType(IpTypeEnums.chuban.getType());
        while (true) {
            Page page = new Page();
            page.setPageSize(200);
            page.setCurrentPage(current);
            List<IpBook> books = ipBookService.getIpBookList(entity, page);
            for (IpBook book : books) {
                String title = HttpUtils.encodeUrl(book.getIndexKeyword());
                String url = "http://data.weibo.com/index/ajax/hotword?flag=like&word=" + title + "&_t=0&__rnd=" + System.currentTimeMillis();
                String html = HttpUtils.getHtmlGet(url);
                String str = HttpUtils.decodeUnicode(html);
                System.out.println(str);
                try {
                    Map<String, Object> map = JsonUtils.toMap(str);
                    if (map != null) {
                        String code = map.get("code").toString();
                        if (code.equals("100000")) {
                            List<Map<String, String>> list = (List<Map<String, String>>) map.get("data");
                            HotWord hotWord = new HotWord();
                            hotWord.setId(book.getId());
                            hotWord.setWid(list.get(0).get("wid"));
                            hotWord.setWname(list.get(0).get("wname"));
                            hotWord.setTitle(book.getTitle());
                            hotWord.setUpdatedAt(TimeUtils.toString("yyyy-mm-dd HH:MM:ss", new Date()));
                            hotWordDao.updateInser(hotWord);
                        }
                    }
                } catch (Exception E) {
                    continue;
                }
            }
            if (!page.isHasNextPage()) {

                break;
            }
            current++;

        }

    }

    @Test
    public void createNetBookHotWord() {
        int current = 50;
        IpBook entity = new IpBook();
        entity.setType(IpTypeEnums.net.getType());

        while (true) {
            Page page = new Page();
            page.setPageSize(200);
            page.setCurrentPage(current);
            List<IpBook> books = ipBookService.getIpBookList(entity, page);
            for (IpBook book : books) {
                String title = HttpUtils.encodeUrl(book.getIndexKeyword());
                String url = "http://data.weibo.com/index/ajax/hotword?flag=like&word=" + title + "&_t=0&__rnd=" + System.currentTimeMillis();
                String html = HttpUtils.getHtmlGet(url);
                String str = HttpUtils.decodeUnicode(html);
                try {
                    Map<String, Object> map = JsonUtils.toMap(str);
                    if (map != null) {
                        String code = map.get("code").toString();
                        if (code.equals("100000")) {
                            List<Map<String, String>> list = (List<Map<String, String>>) map.get("data");
                            HotWord hotWord = new HotWord();
                            hotWord.setId(book.getId());
                            hotWord.setTitle(book.getTitle());
                            hotWord.setWid(list.get(0).get("wid"));
                            hotWord.setWname(list.get(0).get("wname"));
                            hotWord.setUpdatedAt(TimeUtils.toString("yyyy-mm-dd HH:MM:ss", new Date()));
                            hotWordDao.updateInser(hotWord);
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            if (!page.isHasNextPage()) {

                break;
            }
            current++;

        }

    }
}
