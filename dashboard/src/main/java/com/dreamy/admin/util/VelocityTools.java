package com.dreamy.admin.util;

import java.util.List;
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
        return list.contains(id);
    }
}
