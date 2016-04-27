package com.dreamy.service.impl.mongo;

import com.dreamy.mogodb.beans.Comments;
import com.dreamy.mogodb.dao.CommentDao;
import com.dreamy.service.iface.mongo.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/4/27.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;
    @Override
    public Comments getById(Integer id) {
        return commentDao.queryById(id);
    }
}
