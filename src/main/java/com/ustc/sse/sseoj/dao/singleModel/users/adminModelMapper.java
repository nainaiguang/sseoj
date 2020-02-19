package com.ustc.sse.sseoj.dao.singleModel.users;

import com.ustc.sse.sseoj.model.user.adminModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface adminModelMapper {
    int deleteByPrimaryKey(String no);

    int insert(adminModel record);

    int insertSelective(adminModel record);

    adminModel selectByPrimaryKey(String no);

    int updateByPrimaryKeySelective(adminModel record);

    int updateByPrimaryKey(adminModel record);
}