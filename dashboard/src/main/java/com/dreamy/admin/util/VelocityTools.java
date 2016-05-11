package com.dreamy.admin.util;

import com.dreamy.utils.CollectionUtils;

import java.util.Set;

/**
 * Created by wangyongxing on 16/4/11.
 */
public class VelocityTools {
    /**
     * 是否包含
     * @param list
     * @param id
     * @return
     */
    public static Boolean contains(Set<Integer> list, Integer id) {
        if(CollectionUtils.isNotEmpty(list)) {
            return list.contains(id);
        }
        return false;
    }
}
