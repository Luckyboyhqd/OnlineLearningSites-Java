package com.online.service;

import com.online.entity.OnlineAnnounce;

import java.util.List;

public interface OnlineAnnounceService {

    //查询公告
    List<OnlineAnnounce> getAnnounces();

    //添加公告
    int addAnnounces(OnlineAnnounce announce);

    int delAnnounce(String id);
}
