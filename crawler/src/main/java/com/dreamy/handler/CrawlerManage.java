package com.dreamy.handler;

import com.dreamy.handler.CrawlerHandler;
import com.dreamy.mogodb.beans.BookInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/6.
 */
public class CrawlerManage {

    private Map<Integer, CrawlerHandler> handlers = new HashMap<Integer, CrawlerHandler>();

    private CrawlerHandler DEFAULT_HANDLER = new CrawlerHandler() {

        @Override
        public int getId() {
            return 0;
        }

        @Override
        public List<BookInfo> analye(String url, int type) {
            return null;
        }

        @Override
        public BookInfo getByUrl(String url) {
            return null;
        }

        @Override
        public String analyeUrl(String url) {
            return null;
        }
    };

    public synchronized void register(CrawlerHandler handler) {
        if (handler != null && handler.getId() > 0) {
            handlers.put(handler.getId(), handler);
        }
    }

    public CrawlerHandler getHandler(int id) {
        CrawlerHandler handler = null;
        if (id > 0) {
            handler = handlers.get(id);
        }
        return handler != null ? handler : DEFAULT_HANDLER;
    }

    public Object crawler(int id, String url, int type) {
        CrawlerHandler handler = getHandler(id);
        if (handler != null) {
            return handler.analye(url, type);
        }
        return null;
    }

    public Map<Integer, CrawlerHandler> getHandlerMap() {
        return handlers;
    }
}
