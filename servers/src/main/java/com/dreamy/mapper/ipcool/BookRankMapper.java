package com.dreamy.mapper.ipcool;

import com.dreamy.domain.ipcool.BookRank;
import com.dreamy.domain.ipcool.BookRankConditions;
import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookRankMapper extends BaseMapper<BookRank, Integer, BookRankConditions> {
    int countByExample(BookRankConditions example);

    int deleteByExample(BookRankConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookRank record);

    int insertSelective(BookRank record);

    List<BookRank> selectByExample(BookRankConditions example);

    BookRank selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookRank record, @Param("example") BookRankConditions example);

    int updateByExample(@Param("record") BookRank record, @Param("example") BookRankConditions example);

    int updateByPrimaryKeySelective(BookRank record);

    int updateByPrimaryKey(BookRank record);
}