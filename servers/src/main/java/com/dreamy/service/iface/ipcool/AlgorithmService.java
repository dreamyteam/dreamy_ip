package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.Algorithm;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/19.
 */
public interface AlgorithmService {

    List<Algorithm> getList(Algorithm algorithm, Page page);

    public  void save(Algorithm algorithm);

    Algorithm getById(Integer id);


}
