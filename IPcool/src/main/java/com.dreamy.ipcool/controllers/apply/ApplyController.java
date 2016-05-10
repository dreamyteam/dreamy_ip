package com.dreamy.ipcool.controllers.apply;

import com.dreamy.beans.UserSession;
import com.dreamy.beans.params.IpApplyParams;
import com.dreamy.domain.user.User;
import com.dreamy.enums.BookTypeEnums;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.StringUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.omg.CORBA.MARSHAL;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/26
 * Time: 下午7:22
 */
@Controller
@RequestMapping("/apply")
public class ApplyController extends IpcoolController {

    @RequestMapping("")
    public String apply(ModelMap map, HttpServletRequest request, IpApplyParams applyParams, User user) {

        List<Integer> wrongTypes = JsonUtils.toList(Integer.class, request.getParameter("wrongTypes"));

        map.put("wrongTypes", wrongTypes);
        map.put("booTypeEnums", BookTypeEnums.values());

        map.put("user", user);
        map.put("apply", applyParams);

        return "/apply/apply";
    }

    @RequestMapping("/save")
    public String applyResult(IpApplyParams applyParams, User user, ModelMap map, HttpServletRequest request) {
        String redirectUrl = "/apply";
        List<Integer> wrongTypes = new LinkedList<Integer>();

        if (StringUtils.isEmpty(applyParams.getIpName())) {
            wrongTypes.add(1);
        }

        if (applyParams.getIpType() == 0) {
            wrongTypes.add(2);
        }

        UserSession userSession = getUserSession(request);
        if (userSession == null) {
            if (StringUtils.isEmpty(user.getUserName())) {
                wrongTypes.add(3);
            }
            if (StringUtils.isEmpty(user.getEmail())) {
                wrongTypes.add(4);
            }
        }
        if (wrongTypes.size() > 0) {
            return redirect(redirectUrl + "?wrongTypes=" + JsonUtils.toString(wrongTypes)
                            + "&ipName=" + applyParams.getIpName()
                            + "&ipType=" + applyParams.getIpType()
                            + "&refUrl=" + applyParams.getRefUrl()
                            + "&userName=" + user.getUserName()
                            + "&email=" + user.getEmail()
            );
        }

        map.put("ipName", applyParams.getIpName());
        return "/apply/result";
    }


}
