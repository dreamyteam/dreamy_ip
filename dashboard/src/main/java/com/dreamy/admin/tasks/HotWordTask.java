package com.dreamy.admin.tasks;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.IpTypeEnums;
import com.dreamy.mogodb.beans.HotWord;
import com.dreamy.mogodb.dao.HotWordDao;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/6/16.
 * 获取微博指数热词
 */
public class HotWordTask {


    @Autowired
    private IpBookService ipBookService;

    @Autowired
    HotWordDao hotWordDao;


    public void run() {
        int current = 1;
        IpBook entity = new IpBook();
        while (true) {
            Page page = new Page();
            page.setPageSize(500);
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
                            hotWord.setWname(list.get(0).get("wname"));
                            hotWord.setWid(list.get(0).get("wid"));
                            hotWord.setTitle(book.getTitle());
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
