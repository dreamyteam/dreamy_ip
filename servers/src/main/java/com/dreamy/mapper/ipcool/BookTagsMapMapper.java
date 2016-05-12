package com.dreamy.mapper.ipcool;

import com.dreamy.domain.ipcool.BookTagsMap;
import com.dreamy.domain.ipcool.BookTagsMapConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BookTagsMapMapper extends BaseMapper<BookTagsMap,Integer,BookTagsMapConditions> {
    int countByExample(BookTagsMapConditions example);

    int deleteByExample(BookTagsMapConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookTagsMap record);

    int insertSelective(BookTagsMap record);

    List<BookTagsMap> selectByExample(BookTagsMapConditions example);

    BookTagsMap selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookTagsMap record, @Param("example") BookTagsMapConditions example);

    int updateByExample(@Param("record") BookTagsMap record, @Param("example") BookTagsMapConditions example);

    int updateByPrimaryKeySelective(BookTagsMap record);

    int updateByPrimaryKey(BookTagsMap record);
}