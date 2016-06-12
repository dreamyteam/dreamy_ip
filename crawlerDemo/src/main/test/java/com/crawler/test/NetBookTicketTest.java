package com.crawler.test;

import com.dreamy.crawler.handler.info.netbook.hy.HuaYu;
import com.dreamy.crawler.handler.info.netbook.qd.QiDian;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.mogodb.beans.NetBookTicket;
import com.dreamy.service.iface.mongo.NetBookTicketService;
import com.dreamy.utils.PatternUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/7.
 */
public class NetBookTicketTest extends BaseJunitTest {

    @Autowired
    NetBookTicketService netBookTicketService;

    @Test
    public void save() {
        for (int j = 1; j < 156; j++) {
            String url = "http://huayu.baidu.com/store/c0/c0/u5/p" + j + "/v1/s0/ALL.html";
            OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), HuaYu.class);
            HuaYu huaYu = ooSpider.<HuaYu>get(url);

            NetBookTicket netBookTicket = null;
            int size = huaYu.getUrls().size();
            List<String> urls = huaYu.getUrls();
            List<String> nums = huaYu.getNum();

            for (int i = 0; i < size; i++) {
                netBookTicket = new NetBookTicket();
                String code = PatternUtils.getNum(urls.get(i));
                netBookTicket.setCode(Integer.valueOf(code));
                netBookTicket.setTicketNum(Integer.valueOf(nums.get(i).replace(" ","")));
                netBookTicketService.saveByRecord(netBookTicket);

            }

        }
    }
}
