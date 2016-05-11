package com.dreamy.service.iface.user;

import com.dreamy.beans.params.IntentionVoteParams;
import com.dreamy.domain.user.IntentionVote;
import com.dreamy.domain.user.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/11
 * Time: 下午3:43
 */
public interface UserIntentionVoteService {
    /**
     * @param params
     * @param user
     * @return
     */
    Integer save(IntentionVoteParams params, User user);

    /**
     * @param userId
     * @param ipId
     * @return
     */
    List<IntentionVote> getVotesByUserIdAndIpId(Integer userId, Integer ipId);

    /**
     * @param userId
     * @param ipId
     * @return
     */
    List<IntentionVote> getVotesByIpAndIpId(String ip, Integer ipId);

    /**
     * @param intentionVote
     * @return
     */
    List<IntentionVote> getByRecord(IntentionVote intentionVote);
}
