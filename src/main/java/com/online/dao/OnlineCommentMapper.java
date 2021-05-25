package com.online.dao;

import com.online.entity.OnlineComment;
import com.online.entity.OnlineCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OnlineCommentMapper {
    int countByExample(OnlineCommentExample example);

    int deleteByExample(OnlineCommentExample example);

    int deleteByPrimaryKey(String id);

    int insert(OnlineComment record);

    int insertSelective(OnlineComment record);

    List<OnlineComment> selectByExample(OnlineCommentExample example);

    OnlineComment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OnlineComment record, @Param("example") OnlineCommentExample example);

    int updateByExample(@Param("record") OnlineComment record, @Param("example") OnlineCommentExample example);

    int updateByPrimaryKeySelective(OnlineComment record);

    int updateByPrimaryKey(OnlineComment record);

    /**
     * 查看评论
     * @return
     */
    List<OnlineComment> getComments();
}