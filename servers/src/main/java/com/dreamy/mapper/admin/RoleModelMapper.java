package com.dreamy.mapper.admin;

import com.dreamy.domain.admin.RoleModel;
import com.dreamy.domain.admin.RoleModelConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface RoleModelMapper extends  BaseMapper<RoleModel,Integer,RoleModelConditions> {
    int countByExample(RoleModelConditions example);

    int deleteByExample(RoleModelConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleModel record);

    int insertSelective(RoleModel record);

    List<RoleModel> selectByExample(RoleModelConditions example);

    RoleModel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleModel record, @Param("example") RoleModelConditions example);

    int updateByExample(@Param("record") RoleModel record, @Param("example") RoleModelConditions example);

    int updateByPrimaryKeySelective(RoleModel record);

    int updateByPrimaryKey(RoleModel record);
}