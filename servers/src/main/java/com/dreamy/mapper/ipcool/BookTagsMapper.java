package com.dreamy.mapper.ipcool;

import com.dreamy.domain.ipcool.BookTags;
import com.dreamy.domain.ipcool.BookTagsConditions;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookTagsMapper {
    int countByExample(BookTagsConditions example);

    int deleteByExample(BookTagsConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookTags record);

    int insertSelective(BookTags record);

    List<BookTags> selectByExample(BookTagsConditions example);

    BookTags selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookTags record, @Param("example") BookTagsConditions example);

    int updateByExample(@Param("record") BookTags record, @Param("example") BookTagsConditions example);

    int updateByPrimaryKeySelective(BookTags record);

    int updateByPrimaryKey(BookTags record);
}