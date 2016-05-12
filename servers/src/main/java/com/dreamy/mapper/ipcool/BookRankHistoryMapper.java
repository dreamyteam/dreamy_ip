package com.dreamy.mapper.ipcool;

import com.dreamy.domain.ipcool.BookRankHistory;
import com.dreamy.domain.ipcool.BookRankHistoryConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BookRankHistoryMapper  extends BaseMapper<BookRankHistory,Integer,BookRankHistoryConditions>{
    int countByExample(BookRankHistoryConditions example);

    int deleteByExample(BookRankHistoryConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookRankHistory record);

    int insertSelective(BookRankHistory record);

    List<BookRankHistory> selectByExample(BookRankHistoryConditions example);

    BookRankHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookRankHistory record, @Param("example") BookRankHistoryConditions example);

    int updateByExample(@Param("record") BookRankHistory record, @Param("example") BookRankHistoryConditions example);

    int updateByPrimaryKeySelective(BookRankHistory record);

    int updateByPrimaryKey(BookRankHistory record);
}