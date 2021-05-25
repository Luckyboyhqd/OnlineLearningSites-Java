package com.online.dao;

import com.online.entity.OnlineUserQuestion;
import com.online.entity.OnlineUserQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OnlineUserQuestionMapper {
    int countByExample(OnlineUserQuestionExample example);

    int deleteByExample(OnlineUserQuestionExample example);

    int deleteByPrimaryKey(String id);

    int insert(OnlineUserQuestion record);

    int insertSelective(OnlineUserQuestion record);

    List<OnlineUserQuestion> selectByExample(OnlineUserQuestionExample example);

    OnlineUserQuestion selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OnlineUserQuestion record, @Param("example") OnlineUserQuestionExample example);

    int updateByExample(@Param("record") OnlineUserQuestion record, @Param("example") OnlineUserQuestionExample example);

    int updateByPrimaryKeySelective(OnlineUserQuestion record);

    int updateByPrimaryKey(OnlineUserQuestion record);

    /**
     * 获取对象
     * @param uId
     * @param qId
     * @return
     */
    OnlineUserQuestion getOnlineUserQuestionByIds(@Param("uId")String uId, @Param("qId")String qId);
}