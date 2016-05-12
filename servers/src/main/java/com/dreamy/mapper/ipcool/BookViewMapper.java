package com.dreamy.mapper.ipcool;

import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.BookViewConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BookViewMapper extends BaseMapper<BookView,Integer,BookViewConditions> {
    int countByExample(BookViewConditions example);

    int deleteByExample(BookViewConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookView record);

    int insertSelective(BookView record);

    List<BookView> selectByExampleWithBLOBs(BookViewConditions example);

    List<BookView> selectByExample(BookViewConditions example);

    BookView selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookView record, @Param("example") BookViewConditions example);

    int updateByExampleWithBLOBs(@Param("record") BookView record, @Param("example") BookViewConditions example);

    int updateByExample(@Param("record") BookView record, @Param("example") BookViewConditions example);

    int updateByPrimaryKeySelective(BookView record);

    int updateByPrimaryKeyWithBLOBs(BookView record);

    int updateByPrimaryKey(BookView record);
}