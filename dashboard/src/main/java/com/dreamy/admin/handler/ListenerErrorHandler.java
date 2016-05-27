package com.dreamy.admin.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

public class ListenerErrorHandler implements ErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(ListenerErrorHandler.class);

    @Override
    public void handleError(Throwable e) {
        log.error(e.getMessage());
    }

}
