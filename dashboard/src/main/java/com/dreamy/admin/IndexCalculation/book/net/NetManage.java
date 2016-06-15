package com.dreamy.admin.IndexCalculation.book.net;

import com.dreamy.admin.IndexCalculation.book.chuban.ChubanBookSourceHandler;
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
    private Map<Integer, NetBookSourceHandler> handlers = new HashMap<Integer, NetBookSourceHandler>();

    public synchronized void register(NetBookSourceHandler handler) {
        if (handler != null && handler.getHandlerId() > 0) {
            handlers.put(handler.getHandlerId(), handler);
        }
    }

    public NetBookSourceHandler getHandler(int source) {
        NetBookSourceHandler handler = null;
        if (source > 0) {
            handler = handlers.get(source);
        }

        return handler;
    }

    public Map<Integer, NetBookSourceHandler> getHandlerMap() {
        return handlers;
    }
}
