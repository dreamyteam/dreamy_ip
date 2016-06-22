package com.dreamy.mapper.ipcool;

import com.dreamy.domain.ipcool.BookViewCalculateResult;
import com.dreamy.domain.ipcool.BookViewCalculateResultConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BookViewCalculateResultMapper extends BaseMapper<BookViewCalculateResult,Integer,BookViewCalculateResultConditions>{
    int countByExample(BookViewCalculateResultConditions example);

    int deleteByExample(BookViewCalculateResultConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookViewCalculateResult record);

    int insertSelective(BookViewCalculateResult record);

    List<BookViewCalculateResult> selectByExample(BookViewCalculateResultConditions example);

    BookViewCalculateResult selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookViewCalculateResult record, @Param("example") BookViewCalculateResultConditions example);

    int updateByExample(@Param("record") BookViewCalculateResult record, @Param("example") BookViewCalculateResultConditions example);

    int updateByPrimaryKeySelective(BookViewCalculateResult record);

    int updateByPrimaryKey(BookViewCalculateResult record);
}