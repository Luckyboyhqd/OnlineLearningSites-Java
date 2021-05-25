package com.online.service;

import com.online.entity.OnlineVideo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OnlineVideoService {

    //获取视频
    List<OnlineVideo> getVideos();

    //上传视频
    int addVideo(MultipartFile file, String title, String detail, String style, String userId, HttpServletRequest request);

    int delVideo(String id);
}
