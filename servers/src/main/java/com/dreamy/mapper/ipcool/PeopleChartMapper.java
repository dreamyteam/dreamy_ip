package com.dreamy.mapper.ipcool;

import com.dreamy.domain.ipcool.PeopleChart;
import com.dreamy.domain.ipcool.PeopleChartConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface PeopleChartMapper extends BaseMapper<PeopleChart,Integer,PeopleChartConditions> {
    int countByExample(PeopleChartConditions example);

    int deleteByExample(PeopleChartConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(PeopleChart record);

    int insertSelective(PeopleChart record);

    List<PeopleChart> selectByExample(PeopleChartConditions example);

    PeopleChart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PeopleChart record, @Param("example") PeopleChartConditions example);

    int updateByExample(@Param("record") PeopleChart record, @Param("example") PeopleChartConditions example);

    int updateByPrimaryKeySelective(PeopleChart record);

    int updateByPrimaryKey(PeopleChart record);
}