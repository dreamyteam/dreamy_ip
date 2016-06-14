package com.crawler.test;

import com.dreamy.crawler.handler.tieba.TieBaHandler;
import com.dreamy.mogodb.beans.tieba.TieBa;
import com.dreamy.service.iface.mongo.TieBaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangyongxing on 16/6/12.
 */
public class TieBaTest extends BaseJunitTest {
    @Autowired
    TieBaHandler tieBaHandler;
    @Autowired
    TieBaService tieBaService;

    @Test
    public void save() {
        TieBa tieBa = tieBaHandler.crawler("大主宰", 9216);
        if (tieBa != null) {
            tieBa.setName("大主宰");
            tieBaService.updateInser(tieBa);

        }
    }
}
