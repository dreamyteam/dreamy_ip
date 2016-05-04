package com.dreamy.mapper.sys;

import com.dreamy.domain.sys.SysOption;
import com.dreamy.domain.sys.SysOptionConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SysOptionMapper extends BaseMapper<SysOption,Integer,SysOptionConditions> {
    int countByExample(SysOptionConditions example);

    int deleteByExample(SysOptionConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysOption record);

    int insertSelective(SysOption record);

    List<SysOption> selectByExample(SysOptionConditions example);

    SysOption selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysOption record, @Param("example") SysOptionConditions example);

    int updateByExample(@Param("record") SysOption record, @Param("example") SysOptionConditions example);

    int updateByPrimaryKeySelective(SysOption record);

    int updateByPrimaryKey(SysOption record);
}