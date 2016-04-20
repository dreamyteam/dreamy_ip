package com.dreamy.service.mq;

import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by wangyongxing on 16/4/11.
 */
@Service
public class QueueServiceImpl implements QueueService {
    private static final Logger log = LoggerFactory.getLogger(QueueService.class);

    public void setTemplate(RabbitTemplate template) {
        this.template = template;
    }

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    private RabbitTemplate template;


    private RabbitTemplate rabbitTemplate;


    @Override
    public void push(String routingKey, Map<String, Object> data) {
        if (StringUtils.isNotEmpty(routingKey) && data != null) {
            template.convertAndSend(routingKey, JsonUtils.toString(data));
        }
    }

    @Override
    public void push(String routingKey, Object data) {
        if (StringUtils.isNotEmpty(routingKey) && data != null) {
            template.convertAndSend(routingKey, JsonUtils.toString(data));
        }
    }

    /**
     * 入默认交换器队列
     *
     * @param routingKey 路由key
     * @param data       数据
     */
    @Override
    public void pushDefault(String routingKey, Map<String, Object> data) {
        if (StringUtils.isNotEmpty(routingKey) && data != null) {
            rabbitTemplate.convertAndSend(routingKey, JsonUtils.toString(data));
        }
    }

    /**
     * 入默认交换器队列
     *
     * @param routingKey 路由key
     * @param data       数据
     */
    @Override
    public void pushDefault(String routingKey, Object data) {
        if (StringUtils.isNotEmpty(routingKey) && data != null) {
            rabbitTemplate.convertAndSend(routingKey, JsonUtils.toString(data));
        }
    }


}

