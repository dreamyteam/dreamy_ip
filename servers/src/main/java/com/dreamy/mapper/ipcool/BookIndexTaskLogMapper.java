package com.dreamy.mapper.ipcool;

import com.dreamy.domain.ipcool.BookIndexTaskLog;
import com.dreamy.domain.ipcool.BookIndexTaskLogConditions;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookIndexTaskLogMapper {
    int countByExample(BookIndexTaskLogConditions example);

    int deleteByExample(BookIndexTaskLogConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookIndexTaskLog record);

    int insertSelective(BookIndexTaskLog record);

    List<BookIndexTaskLog> selectByExample(BookIndexTaskLogConditions example);

    BookIndexTaskLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookIndexTaskLog record, @Param("example") BookIndexTaskLogConditions example);

    int updateByExample(@Param("record") BookIndexTaskLog record, @Param("example") BookIndexTaskLogConditions example);

    int updateByPrimaryKeySelective(BookIndexTaskLog record);

    int updateByPrimaryKey(BookIndexTaskLog record);
}