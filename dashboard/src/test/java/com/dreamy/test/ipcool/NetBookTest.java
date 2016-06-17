package com.dreamy.test.ipcool;

import com.dreamy.admin.tasks.QiDianTask;
import com.dreamy.admin.tasks.rank.UpdateChubanBookIndexTask;
import com.dreamy.admin.tasks.rank.UpdateNetBookIndexTask;
import com.dreamy.test.BaseJunitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangyongxing on 16/6/16.
 */
public class NetBookTest extends BaseJunitTest {

    @Autowired
    UpdateNetBookIndexTask updateNetBookIndexTask;
    @Autowired
    UpdateChubanBookIndexTask updateChubanBookIndexTask;
    @Autowired
    QiDianTask qiDianTask;

    @Test
    public void test() {
        qiDianTask.fans();
        //    updateChubanBookIndexTask.run();
    }
}
