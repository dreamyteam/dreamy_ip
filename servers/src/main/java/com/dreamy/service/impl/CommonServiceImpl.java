package com.dreamy.service.impl;

import com.dreamy.service.iface.CommonService;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author jared
 * @Description:
 * @date Dec 11, 2014 10:01:45 AM
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Value("${service}")
    private String service;

    @Value("${domain}")
    private String domain;

    @Value("${assetsDomain}")
    private String assetsDomain;


    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public boolean isDev() {
        if (StringUtils.isNotEmpty(service) && !service.equals("dev")) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }


    @Override
    public String getAssetsDomain() {
        return assetsDomain;
    }
}
