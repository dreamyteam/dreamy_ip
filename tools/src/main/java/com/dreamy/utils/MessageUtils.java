package com.dreamy.utils;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.utils.SerializationUtils;

/**

/**
 * Created by wangyongxing on 16/4/12.
 */
public class MessageUtils {

    private static final String ENCODING = "UTF-8";

    public static String getContentAsString(byte[] content, MessageProperties messageProperties) {
        if (content == null) {
            return null;
        }
        try {
            String contentType = (messageProperties != null) ? messageProperties.getContentType() : null;
            if (MessageProperties.CONTENT_TYPE_SERIALIZED_OBJECT.equals(contentType)) {
                return SerializationUtils.deserialize(content).toString();
            }
            return new String(content, ENCODING);
        } catch (Exception e) {
        }
        return content.toString();
    }
}