package com.online.service.impl;

import com.online.dao.OnlineUserMapper;
import com.online.entity.OnlineUser;
import com.online.service.OnlineUserService;
import com.online.utils.UuidUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OnlineUserServiceImpl implements OnlineUserService {
    @Autowired
    private OnlineUserMapper onlineUserMapper;
    /**
     * 登录验证
     * @param loginName
     * @param password
     * @return
     */
    @Transactional
    @Override
    public OnlineUser getUserByLoginNameAndPassword(String loginName, String password) {
        OnlineUser users = onlineUserMapper.getUserByName(loginName);
        if(users != null){
            //将查询出来的密码和前端传过来的密码进行验证
            if(users.getPassword().equals(password)){
                //登录成功
                return users;
            }
        }
        return null;
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Transactional
    @Override
    public int register(OnlineUser user) {
        String num = user.getSerialNumber();
        OnlineUser u = onlineUserMapper.getUserBySerialNumber(num);
        if(u != null){
            return -1;//用户已经存在
        }else{
            if(user.getSerialNumber() == null || user.getPassword() == null || user.getName() == null){
                return 0;
            }
            user.setId(UuidUtils.getUUID());
            user.setPassword(user.getPassword());
            user.setStatus(1);
            return onlineUserMapper.insert(user);
        }
    }

    /**
     * admin 登录
     */
    @Override
    public OnlineUser getAdminUserByLoginNameAndPassword(String loginName, String pwd) {
        OnlineUser users = onlineUserMapper.getAdminUserByName(loginName);
        if(users != null){
            //将查询出来的密码和前端传过来的密码进行验证
            if(users.getPassword().equals(pwd)){
                //登录成功
                return users;
            }
        }
        return null;
    }

    /**
     * 获取所有用户
     * @return
     */
    @Override
    public List<OnlineUser> getUsers() {
        List<OnlineUser> user = onlineUserMapper.getUsers();
        if(user != null && user.size() != 0){
            for (int i = 0; i < user.size(); i++) {
                OnlineUser u = user.get(i);
                u.setRemark(String.valueOf(user.size()));
            }
            return user;
        }
        return null;
    }

    /**
     * 审核用户
     * @param id
     * @param status
     * @return
     */
    @Override
    public int changeStatus(String id, int status) {
        return onlineUserMapper.changeStatus(id, status);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public int delUser(String id) {
        return onlineUserMapper.deleteByPrimaryKey(id);
    }

    /**
     * MD5加密
     * @param password
     * @return
     */
    public static String MD5Util(String password){
        String hashAlgorithmName = "MD5";// 加密方法
        int hashIterations = 1;// 加密次数
        ByteSource credentialsSalt = null;// 加盐
        Object obj = new SimpleHash(hashAlgorithmName, password, credentialsSalt, hashIterations);
        String pwd = obj.toString();//加密后密码
        return pwd;
    }

}
