package com.dreamy.mapper.ipcool;

import com.dreamy.domain.ipcool.BookIndexHistory;
import com.dreamy.domain.ipcool.BookIndexHistoryConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BookIndexHistoryMapper extends BaseMapper<BookIndexHistory,Integer,BookIndexHistoryConditions> {
    int countByExample(BookIndexHistoryConditions example);

    int deleteByExample(BookIndexHistoryConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookIndexHistory record);

    int insertSelective(BookIndexHistory record);

    List<BookIndexHistory> selectByExample(BookIndexHistoryConditions example);

    BookIndexHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookIndexHistory record, @Param("example") BookIndexHistoryConditions example);

    int updateByExample(@Param("record") BookIndexHistory record, @Param("example") BookIndexHistoryConditions example);

    int updateByPrimaryKeySelective(BookIndexHistory record);

    int updateByPrimaryKey(BookIndexHistory record);
}