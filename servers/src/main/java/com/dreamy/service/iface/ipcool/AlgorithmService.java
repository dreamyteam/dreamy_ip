package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.Algorithm;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/19.
 */
public interface AlgorithmService {

    /**
     *
     * @param algorithm
     * @param page
     * @return
     */
    List<Algorithm> getList(Algorithm algorithm, Page page);

    /**
     *
     * @param algorithm
     */
    public  void save(Algorithm algorithm);


    /**
     *
     * @param id
     * @return
     */
    Algorithm getById(Integer id);


}
