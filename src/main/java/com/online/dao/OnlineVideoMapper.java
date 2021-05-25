package com.online.dao;

import com.online.entity.OnlineVideo;
import com.online.entity.OnlineVideoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OnlineVideoMapper {
    int countByExample(OnlineVideoExample example);

    int deleteByExample(OnlineVideoExample example);

    int deleteByPrimaryKey(String id);

    int insert(OnlineVideo record);

    int insertSelective(OnlineVideo record);

    List<OnlineVideo> selectByExample(OnlineVideoExample example);

    OnlineVideo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OnlineVideo record, @Param("example") OnlineVideoExample example);

    int updateByExample(@Param("record") OnlineVideo record, @Param("example") OnlineVideoExample example);

    int updateByPrimaryKeySelective(OnlineVideo record);

    int updateByPrimaryKey(OnlineVideo record);

    /**
     * 获取视频
     * @return
     */
    List<OnlineVideo> getVideos();

    /**
     * 查看是否存在
     * @param name
     * @param size
     * @return
     */
    OnlineVideo findVideoExit(@Param("name")String name, @Param("size")String size);
}