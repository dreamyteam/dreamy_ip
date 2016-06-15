package com.dreamy.admin.IndexCalculation.book;

import com.dreamy.domain.ipcool.BookView;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/6/15
 * Time: 下午1:38
 */
public interface BookSourceHandler {

    /**
     *
     * @return
     */
    Integer getHandlerId();

    /**
     * 获取火热
     *
     * @param bookView
     * @return
     */
    Integer getHotIndex(BookView bookView);


    /**
     * 获取传播
     *
     * @param bookView
     * @return
     */
    Integer getPropagationIndex(BookView bookView);

    /**
     * 获取活跃
     *
     * @param bookView
     * @return
     */
    Integer getActiveIndex(BookView bookView);


    /**
     * 获取口碑
     *
     * @param bookView
     * @return
     */
    Integer getReputationIndex(BookView bookView);

    /**
     * 获取开发潜力
     *
     * @param bookView
     * @return
     */
    Integer getDevelopIndex(BookView bookView);

}
