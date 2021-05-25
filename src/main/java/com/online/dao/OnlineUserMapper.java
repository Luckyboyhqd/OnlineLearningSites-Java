package com.online.dao;

import com.online.entity.OnlineUser;
import com.online.entity.OnlineUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OnlineUserMapper {
    int countByExample(OnlineUserExample example);

    int deleteByExample(OnlineUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(OnlineUser record);

    int insertSelective(OnlineUser record);

    List<OnlineUser> selectByExample(OnlineUserExample example);

    OnlineUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OnlineUser record, @Param("example") OnlineUserExample example);

    int updateByExample(@Param("record") OnlineUser record, @Param("example") OnlineUserExample example);

    int updateByPrimaryKeySelective(OnlineUser record);

    int updateByPrimaryKey(OnlineUser record);

    /**
     * 登录
     * @param loginName
     * @return
     */
    OnlineUser getUserByName(String loginName);

    /**
     * 查看是否存在账号
     * @param num
     * @return
     */
    OnlineUser getUserBySerialNumber(String num);

    /**
     * 获取用户名
     * @param uId
     * @return
     */
    OnlineUser getNameById(String uId);


    OnlineUser getAdminUserByName(String loginName);

    /**
     * 获取用户集合
     * @return
     */
    List<OnlineUser> getUsers();

    /**
     * 审核账号
     * @param id
     * @param status
     * @return
     */
    int changeStatus(@Param("id")String id, @Param("status")int status);
}