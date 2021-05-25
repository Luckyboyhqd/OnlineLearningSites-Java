package com.online.controller;

import com.online.entity.OnlineVideo;
import com.online.service.OnlineVideoService;
import com.online.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
public class OnlineVideoController {
    @Autowired
    private OnlineVideoService onlineVideoService;

    /**
     * 获取视频列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getVideos", method = RequestMethod.POST)
    public ResultSet getVideos(){
        List<OnlineVideo> videos = onlineVideoService.getVideos();
        if(videos != null){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(videos).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");
    }

    /**
     * 添加视频
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addVideo", method = RequestMethod.POST)
    public ResultSet addVideo(@RequestParam(value = "file", required = false) MultipartFile file, String title, String detail, String style, String userId, HttpServletRequest request){
        int num = onlineVideoService.addVideo(file, title, detail, style, userId, request);
        if(num == 1){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(num).setMsg("上传成功");
        }else if(num == -1){
            //文件为空 上传失败
            return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(-1).setMsg("文件为空");
        }else if(num == -2){
            //视频已经存在
            return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(-2).setMsg("视频已经存在");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(0).setMsg("上传失败");
    }

    /**
     * 删除视频
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delVideo", method = RequestMethod.POST)
    public int delVideo(String id){
        int temp = onlineVideoService.delVideo(id);
        return temp;
    }
}
