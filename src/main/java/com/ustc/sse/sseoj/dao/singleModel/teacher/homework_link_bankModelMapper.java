package com.ustc.sse.sseoj.dao.singleModel.teacher;

import com.ustc.sse.sseoj.model.teacher.homework_link_bankModel;
import com.ustc.sse.sseoj.model.teacher.homework_link_bankModelKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface homework_link_bankModelMapper {
    int deleteByPrimaryKey(homework_link_bankModelKey key);

    int insert(homework_link_bankModel record);

    int insertSelective(homework_link_bankModel record);

    homework_link_bankModel selectByPrimaryKey(homework_link_bankModelKey key);

    int updateByPrimaryKeySelective(homework_link_bankModel record);

    int updateByPrimaryKey(homework_link_bankModel record);
}