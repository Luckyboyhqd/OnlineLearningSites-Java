package com.online.service.impl;

import com.online.dao.OnlineUserMapper;
import com.online.dao.OnlineVideoMapper;
import com.online.entity.OnlineUser;
import com.online.entity.OnlineVideo;
import com.online.service.OnlineVideoService;
import com.online.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OnlineVideoServiceImpl implements OnlineVideoService {
    @Autowired
    private OnlineVideoMapper onlineVideoMapper;
    @Autowired
    private OnlineUserMapper onlineUserMapper;

    @Override
    public List<OnlineVideo> getVideos() {
        List<OnlineVideo> list = onlineVideoMapper.getVideos();
        if(list != null && list.size() != 0){
            List<OnlineVideo> videos = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                OnlineVideo video = list.get(i);
                String uId = video.getUserId();
                OnlineUser u = onlineUserMapper.getNameById(uId);
                video.setUserId(u.getName());
                Date date = video.getCreateTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);
                video.setRemark(time);
                videos.add(video);
            }
            for (int i = 0; i < list.size(); i++) {
                OnlineVideo video = list.get(i);
                video.setNum(list.size());
            }
            return videos;
        }
        return null;
    }

    /**
     * 上传视频
     * @param file
     * @param title
     * @param detail
     * @param style
     * @return
     */
    @Override
    public int addVideo(MultipartFile file, String title, String detail, String style, String userId, HttpServletRequest request) {
        if(file == null || file.getSize() == 0){
            return -1;//文件为空 上传失败
        }
        String name = file.getOriginalFilename();//获取视频名字
        String realName = name.substring(0, name.lastIndexOf("."));
        double m = file.getSize() / 1024;
        String size = String.format("%.2f", m).toString() + "KB";//获取视频大小
        OnlineVideo temp = onlineVideoMapper.findVideoExit(name, size);
        if(temp != null){
            return -2;//视频已经存在
        }
        String path = request.getSession().getServletContext().getRealPath("/img/");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(file.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(path + name));
            int len = 0;
            byte[] data = new byte[1024];
            while((len = bis.read(data))!= -1){
                bos.write(data,0,len);
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        OnlineVideo video = new OnlineVideo();
        String uuid = UuidUtils.getUUID();
        video.setId(uuid);
        video.setName(realName);
        video.setTitle(title);
        video.setAddress("/img/" + name);
        video.setSize(size);
        video.setStyle(style);
        video.setDetail(detail);
        video.setCreateTime(new Date());
        video.setUserId(userId);
        video.setStatus(0);
        return onlineVideoMapper.insert(video);
    }

    @Override
    public int delVideo(String id) {
        return onlineVideoMapper.deleteByPrimaryKey(id);
    }
}
