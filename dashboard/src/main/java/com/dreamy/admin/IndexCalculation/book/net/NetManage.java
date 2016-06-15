package com.dreamy.admin.IndexCalculation.book.net;

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
public class NetManage {
    private Map<Integer, NetBookSourceBaseHandler> handlers = new HashMap<Integer, NetBookSourceBaseHandler>();

    public synchronized void register(NetBookSourceBaseHandler handler) {
        if (handler != null && handler.getHandlerId() > 0) {
            handlers.put(handler.getHandlerId(), handler);
        }
    }

    public NetBookSourceBaseHandler getHandler(int source) {
        NetBookSourceBaseHandler handler = null;
        if (source > 0) {
            handler = handlers.get(source);
        }

        return handler;
    }

    public Map<Integer, NetBookSourceBaseHandler> getHandlerMap() {
        return handlers;
    }
}
