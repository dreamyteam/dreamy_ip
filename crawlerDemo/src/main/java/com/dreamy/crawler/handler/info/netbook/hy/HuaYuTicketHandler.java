package com.dreamy.crawler.handler.info.netbook.hy;

import com.dreamy.mogodb.beans.NetBookTicket;
import com.dreamy.service.iface.mongo.NetBookTicketService;
import com.dreamy.utils.PatternUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/2.
 */
@Component
public class HuaYuTicketHandler {

    private static final Logger log = LoggerFactory.getLogger(HuaYuTicketHandler.class);
    @Autowired
    public NetBookTicketService netBookTicketService;


    public void crawler(int page) {

        String url = "http://huayu.baidu.com/store/c0/c0/u5/p" + page + "/v1/s0/ALL.html";
        try {
            OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), HuaYu.class);
            HuaYu huaYu = ooSpider.<HuaYu>get(url);
            NetBookTicket netBookTicket = null;
            int size = huaYu.getUrls().size();
            List<String> urls = huaYu.getUrls();
            List<String> nums = huaYu.getNum();
            for (int i = 0; i < size; i++) {
                netBookTicket = new NetBookTicket();
                String code = PatternUtils.getNum(urls.get(i));
                netBookTicket.setTicketNum(Integer.valueOf(nums.get(i).replace(" ", "")));
                netBookTicket.setCode(Integer.valueOf(code));
                netBookTicketService.saveByRecord(netBookTicket);

            }
        } catch (Exception e) {
            log.error("HuaYuTicketHandler is error url:" + url, e);
        }


    }


    public void crawler22(int start, int end) {

        for (int j = start; j <= end; j++) {
            String url = "http://huayu.baidu.com/store/c0/c0/u5/p" + j + "/v1/s0/ALL.html";
            try {
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
                    netBookTicket.setTicketNum(Integer.valueOf(nums.get(i).replace(" ", "")));
                    netBookTicketService.saveByRecord(netBookTicket);

                }
            } catch (Exception e) {
                log.error("HuaYuTicketHandler is error url:" + url, e);
                continue;
            }


        }
    }

}
