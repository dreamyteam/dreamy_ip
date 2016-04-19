package com.dreamy.mapper.ipcool;

import com.dreamy.domain.ipcool.Algorithm;
import com.dreamy.domain.ipcool.AlgorithmConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface AlgorithmMapper extends BaseMapper<Algorithm,Integer,AlgorithmConditions> {
    int countByExample(AlgorithmConditions example);

    int deleteByExample(AlgorithmConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(Algorithm record);

    int insertSelective(Algorithm record);

    List<Algorithm> selectByExample(AlgorithmConditions example);

    Algorithm selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Algorithm record, @Param("example") AlgorithmConditions example);

    int updateByExample(@Param("record") Algorithm record, @Param("example") AlgorithmConditions example);

    int updateByPrimaryKeySelective(Algorithm record);

    int updateByPrimaryKey(Algorithm record);
}