package com.dreamy.mapper.ipcool;

import com.dreamy.domain.ipcool.BookScore;
import com.dreamy.domain.ipcool.BookScoreConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BookScoreMapper extends BaseMapper<BookScore,Integer,BookScoreConditions>{
    int countByExample(BookScoreConditions example);

    int deleteByExample(BookScoreConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookScore record);

    int insertSelective(BookScore record);

    List<BookScore> selectByExample(BookScoreConditions example);

    BookScore selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookScore record, @Param("example") BookScoreConditions example);

    int updateByExample(@Param("record") BookScore record, @Param("example") BookScoreConditions example);

    int updateByPrimaryKeySelective(BookScore record);

    int updateByPrimaryKey(BookScore record);
}