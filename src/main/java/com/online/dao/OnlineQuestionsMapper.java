package com.online.dao;

import com.online.entity.OnlineQuestions;
import com.online.entity.OnlineQuestionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OnlineQuestionsMapper {
    int countByExample(OnlineQuestionsExample example);

    int deleteByExample(OnlineQuestionsExample example);

    int deleteByPrimaryKey(String id);

    int insert(OnlineQuestions record);

    int insertSelective(OnlineQuestions record);

    List<OnlineQuestions> selectByExample(OnlineQuestionsExample example);

    OnlineQuestions selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OnlineQuestions record, @Param("example") OnlineQuestionsExample example);

    int updateByExample(@Param("record") OnlineQuestions record, @Param("example") OnlineQuestionsExample example);

    int updateByPrimaryKeySelective(OnlineQuestions record);

    int updateByPrimaryKey(OnlineQuestions record);

    /**
     * 获取考题
     * @return
     */
    List<OnlineQuestions> getQuestions();

    /**
     * 答题
     * @param qid
     * @param resultSet
     * @return
     */
    int updateResultSetById(@Param("qid")String qid, @Param("resultSet")String resultSet);


    OnlineQuestions getObjBySerntenceAndCorrectSet(@Param("sentence")String sentence, @Param("correctSet")String correctSet);
}