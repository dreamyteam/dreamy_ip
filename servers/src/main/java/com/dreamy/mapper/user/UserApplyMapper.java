package com.dreamy.mapper.user;

import com.dreamy.domain.user.UserApply;
import com.dreamy.domain.user.UserApplyConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface UserApplyMapper extends BaseMapper<UserApply,Integer,UserApplyConditions>{
    int countByExample(UserApplyConditions example);

    int deleteByExample(UserApplyConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserApply record);

    int insertSelective(UserApply record);

    List<UserApply> selectByExample(UserApplyConditions example);

    UserApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserApply record, @Param("example") UserApplyConditions example);

    int updateByExample(@Param("record") UserApply record, @Param("example") UserApplyConditions example);

    int updateByPrimaryKeySelective(UserApply record);

    int updateByPrimaryKey(UserApply record);
}