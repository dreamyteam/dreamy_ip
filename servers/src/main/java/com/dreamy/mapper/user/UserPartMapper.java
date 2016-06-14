package com.dreamy.mapper.user;

import com.dreamy.domain.user.UserPart;
import com.dreamy.domain.user.UserPartConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface UserPartMapper extends BaseMapper<UserPart, Integer, UserPartConditions> {
    int countByExample(UserPartConditions example);

    int deleteByExample(UserPartConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserPart record);

    int insertSelective(UserPart record);

    List<UserPart> selectByExample(UserPartConditions example);

    UserPart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserPart record, @Param("example") UserPartConditions example);

    int updateByExample(@Param("record") UserPart record, @Param("example") UserPartConditions example);

    int updateByPrimaryKeySelective(UserPart record);

    int updateByPrimaryKey(UserPart record);
}