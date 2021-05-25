package com.online.service;

import com.online.entity.OnlineUser;

import java.util.List;

public interface OnlineUserService {

    //查询用户
    OnlineUser getUserByLoginNameAndPassword(String loginName, String pwd);

    //注册用户
    int register(OnlineUser user);

    OnlineUser getAdminUserByLoginNameAndPassword(String loginName, String pwd);

    //获取用户
    List<OnlineUser> getUsers();

    //删除用户
    int delUser(String id);

    //审核用户
    int changeStatus(String id, int status);
}
