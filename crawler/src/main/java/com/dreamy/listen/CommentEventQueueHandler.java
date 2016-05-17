package com.dreamy.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.handler.CommentHandler;
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
 */
@Component
public class CommentEventQueueHandler  extends  AbstractQueueHandler{

    private static final Logger log = LoggerFactory.getLogger(CrawlerEventQueueHandler.class);

    @Autowired
    private CommentHandler commentHandler;
    @Autowired
    private CommentDao commentDao;

    @Override
    public void consume(JSONObject jsonObject) {
        //获取类型
//        Integer type = jsonObject.getInteger("type");
//        Integer ipId=jsonObject.getInteger("ipId");
//        Integer crawlerId=jsonObject.getInteger("crawlerId");
//        String url=jsonObject.getString("url");

        String isbn=jsonObject.getString("isbn");
        String url=jsonObject.getString("url");
        Integer bookId=jsonObject.getInteger("bookId");

        List<Comment> commentList= commentHandler.getByUrl(url);
        if(CollectionUtils.isNotEmpty(commentList))
        {
            Comments comment=new Comments();
            comment.setIsbn(isbn);
            comment.setIpId(bookId);
            comment.setComments(commentList);
            commentDao.save(comment);
        }

    }
}
