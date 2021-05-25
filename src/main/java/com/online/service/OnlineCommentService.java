package com.online.service;

import com.online.entity.OnlineComment;

import java.util.List;

public interface OnlineCommentService {

    //查看评论
    List<OnlineComment> getComments();

    int addComment(String uid, String comment);
}
