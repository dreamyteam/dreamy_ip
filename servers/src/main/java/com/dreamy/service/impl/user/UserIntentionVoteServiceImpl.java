package com.dreamy.service.impl.user;

import com.dreamy.beans.params.IntentionVoteParams;
import com.dreamy.dao.iface.user.UserIntentionVoteDao;
import com.dreamy.domain.user.IntentionVote;
import com.dreamy.domain.user.IntentionVoteConditions;
import com.dreamy.domain.user.User;
import com.dreamy.service.iface.user.UserIntentionVoteService;
import com.dreamy.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/11
 * Time: 下午3:43
 */
@Service
public class UserIntentionVoteServiceImpl implements UserIntentionVoteService {

    @Autowired
    private UserIntentionVoteDao userIntentionVoteDao;

    @Override
    public Integer save(IntentionVoteParams params, User user) {
        IntentionVote intentionVote = new IntentionVote();

        if (user != null) {
            intentionVote.setUserId(user.getId());
        }

        intentionVote.setIpId(params.getIpId());
        intentionVote.setType(params.getType());
        intentionVote.setUserChoice(params.getChoice());
        intentionVote.setIpAddress(params.getIp());

        return userIntentionVoteDao.save(intentionVote);
    }

    @Override
    public List<IntentionVote> getVotesByUserIdAndIpId(Integer userId, Integer ipId) {
        IntentionVote intentionVote = new IntentionVote();
        intentionVote.userId(userId).ipId(ipId);

        return getByRecord(intentionVote);
    }

    @Override
    public List<IntentionVote> getVotesByIpAndIpId(String ip, Integer ipId) {
        IntentionVote intentionVote = new IntentionVote();
        intentionVote.ipAddress(ip).ipId(ipId);
        return getByRecord(intentionVote);
    }

    @Override
    public List<IntentionVote> getByRecord(IntentionVote intentionVote) {
        Map<String, Object> params = BeanUtils.toQueryMap(intentionVote);
        IntentionVoteConditions conditions = new IntentionVoteConditions();
        conditions.createCriteria().addByMap(params);

        return userIntentionVoteDao.selectByExample(conditions);
    }
}
