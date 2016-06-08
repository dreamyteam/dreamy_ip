package com.dreamy.crawler.handler.info.netbook.qd;

import com.dreamy.mogodb.beans.qidian.FanInfo;
import com.dreamy.mogodb.beans.qidian.QiDianFan;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.PatternUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/6/8.
 */
@Component
public class FansHanlder {

    public static void main(String[] args) {


       // crawler(1, "http://t.qidian.com/Friend/HisFollower.php?id=196764106");


    }

    public QiDianFan  crawler(Integer bookId, String url) {
        String userId = PatternUtils.getNum(url);
        String ajaxUrl = "http://t.qidian.com/Ajax/Index.php?ajaxMethod=getUserSummary&userId=" + userId + "&twitterCount=-1&followerCount=-1&followingCount=-1&random=0.6084272195739169";
        String json = HttpUtils.getHtmlGet(ajaxUrl);
        Map<String, Object> map = JsonUtils.toMap(json);
        int code = (Integer) map.get("success");
        QiDianFan qianDainFans=null;
        if (code == 1) {
            qianDainFans= new QiDianFan();
            qianDainFans.setBookId(bookId);
            qianDainFans.setFollowerCount(Integer.valueOf(map.get("followerCount") + ""));
            qianDainFans.setFollowingCount(Integer.valueOf(map.get("followingCount") + ""));
            qianDainFans.setTwitterCount(Integer.valueOf(map.get("twitterCount") + ""));
            fans(qianDainFans, url);
        }
        return  qianDainFans;

    }

    private  void fans(QiDianFan qianDainFans, String url) {
        List<FanInfo> list = new ArrayList<FanInfo>();
        int num = 10;
        for (int j = 1; j <= 10; j++) {
            url = url + "&page=" + j;
            OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), Info.class);
            Info info = ooSpider.<Info>get(url);
            if (info != null) {
                List<String> strs = info.getInfos();
                List<String> names = info.getNames();
                int size = strs.size();
                if (size > 0) {
                    FanInfo fanInfo = null;
                    for (int i = 0; i < size; i++) {
                        fanInfo = new FanInfo();
                        String str = strs.get(i);
                        int length = str.length();
                        fanInfo.setSex(str.substring(0, 1));
                        fanInfo.setName(names.get(i));
                        if (length > 1) {
                            fanInfo.setAddress(str.substring(1, length));
                        }
                        list.add(fanInfo);
                    }

                } else {
                    break;
                }
            }
        }

        qianDainFans.setList(list);

    }
}
