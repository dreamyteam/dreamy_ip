package com.dreamy.mapper.ipcool;

import com.dreamy.domain.ipcool.NewsMedia;
import com.dreamy.domain.ipcool.NewsMediaConditions;
import java.util.List;

import com.dreamy.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface NewsMediaMapper extends BaseMapper<NewsMedia,Integer,NewsMediaConditions> {
    int countByExample(NewsMediaConditions example);

    int deleteByExample(NewsMediaConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(NewsMedia record);

    int insertSelective(NewsMedia record);

    List<NewsMedia> selectByExample(NewsMediaConditions example);

    NewsMedia selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NewsMedia record, @Param("example") NewsMediaConditions example);

    int updateByExample(@Param("record") NewsMedia record, @Param("example") NewsMediaConditions example);

    int updateByPrimaryKeySelective(NewsMedia record);

    int updateByPrimaryKey(NewsMedia record);
}