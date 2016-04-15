package com.dreamy.mapper.admin;

import com.dreamy.domain.admin.UserRole;
import com.dreamy.domain.admin.UserRoleConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper extends BaseMapper<UserRole,Integer,UserRoleConditions> {
    int countByExample(UserRoleConditions example);

    int deleteByExample(UserRoleConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleConditions example);

    UserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleConditions example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleConditions example);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}