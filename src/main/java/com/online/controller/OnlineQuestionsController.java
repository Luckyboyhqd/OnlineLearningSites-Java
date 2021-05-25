package com.online.controller;

import com.online.entity.OnlineQuestions;
import com.online.service.OnlineQuestionsService;
import com.online.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OnlineQuestionsController {
    @Autowired
    private OnlineQuestionsService onlineQuestionsService;

    /**
     * 获取试题
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getQuestions", method = RequestMethod.POST)
    public ResultSet getQuestions(String uId){
        List<OnlineQuestions> questions = onlineQuestionsService.getQuestions(uId);
        if(questions != null){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(questions).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");
    }

    /**
     * 获取考题详情
     * @param qId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getQuestionDetailById", method = RequestMethod.POST)
    public ResultSet getQuestionDetailById(String qId){
        OnlineQuestions questions = onlineQuestionsService.getQuestionDetailById(qId);
        if(questions != null){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(questions).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");
    }

    /**
     * 答题
     * @param uid
     * @param qid
     * @param resultSet
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
    public ResultSet addQuestion(String uid, String qid, String resultSet){
        int temp = onlineQuestionsService.addQuestion(uid, qid, resultSet);
        if(temp != 0){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(1).setMsg("成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(0).setMsg("失败");
    }

    /**
     * 添加题目
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertQuestion", method = RequestMethod.POST)
    public ResultSet insertQuestion(OnlineQuestions questions){
        int temp = onlineQuestionsService.insertQuestion(questions);
        if(temp != 0){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(1).setMsg("成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(0).setMsg("失败");
    }


    /**
     * 删除试题
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delTest", method = RequestMethod.POST)
    public int delTest(String id){
        int temp = onlineQuestionsService.delTest(id);
        return temp;
    }

}
