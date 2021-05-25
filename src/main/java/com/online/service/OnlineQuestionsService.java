package com.online.service;

import com.online.entity.OnlineQuestions;

import java.util.List;

public interface OnlineQuestionsService {

    //查询试题
    List<OnlineQuestions> getQuestions(String uId);

    //获取考题详情
    OnlineQuestions getQuestionDetailById(String qId);

    //答题
    int addQuestion(String uid, String qid, String resultSet);

    int delTest(String id);

    int insertQuestion(OnlineQuestions questions);
}
