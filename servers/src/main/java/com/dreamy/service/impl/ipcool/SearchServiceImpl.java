package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.service.iface.CommonService;
import com.dreamy.service.iface.ipcool.SearchService;
import com.dreamy.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/6/8
 * Time: 下午6:54
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private CommonService commonService;

    @Override
    public String getBookViewByName(String name, Page page) {
        String url = commonService.getSearchDomain() + "/ipbook/select/";
        Map<String, String> params = new HashMap<>();

        params.put("q", "name:" + name + "~");
        params.put("wt", "json");
        params.put("sort", "compositeIndex desc");
        params.put("indent", "true");
        params.put("start", (page.getCurrentPage() - 1) * page.getPageSize() + "");
        params.put("rows", page.getPageSize() + "");

        String res = HttpUtils.post(url, params);

        return res;
    }


}
