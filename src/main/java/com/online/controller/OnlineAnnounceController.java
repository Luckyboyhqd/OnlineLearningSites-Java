package com.online.controller;

import com.online.entity.OnlineAnnounce;
import com.online.service.OnlineAnnounceService;
import com.online.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 公告控制器
 */
@Controller
public class OnlineAnnounceController {
    @Autowired
    private OnlineAnnounceService onlineAnnounceService;

    /**
     * 获取公告
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAnnounces", method = RequestMethod.POST)
    public ResultSet getAnnounces(){
        List<OnlineAnnounce> list = onlineAnnounceService.getAnnounces();
        if(list != null){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(list).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");
    }

    /**
     *插入公告
     * @param announce
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addAnnounces", method = RequestMethod.POST)
    public ResultSet addAnnounces(OnlineAnnounce announce){
        int temp = onlineAnnounceService.addAnnounces(announce);
        if(temp != 0){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(1).setMsg("添加成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(0).setMsg("添加失败");
    }

    /**
     * 删除视频
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delAnnounce", method = RequestMethod.POST)
    public int delAnnounce(String id){
        int temp = onlineAnnounceService.delAnnounce(id);
        return temp;
    }
}
