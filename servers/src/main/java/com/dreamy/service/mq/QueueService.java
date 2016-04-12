package com.dreamy.service.mq;

import java.util.Map;

/**
 * Created by wangyongxing on 16/4/11.
 */
public interface QueueService {

    /**
     * 入队列
     * @param routingKey 路由key
     * @param data 数据
     */
    void push(String routingKey, Map<String, Object> data);

    /**
     * 入队列
     * @param routingKey 路由key
     * @param data 数据
     */
    void push(String routingKey, Object data);


    /**
     * 入默认交换器队列
     * @param routingKey 路由key
     * @param data 数据
     */
    void pushDefault(String routingKey, Map<String, Object> data);

    /**
     * 入默认交换器队列
     * @param routingKey 路由key
     * @param data 数据
     */
    void pushDefault(String routingKey, Object data);


}
