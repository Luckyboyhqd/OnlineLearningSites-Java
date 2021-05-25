package com.online.service.impl;

import com.online.dao.OnlineQuestionsMapper;
import com.online.dao.OnlineSortMapper;
import com.online.dao.OnlineUserMapper;
import com.online.dao.OnlineUserQuestionMapper;
import com.online.entity.OnlineQuestions;
import com.online.entity.OnlineSort;
import com.online.entity.OnlineUser;
import com.online.entity.OnlineUserQuestion;
import com.online.service.OnlineQuestionsService;
import com.online.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OnlineQuestionsServiceImpl implements OnlineQuestionsService {
    @Autowired
    private OnlineUserMapper onlineUserMapper;
    @Autowired
    private OnlineSortMapper onlineSortMapper;
    @Autowired
    private OnlineQuestionsMapper onlineQuestionsMapper;
    @Autowired
    private OnlineUserQuestionMapper onlineUserQuestionMapper;

    /**
     * 获取试题
     * @return
     */
    @Override
    public List<OnlineQuestions> getQuestions(String uId) {
        List<OnlineQuestions> questions = onlineQuestionsMapper.getQuestions();
        if(questions != null && questions.size() != 0){
            List<OnlineQuestions> list = new ArrayList<>();
            for (int i = 0; i < questions.size(); i++) {
                OnlineQuestions q = questions.get(i);
                //获取题目id和用户id 判断题目是否已经做过
                OnlineUserQuestion ouq = onlineUserQuestionMapper.getOnlineUserQuestionByIds(uId, q.getId());
                if(ouq != null){
                    q.setStatus(1);//题目已经做过
                }else{
                    q.setStatus(0);//题目未做过
                    q.setResultSet("无");
                }
                String sortId = q.getSortId();
                OnlineSort sort = onlineSortMapper.getNameById(sortId);
                q.setSortId(sort.getName());
                String userId = q.getUserId();
                OnlineUser user = onlineUserMapper.getNameById(userId);
                q.setUserId(user.getName());
                Date date = q.getCreateTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);
                q.setRemark(time);
                list.add(q);
            }
            for (int i = 0; i < list.size(); i++) {
                OnlineQuestions o = questions.get(i);
                o.setOther(String.valueOf(list.size()));
            }
            return list;
        }
        return null;
    }

    //回显题目详情
    @Override
    public OnlineQuestions getQuestionDetailById(String qId) {
        OnlineQuestions q = onlineQuestionsMapper.selectByPrimaryKey(qId);
        if(q == null){
            return null;
        }
        String sortId = q.getSortId();
        OnlineSort sort = onlineSortMapper.getNameById(sortId);
        q.setSortId(sort.getName());
        return q;
    }

    //答题
    @Transactional
    @Override
    public int addQuestion(String uid, String qid, String resultSet) {
        OnlineUserQuestion q = new OnlineUserQuestion();
        q.setId(UuidUtils.getUUID());
        q.setUserId(uid);
        q.setQuestionId(qid);
        int temp = onlineUserQuestionMapper.insert(q);
        if(temp == 1){
            return onlineQuestionsMapper.updateResultSetById(qid, resultSet);
        }
        return 0;
    }

    @Override
    public int delTest(String id) {
        return onlineQuestionsMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加题目
     * @param questions
     * @return
     */
    @Override
    public int insertQuestion(OnlineQuestions questions) {
        String sortName = questions.getSortId();
        //获取 分类id
        OnlineSort sort = onlineSortMapper.getIdByName(sortName);
        if(sort == null){
            return 0;
        }
        //获取题干 和 结果集
        String sentence = questions.getSentence();
        String correctSet = questions.getCorrectSet();
        //判断是否存
        OnlineQuestions q = onlineQuestionsMapper.getObjBySerntenceAndCorrectSet(sentence, correctSet);
        if(q != null){
            return  0;//题目已经存在
        }
        questions.setId(UuidUtils.getUUID());
        questions.setSortId(sort.getId());
        questions.setType(1);
        questions.setStatus(0);
        questions.setCreateTime(new Date());
        return onlineQuestionsMapper.insert(questions);
    }
}
