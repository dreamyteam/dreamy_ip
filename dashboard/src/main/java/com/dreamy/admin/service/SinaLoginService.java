package com.dreamy.admin.service;

import com.dreamy.domain.sys.SysOption;
import com.dreamy.enums.RedisConstEnums;
import com.dreamy.service.cache.CommonService;
import com.dreamy.service.iface.sys.SysOptionService;
import com.dreamy.utils.ConstStrings;
import com.dreamy.utils.StringUtils;
import com.dreamy.utils.sina.CrawSina;
import com.dreamy.utils.sina.LoginSina;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by wangyongxing on 16/5/11.
 */
@Service
public class SinaLoginService {

    @Resource
    CommonService commonService;

    @Resource
    private SysOptionService sysOptionService;


    public void init() {

        SysOption option = sysOptionService.getByCode("001");
        if (option != null) {
            if (StringUtils.isNotEmpty(option.getCodeValue())) {
                String arr[] = option.getCodeValue().split(",");
                int length = arr.length;
                int j = 1;
                for (int i = 0; i < length; i++) {
                    String values[] = arr[i].split("\\|");
                    LoginSina ls = new LoginSina(values[0], values[1]);
                    ls.dologinSina();
                    if (StringUtils.isNotEmpty(CrawSina.Cookie)) {
                        commonService.getCacheService().set(RedisConstEnums.weiboCookieName.getCacheKey() + j, CrawSina.Cookie, 3600);
                        j++;
                    }
                }
                commonService.getCacheService().put(RedisConstEnums.weibo.getCacheKey(), j);

            }

        }
    }


}
