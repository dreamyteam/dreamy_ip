package com.dreamy.mapper.user;

import com.dreamy.domain.user.UserAuth;
import com.dreamy.domain.user.UserAuthConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface UserAuthMapper extends BaseMapper<UserAuth, Integer, UserAuthConditions> {
    int countByExample(UserAuthConditions example);

    int deleteByExample(UserAuthConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserAuth record);

    int insertSelective(UserAuth record);

    List<UserAuth> selectByExample(UserAuthConditions example);

    UserAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserAuth record, @Param("example") UserAuthConditions example);

    int updateByExample(@Param("record") UserAuth record, @Param("example") UserAuthConditions example);

    int updateByPrimaryKeySelective(UserAuth record);

    int updateByPrimaryKey(UserAuth record);
}