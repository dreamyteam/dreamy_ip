package com.dreamy.mapper.admin;

import com.dreamy.domain.admin.SysModel;
import com.dreamy.domain.admin.SysModelConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SysModelMapper extends BaseMapper<SysModel,Integer,SysModelConditions> {
    int countByExample(SysModelConditions example);

    int deleteByExample(SysModelConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysModel record);

    int insertSelective(SysModel record);

    List<SysModel> selectByExample(SysModelConditions example);

    SysModel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysModel record, @Param("example") SysModelConditions example);

    int updateByExample(@Param("record") SysModel record, @Param("example") SysModelConditions example);

    int updateByPrimaryKeySelective(SysModel record);

    int updateByPrimaryKey(SysModel record);

    List<SysModel> selectByRoles(@Param("roles") List<Integer> roles);


}