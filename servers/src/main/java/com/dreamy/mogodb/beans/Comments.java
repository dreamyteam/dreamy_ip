package com.dreamy.mogodb.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/18.
 */
@Document(collection="comment")
@TypeAlias("comment")
public class Comments {
    @Id
    private Integer ipId;
    private List<Comment> comments;

    public Integer getIpId() {
        return ipId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setIpId(Integer ipId) {
        this.ipId = ipId;

    }
}
