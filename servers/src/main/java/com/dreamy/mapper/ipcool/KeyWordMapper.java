package com.dreamy.mapper.ipcool;

import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.domain.ipcool.KeyWordConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface KeyWordMapper extends BaseMapper<KeyWord,Integer,KeyWordConditions> {
    int countByExample(KeyWordConditions example);

    int deleteByExample(KeyWordConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(KeyWord record);

    int insertSelective(KeyWord record);

    List<KeyWord> selectByExample(KeyWordConditions example);

    KeyWord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") KeyWord record, @Param("example") KeyWordConditions example);

    int updateByExample(@Param("record") KeyWord record, @Param("example") KeyWordConditions example);

    int updateByPrimaryKeySelective(KeyWord record);

    int updateByPrimaryKey(KeyWord record);
}