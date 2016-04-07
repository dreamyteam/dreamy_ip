package com.dreamy.mapper.admin;

import com.dreamy.domain.admin.Role;
import com.dreamy.domain.admin.RoleConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper extends BaseMapper<Role,Integer,RoleConditions> {
    int countByExample(RoleConditions example);

    int deleteByExample(RoleConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleConditions example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleConditions example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleConditions example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}