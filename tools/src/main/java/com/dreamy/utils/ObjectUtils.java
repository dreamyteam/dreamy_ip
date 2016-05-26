package com.dreamy.utils;

import org.apache.commons.beanutils.ConvertUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Locale;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/1.
 */
public final class ObjectUtils {

    /**
     * 对象为空时赋默认值
     *
     * @param value 目标对象
     * @param def   默认值
     * @return
     */
    public static <T> T nullToDefault(T value, T def) {
        return value == null ? def : value;
    }

    /**
     * @param type
     * @param map
     * @return
     * @throws Exception
     */
    public static Object convertMapToObject(Class type, Map<String, String> map)
            throws Exception {
        // 创建 JavaBean 对象
        Object obj = null;

        // 获取类属性
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        obj = type.newInstance();

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo
                .getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName().toLowerCase(
                    Locale.getDefault());

            if (map.containsKey(propertyName)) {
                String value = ConvertUtils.convert(map.get(propertyName));
                Object[] args = new Object[1];
                args[0] = ConvertUtils.convert(value, descriptor
                        .getPropertyType());

                descriptor.getWriteMethod().invoke(obj, args);

            }
        }
        return obj;
    }


}