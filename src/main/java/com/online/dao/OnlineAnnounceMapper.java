package com.online.dao;

import com.online.entity.OnlineAnnounce;
import com.online.entity.OnlineAnnounceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OnlineAnnounceMapper {
    int countByExample(OnlineAnnounceExample example);

    int deleteByExample(OnlineAnnounceExample example);

    int deleteByPrimaryKey(String id);

    int insert(OnlineAnnounce record);

    int insertSelective(OnlineAnnounce record);

    List<OnlineAnnounce> selectByExample(OnlineAnnounceExample example);

    OnlineAnnounce selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OnlineAnnounce record, @Param("example") OnlineAnnounceExample example);

    int updateByExample(@Param("record") OnlineAnnounce record, @Param("example") OnlineAnnounceExample example);

    int updateByPrimaryKeySelective(OnlineAnnounce record);

    int updateByPrimaryKey(OnlineAnnounce record);

    /**
     * 查询公告
     * @return
     */
    List<OnlineAnnounce> getAnnounces();

    /**
     * 查询公告 查看是否存在
     * @param title
     * @param userId
     * @return
     */
    OnlineAnnounce getAnnounceByTitleAndUserId(@Param("title")String title, @Param("userId")String userId);
}