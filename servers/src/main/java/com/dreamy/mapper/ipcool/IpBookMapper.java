package com.dreamy.mapper.ipcool;

import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.domain.ipcool.IpBookConditions;
import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IpBookMapper  extends BaseMapper<IpBook,Integer,IpBookConditions>{
    int countByExample(IpBookConditions example);

    int deleteByExample(IpBookConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(IpBook record);

    int insertSelective(IpBook record);

    List<IpBook> selectByExample(IpBookConditions example);

    IpBook selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IpBook record, @Param("example") IpBookConditions example);

    int updateByExample(@Param("record") IpBook record, @Param("example") IpBookConditions example);

    int updateByPrimaryKeySelective(IpBook record);

    int updateByPrimaryKey(IpBook record);
}