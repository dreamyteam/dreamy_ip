package com.dreamy.test.index;

import com.dreamy.admin.tasks.BookIndexHistoryTask;
import com.dreamy.admin.tasks.index.*;
import com.dreamy.test.BaseJunitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangyongxing on 16/5/10.
 */
public class IndexTest extends BaseJunitTest {

    @Autowired
    CompositeIndexexCreateTask compositeIndexexCreateTask;
    @Autowired
    DevelopIndexesCreateTask developIndexesCreateTask;
    @Autowired
    HotIndexesCreateTask hotIndexesCreateTask;
    @Autowired
    PropagationIndexesCreareTask propagationIndexesCreareTask;

    @Autowired
    ReputationIndexesCreateTask reputationIndexesCreateTask;

    @Autowired
    BookIndexHistoryTask bookIndexHistoryTask;

    @Test
    public  void task(){

        hotIndexesCreateTask.run();
    }

    @Test
    public  void history(){
        bookIndexHistoryTask.copy();
    }

}
