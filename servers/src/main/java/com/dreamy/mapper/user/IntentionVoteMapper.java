package com.dreamy.mapper.user;

import com.dreamy.domain.user.IntentionVote;
import com.dreamy.domain.user.IntentionVoteConditions;

import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface IntentionVoteMapper extends BaseMapper<IntentionVote, Integer, IntentionVoteConditions> {
    int countByExample(IntentionVoteConditions example);

    int deleteByExample(IntentionVoteConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(IntentionVote record);

    int insertSelective(IntentionVote record);

    List<IntentionVote> selectByExample(IntentionVoteConditions example);

    IntentionVote selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IntentionVote record, @Param("example") IntentionVoteConditions example);

    int updateByExample(@Param("record") IntentionVote record, @Param("example") IntentionVoteConditions example);

    int updateByPrimaryKeySelective(IntentionVote record);

    int updateByPrimaryKey(IntentionVote record);
}