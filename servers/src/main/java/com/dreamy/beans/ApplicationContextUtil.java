package com.dreamy.beans;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created with IntelliJ IDEA.
 * Author: wangyongxing(wangyongxing@duotin.com)
 * Date: 15-9-8 下午2:50
 *
 * @Description:
 */

public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;


    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        this.context = context;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
