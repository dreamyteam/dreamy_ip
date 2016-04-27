package com.dreamy.service.iface.mongo;

import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.beans.Comments;

/**
 * Created by wangyongxing on 16/4/27.
 */
public interface CommentService {

    /**
     * @param id
     * @return
     */
    Comments getById(Integer id);
}
