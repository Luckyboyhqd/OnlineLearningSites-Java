package com.online.service.impl;

import com.online.dao.OnlineCommentMapper;
import com.online.dao.OnlineUserMapper;
import com.online.entity.OnlineComment;
import com.online.entity.OnlineUser;
import com.online.service.OnlineCommentService;
import com.online.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class OnlineCommentServiceImpl implements OnlineCommentService {
    @Autowired
    private OnlineCommentMapper onlineCommentMapper;
    @Autowired
    private OnlineUserMapper onlineUserMapper;

    @Override
    public List<OnlineComment> getComments() {
        return onlineCommentMapper.getComments();
    }

    @Override
    public int addComment(String uid, String comment) {
        OnlineUser user = onlineUserMapper.getNameById(uid);
        if(user == null){
            return 0;
        }
        String name = user.getName();
        String phone = user.getPhone();
        OnlineComment ct = new OnlineComment();
        ct.setId(UuidUtils.getUUID());
        ct.setComment(comment);
        ct.setCommentTime(new Date());
        ct.setUserName(name);
        ct.setUserPhone(phone);
        return onlineCommentMapper.insert(ct);
    }
}
