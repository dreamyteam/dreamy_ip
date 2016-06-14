package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.service.iface.CommonService;
import com.dreamy.service.iface.ipcool.SearchService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/6/8
 * Time: 下午6:54
 */
@Service
public class SearchServiceImpl implements SearchService {

    private final static Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Autowired
    private CommonService commonService;

    @Override
    public List<Integer> getBookIdsFromSolrByNameAndType(String name, Page page, List<Integer> types) {
        List<Integer> bookViewIds = new LinkedList<>();

        String url = commonService.getSearchDomain() + "/ipbook/select/";
        Map<String, String> params = new HashMap<>();

        if (CollectionUtils.isNotEmpty(types)) {
            String fqString = "(";
            for (Integer type : types)
                fqString += "type:" + type + " OR ";

            fqString += fqString.substring(0, -3) + ")";
            params.put("fq", fqString);
        }

        params.put("q", "name:" + name + "~");
        params.put("wt", "json");
        params.put("sort", "compositeIndex desc");
        params.put("indent", "true");
        params.put("start", (page.getCurrentPage() - 1) * page.getPageSize() + "");
        params.put("rows", page.getPageSize() + "");


        try {
            String res = HttpUtils.post(url, params);
            if (StringUtils.isEmpty(res)) {
                return bookViewIds;
            }

            LinkedHashMap resMap = (LinkedHashMap) JsonUtils.toMap(res);
            if (CollectionUtils.isEmpty(resMap)) {
                return bookViewIds;
            }

            LinkedHashMap responseHeader = (LinkedHashMap) resMap.get("responseHeader");
            Integer status = (Integer) responseHeader.get("status");
            if (status == 0) {
                LinkedHashMap response = (LinkedHashMap) resMap.get("response");
                Integer numFound = (Integer) response.get("numFound");
                if (numFound > 0) {
                    ArrayList<LinkedHashMap> docs = (ArrayList<LinkedHashMap>) response.get("docs");
                    for (LinkedHashMap doc : docs) {
                        bookViewIds.add((Integer) doc.get("bookid"));
                    }

                    page.setTotalNum(numFound);
                }


            }
        } catch (Exception e) {
            LOGGER.error("search from solr failed search word is : " + name, e);
        }

        return bookViewIds;
    }

}
