package com.dreamy.utils.asynchronous;

import java.util.concurrent.Callable;

/**
 * Created by wangyongxing on 16/4/11.
 */
public abstract class ObjectCallable implements Callable {
    protected String name;
    private Object data;

    public ObjectCallable(){
        this("");
    }

    public ObjectCallable(String name){
        this.name = name;
    }

    @Override
    public Object call() throws Exception {
        return run();
    }

    public abstract Object run() throws Exception;

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
