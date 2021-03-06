package com.dreamy.crawler.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.service.mq.QueueService;
import com.dreamy.utils.MessageUtils;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractQueueHandler implements MessageListener {


    private static final Logger log = LoggerFactory.getLogger(AbstractQueueHandler.class);


    @Override
    public void onMessage(Message message) {
        try {
            String json = MessageUtils.getContentAsString(message.getBody(), message.getMessageProperties());
            if (StringUtils.isNotEmpty(json)) {
                JSONObject jsonObject = JSONObject.parseObject(json);
                try {
                    consume(jsonObject);
                } catch (Exception e) {
                    log.error("处理异常JSON[" + json + "]", e);

                }
            }
        } catch (Throwable e) {
            log.error("json pars error,json body:" + message.getBody(), e);
        }

    }

    /**
     * 处理逻辑
     *
     * @param jsonObject
     */
    public abstract void consume(JSONObject jsonObject);


}
