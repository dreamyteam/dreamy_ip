package com.dreamy.mapper.ipcool;

import com.dreamy.domain.ipcool.bookTags;
import com.dreamy.domain.ipcool.bookTagsConditions;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface bookTagsMapper {
    int countByExample(bookTagsConditions example);

    int deleteByExample(bookTagsConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(bookTags record);

    int insertSelective(bookTags record);

    List<bookTags> selectByExample(bookTagsConditions example);

    bookTags selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") bookTags record, @Param("example") bookTagsConditions example);

    int updateByExample(@Param("record") bookTags record, @Param("example") bookTagsConditions example);

    int updateByPrimaryKeySelective(bookTags record);

    int updateByPrimaryKey(bookTags record);
}