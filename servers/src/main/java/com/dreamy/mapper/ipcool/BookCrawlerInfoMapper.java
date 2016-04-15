package com.dreamy.mapper.ipcool;

import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.BookCrawlerInfoConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BookCrawlerInfoMapper extends BaseMapper<BookCrawlerInfo,Integer,BookCrawlerInfoConditions>{
    int countByExample(BookCrawlerInfoConditions example);

    int deleteByExample(BookCrawlerInfoConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookCrawlerInfo record);

    int insertSelective(BookCrawlerInfo record);

    List<BookCrawlerInfo> selectByExample(BookCrawlerInfoConditions example);

    BookCrawlerInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookCrawlerInfo record, @Param("example") BookCrawlerInfoConditions example);

    int updateByExample(@Param("record") BookCrawlerInfo record, @Param("example") BookCrawlerInfoConditions example);

    int updateByPrimaryKeySelective(BookCrawlerInfo record);

    int updateByPrimaryKey(BookCrawlerInfo record);
}