package com.dreamy.dao.impl.user;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.user.UserIntentionVoteDao;
import com.dreamy.domain.user.IntentionVote;
import com.dreamy.domain.user.IntentionVoteConditions;
import com.dreamy.mapper.user.IntentionVoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/11
 * Time: 下午3:30
 */
@Repository
public class UserIntentionVoteDaoImpl extends BaseDaoImpl<IntentionVote, Integer, IntentionVoteConditions> implements UserIntentionVoteDao {

    @Autowired
    private IntentionVoteMapper mapper;

    @Autowired

    @Override
    public void setBaseMapper() {
        super.setBaseMapper(mapper);
    }
}
