package com.dreamy.mapper.user;

import com.dreamy.domain.user.UserAttach;
import com.dreamy.domain.user.UserAttachConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface UserAttachMapper extends BaseMapper<UserAttach,Integer,UserAttachConditions> {
    int countByExample(UserAttachConditions example);

    int deleteByExample(UserAttachConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserAttach record);

    int insertSelective(UserAttach record);

    List<UserAttach> selectByExample(UserAttachConditions example);

    UserAttach selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserAttach record, @Param("example") UserAttachConditions example);

    int updateByExample(@Param("record") UserAttach record, @Param("example") UserAttachConditions example);

    int updateByPrimaryKeySelective(UserAttach record);

    int updateByPrimaryKey(UserAttach record);
}