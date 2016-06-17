package com.dreamy.admin.IndexCalculation.book.chuban;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/6/15
 * Time: 下午5:27
 */
@Component
public class ChubanManage {
    private Map<Integer, ChubanBookSourceBaseHandler> handlers = new HashMap<Integer, ChubanBookSourceBaseHandler>();

    public synchronized void register(ChubanBookSourceBaseHandler handler) {
        if (handler != null && handler.getHandlerId() > 0) {
            handlers.put(handler.getHandlerId(), handler);
        }
    }

    public ChubanBookSourceBaseHandler getHandler(int source) {
        ChubanBookSourceBaseHandler handler = null;
        if (source > 0) {
            handler = handlers.get(source);
        }

        return handler;
    }

    public Map<Integer, ChubanBookSourceBaseHandler> getHandlerMap() {
        return handlers;
    }
}
