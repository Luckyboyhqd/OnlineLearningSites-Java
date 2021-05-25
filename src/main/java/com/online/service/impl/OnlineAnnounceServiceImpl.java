package com.online.service.impl;

import com.online.dao.OnlineAnnounceMapper;
import com.online.dao.OnlineUserMapper;
import com.online.entity.OnlineAnnounce;
import com.online.entity.OnlineUser;
import com.online.service.OnlineAnnounceService;
import com.online.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OnlineAnnounceServiceImpl implements OnlineAnnounceService {
    @Autowired
    private OnlineAnnounceMapper onlineAnnounceMapper;
    @Autowired
    private OnlineUserMapper onlineUserMapper;

    @Override
    public List<OnlineAnnounce> getAnnounces() {
        List<OnlineAnnounce> list = onlineAnnounceMapper.getAnnounces();
        if(list != null && list.size() != 0){
            List<OnlineAnnounce> ac = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                OnlineAnnounce o = list.get(i);
                String uId = o.getUserId();
                OnlineUser u = onlineUserMapper.getNameById(uId);
                o.setUserId(u.getName());
                ac.add(o);
            }
            for (int i = 0; i < list.size(); i++) {
                OnlineAnnounce oe = list.get(i);
                Date date = oe.getCreateTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);
                oe.setRemark(String.valueOf(list.size()));
                oe.setOther(time);
            }
            return ac;
        }

        return null;
    }

    /**
     * 添加公告
     * @param announce
     * @return
     */
    @Override
    public int addAnnounces(OnlineAnnounce announce) {
        String title = announce.getTitle();
        String userId = announce.getUserId();
        OnlineAnnounce oe = onlineAnnounceMapper.getAnnounceByTitleAndUserId(title, userId);
        if(oe != null){
            return -1;
        }
        announce.setId(UuidUtils.getUUID());
        announce.setStatus(0);
        announce.setCreateTime(new Date());
        return onlineAnnounceMapper.insert(announce);
    }

    @Override
    public int delAnnounce(String id) {
        return onlineAnnounceMapper.deleteByPrimaryKey(id);
    }
}
