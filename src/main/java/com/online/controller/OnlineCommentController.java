package com.online.controller;

import com.online.entity.OnlineComment;
import com.online.service.OnlineCommentService;
import com.online.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OnlineCommentController {
    @Autowired
    private OnlineCommentService onlineCommentService;

    /**
     * 查询评论
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getComments", method = RequestMethod.POST)
    public ResultSet getComments(){
        List<OnlineComment> list = onlineCommentService.getComments();
        List<OnlineComment> l = new ArrayList<>();
        if(list != null && list.size()!= 0){
            for (int i = 0; i < list.size(); i++) {
                OnlineComment t = list.get(i);
                Date date = t.getCommentTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);
                t.setNum(list.size());
                t.setRemark(time);
                l.add(t);
            }
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(l).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");
    }

    /**
     * 添加信息
     * @param uid
     * @param comment
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public ResultSet addComment(String uid, String comment){
        int temp = onlineCommentService.addComment(uid, comment);
        if(temp == 1){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(temp).setMsg("成功");
        }else if(temp == -1){
            return new ResultSet().setCode(HttpStatus.UNAUTHORIZED.value()).setData(-1).setMsg("评论已经存在");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(0).setMsg("失败");
    }

}
