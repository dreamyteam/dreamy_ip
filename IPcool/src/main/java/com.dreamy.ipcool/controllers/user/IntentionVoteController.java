package com.dreamy.ipcool.controllers.user;

import com.dreamy.beans.InterfaceBean;
import com.dreamy.beans.UserSession;
import com.dreamy.beans.params.IntentionVoteParams;
import com.dreamy.domain.user.IntentionVote;
import com.dreamy.domain.user.User;
import com.dreamy.enums.BookIpDevelopTypeEnums;
import com.dreamy.enums.ErrorCodeEnums;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.service.iface.user.UserIntentionVoteService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/11
 * Time: 下午4:01
 */
@Controller
public class IntentionVoteController extends IpcoolController {

    @Autowired
    private UserIntentionVoteService userIntentionVoteService;

    @RequestMapping(value = "/user/intention/vote")
    @ResponseBody
    public void vote(HttpServletResponse response, HttpServletRequest request, IntentionVoteParams params) {
        InterfaceBean bean = new InterfaceBean().success();
        User user = new User();

        Integer ipId = params.getIpId();
        if (ipId != null && ipId > 0) {

            String ipAddress = HttpUtils.getIpAddr(request);
            params.setIp(ipAddress);
            UserSession userSession = getUserSession(request);
            if (userSession != null && userSession.getUserId() != 0) {
                user = new User().id(userSession.getUserId());
            }

            IntentionVote intentionVote = new IntentionVote();
            intentionVote.ipId(ipId);
            intentionVote.ipAddress(ipAddress);
            intentionVote.type(params.getType());
            intentionVote.userChoice(params.getChoice());
            if (userIntentionVoteService.getByRecord(intentionVote).size() > 0) {
                bean.failure(ErrorCodeEnums.vote_failed.getErrorCode(), "您已经投过啦");
            } else {
                userIntentionVoteService.save(params, user);
            }
        } else {
            bean.failure(ErrorCodeEnums.vote_failed);
        }

        interfaceReturn(response, JsonUtils.toString(bean), "");
    }

    @RequestMapping("/ip/vote/detail")
    @ResponseBody
    public void getIntentionByIp(HttpServletResponse response, HttpServletRequest request, Integer ipId) {
        InterfaceBean bean = new InterfaceBean().success();
        UserSession userSession = getUserSession(request);

        List<IntentionVote> list = new LinkedList<IntentionVote>();
        if (userSession == null || userSession.getUserId() == 0) {
            list = userIntentionVoteService.getVotesByIpAndIpId(HttpUtils.getIpAddr(request), ipId);
        } else {
            list = userIntentionVoteService.getVotesByUserIdAndIpId(userSession.getUserId(), ipId);
        }

        Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
        if (CollectionUtils.isNotEmpty(list)) {
            List<Integer> typeOne = new LinkedList<Integer>();
            List<Integer> typeTwo = new LinkedList<Integer>();
            for (IntentionVote each : list) {
                if (each.getUserChoice() == 1) {
                    typeOne.add(each.getType());
                } else {
                    typeTwo.add(each.getType());
                }
            }

            map.put("1", typeOne);
            map.put("2", typeTwo);
            bean.setData(map);
        }

        interfaceReturn(response, JsonUtils.toString(bean), "");
    }


}
