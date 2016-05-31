package com.dreamy.crawler.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.CommentHandler;
import com.dreamy.mogodb.beans.Comment;
import com.dreamy.mogodb.beans.Comments;
import com.dreamy.mogodb.dao.CommentDao;
import com.dreamy.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/18.
 * 爬取评论
 */
@Component
public class CommentEventQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(CommentEventQueueHandler.class);

    @Autowired
    private CommentHandler commentHandler;
    @Autowired
    private CommentDao commentDao;

    @Override
    public void consume(JSONObject jsonObject) {

        Integer bookId = jsonObject.getInteger("ipId");
        String url = jsonObject.getString("url");
        List<Comment> commentList = commentHandler.getByUrl(url);
        if (CollectionUtils.isNotEmpty(commentList)) {
            Comments comment = new Comments();
            comment.setComments(commentList);
            comment.setIpId(bookId);
            commentDao.updateInser(comment);
        }

    }


}
