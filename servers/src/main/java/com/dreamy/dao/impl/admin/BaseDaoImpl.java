package com.dreamy.dao.impl.admin;

import com.dreamy.dao.BaseDao;
import com.dreamy.mapper.BaseMapper;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangyongxing on 16/4/6.
 */
public abstract class BaseDaoImpl<BaseDomain,ID extends Serializable,T> implements BaseDao<BaseDomain,ID,T> {

    private BaseMapper<BaseDomain, ID,T> baseMapper;

    public void setBaseMapper(BaseMapper<BaseDomain, ID,T> baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public int save(BaseDomain record) {
        return baseMapper.insertSelective(record);
    }

    @Override
    public BaseDomain selectById(ID id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteById(ID id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int countByExample(T example) {
        return baseMapper.countByExample(example);
    }

    @Override
    public int update(BaseDomain record) {
        return baseMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<BaseDomain> selectByExample(T example) {
        return baseMapper.selectByExample(example);
    }

    @Override
    public int updateByExampleSelective(BaseDomain record, T t) {
        return baseMapper.updateByExampleSelective(record,t);
    }
}
