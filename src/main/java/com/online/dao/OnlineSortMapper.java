package com.online.dao;

import com.online.entity.OnlineSort;
import com.online.entity.OnlineSortExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OnlineSortMapper {
    int countByExample(OnlineSortExample example);

    int deleteByExample(OnlineSortExample example);

    int deleteByPrimaryKey(String id);

    int insert(OnlineSort record);

    int insertSelective(OnlineSort record);

    List<OnlineSort> selectByExample(OnlineSortExample example);

    OnlineSort selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OnlineSort record, @Param("example") OnlineSortExample example);

    int updateByExample(@Param("record") OnlineSort record, @Param("example") OnlineSortExample example);

    int updateByPrimaryKeySelective(OnlineSort record);

    int updateByPrimaryKey(OnlineSort record);

    /**
     * 获取分类名称
     * @param sortId
     * @return
     */
    OnlineSort getNameById(String sortId);


    OnlineSort getIdByName(String sortName);
}