package com.dreamy.utils;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/6.
 */
public class BeanUtils extends BeanUtilsBean {

    private static final Logger log = LoggerFactory.getLogger(BeanUtils.class);

    public static final char UNDERLINE = '_';

    @Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if (value == null) {
            return;
        }
        super.copyProperty(dest, name, value);
    }

    @Deprecated
    public static Map<String, Object> toMap(Object bean) {

        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {

            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Object type = descriptor.getPropertyType();
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean, new Object[0]);
                    if (result != null && StringUtils.isNotEmpty(result.toString())) {
//                        if (StringUtils.isNotEmpty(QuerySettings.QUERY_SETTINGS.get(propertyName))) {
//                            returnMap.put(camelToUnderline(propertyName), StringUtils.sqlLike(result.toString()));
//                        } else {
//                            returnMap.put(camelToUnderline(propertyName), result);
//                        }

                    } else {

                    }
                }
            }
        } catch (Exception e) {
            log.error("bean to map  has a error !" + e);

        }
        return returnMap;
    }


    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(camelToUnderline("asasaAasas"));
    }

    public static Map<String, Object> toQueryMap(Object bean) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Object type = descriptor.getPropertyType();
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean, null);
                    if (result != null && result != "" && StringUtils.isNotEmpty(result.toString())) {
                        if (((Class) type).getName().equals("java.lang.String")) {
                            returnMap.put(camelToUnderline(propertyName), StringUtils.sqlLike(result.toString()));
                        } else {
                            returnMap.put(camelToUnderline(propertyName), result);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("bean to map  has a error !" + e);
        }
        return returnMap;
    }
}
