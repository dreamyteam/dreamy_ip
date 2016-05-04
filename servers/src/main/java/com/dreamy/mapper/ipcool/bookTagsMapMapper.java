package com.dreamy.mapper.ipcool;

import com.dreamy.domain.ipcool.bookTagsMap;
import com.dreamy.domain.ipcool.bookTagsMapConditions;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface bookTagsMapMapper {
    int countByExample(bookTagsMapConditions example);

    int deleteByExample(bookTagsMapConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(bookTagsMap record);

    int insertSelective(bookTagsMap record);

    List<bookTagsMap> selectByExample(bookTagsMapConditions example);

    bookTagsMap selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") bookTagsMap record, @Param("example") bookTagsMapConditions example);

    int updateByExample(@Param("record") bookTagsMap record, @Param("example") bookTagsMapConditions example);

    int updateByPrimaryKeySelective(bookTagsMap record);

    int updateByPrimaryKey(bookTagsMap record);
}